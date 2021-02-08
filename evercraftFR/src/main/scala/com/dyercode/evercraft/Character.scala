package com.dyercode.evercraft

import com.dyercode.evercraft.Alignment
import com.dyercode.evercraft.Aligned
import com.dyercode.evercraft.Race
import com.dyercode.evercraft.Raced

case class Character(
                      name: String,
                      _alignment: Alignment,
                      private val strength: Ability = Ability(),
                      private val dexterity: Ability = Ability(),
                      private val constitution: Ability = Ability(),
                      private val intelligence: Ability = Ability(),
                      private val wisdom: Ability = Ability(),
                      private val charisma: Ability = Ability(),
                      playerClass: PlayerClass = DefaultClass,
                      race: Race = DefaultRace,
                      damage: Int = 0,
                      xp: Int = 0
                    ) {
  require(playerClass.checkRequirements(this))

  // todo - gut tells me to make this a separate thing, too. but starting with simplest way. mostly because I don't know what to call that other thing.
  def gainXp(xp: Int): Character = {
    this.copy(xp = this.xp + xp)
  }

  def level: Int = {
    1 + (xp / 1000)
  }

  def strengthScore: Int = strength.value
  def dexterityScore: Int = dexterity.value
  def constitutionScore: Int = constitution.value
  def intelligenceScore: Int = intelligence.value
  def wisdomScore: Int = wisdom.value
  def charismaScore: Int = charisma.value
  def strengthModifier: Int = strength.modifier + race.strengthModifier
  def dexterityModifier: Int = dexterity.modifier + race.dexterityModifier
  def constitutionModifier: Int = constitution.modifier + race.constitutionModifier
  def intelligenceModifier: Int = intelligence.modifier + race.intelligenceModifier
  def wisdomModifier: Int = wisdom.modifier + race.wisdomModifier
  def charismaModifier: Int = charisma.modifier + race.charismaModifier
}

given Aligned[Character] with
  extension(a: Character) def alignment: Alignment = a._alignment

given Raced[Character] with
  extension(c: Character) def race: Race = c.race

object Character {
  def changeAlignment(character: Character, alignment: Alignment): Character = {
    character.copy(_alignment = alignment)
  }
}

given Combatant[Character] with {

  // TODO - consider returning and object with a breakdown of bonuses, rather than an Int
  // This will make doing things like ignoring specific modifiers cleaner to add.
  extension [A: Raced](defender: Character) def armorClass(attacker: A): Int = 10
    + acDexBonus(defender)
    + defender.playerClass.acMod (defender)
    + defender.race.acMod(attacker)

  extension[B: Combatant : Aligned : Raced] (attacker: Character) def attack(roll: Int, defender: B): AttackResult = {
    val critRange = 20 - attacker.race.critRangeModifier
    if (roll >= critRange) AttackResult.Crit
    else {
      val targetAc = defender.armorClass(attacker) + attacker.playerClass.targetAcModifier(defender)
      if (roll + attacker.attackBonus(defender) >= targetAc) {
        AttackResult.Hit
      } else AttackResult.Miss
    }
  }

  extension[D: Aligned : Raced] (c: Character) def calculateDamage(ar: AttackResult, defender: D): Int = {
    val rawDamage = c.playerClass.baseDamage
      + c.strengthModifier
      + c.playerClass.extraDamage(defender)
      + c.race.extraDamage(defender)
    val critMultiplier = if (ar == AttackResult.Crit) c.playerClass.critMultiplier(defender) else 1
    Math.max(1, rawDamage * critMultiplier)
  }

  extension (c: Character) def hitPoints: Int = {
    val cap = Math.max(
      1,
      c.playerClass.baseHitPoints + c.constitutionModifier + c.race.hitPointModifier(c)
    ) * c.level
    cap - c.damage
  }

  extension (c: Character) def takeDamage(dmg: Int): Character =
    c.copy(damage = c.damage + dmg)

  extension (c: Character) def dead: Boolean = hitPoints(c) <= 0

  extension[B: Combatant : Aligned : Raced] (character: Character) def attackBonus(defender: B): Int = {
    character.playerClass.attackStatMod(character) +
      character.playerClass.baseAttack(character) +
      character.playerClass.attackModifier(defender) +
      character.race.attackModifier(defender)
  }

  extension (c: Character) def acDexBonus: Int = c.dexterityModifier
}