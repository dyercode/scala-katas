package com.dyercode.evercraft

import com.dyercode.evercraft.Alignment
import com.dyercode.evercraft.Aligned

case class Character(
                      name: String,
                      _alignment: Alignment,
                      strength: Ability = Ability(),
                      dexterity: Ability = Ability(),
                      constitution: Ability = Ability(),
                      intelligence: Ability = Ability(),
                      wisdom: Ability = Ability(),
                      charisma: Ability = Ability(),
                      playerClass: PlayerClass = DefaultClass,
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
}

given Aligned[Character] with
  extension(a: Character) def alignment: Alignment = a._alignment

enum AttackResult:
  case Crit, Hit, Miss

object Character {
  def changeAlignment(
                       character: Character,
                       alignment: Alignment
                     ): Character = {
    character.copy(_alignment = alignment)
  }
}

given Combatant[Character] with {

  // TODO - consider returning and object with a breakdown of bonuses, rather than an Int
  // This will make doing things like ignoring specific modifiers cleaner to add.
  extension (a: Character) def armorClass: Int = 10 + acDexBonus(a) + a.playerClass.acMod(a)

  extension [B: Combatant : Aligned](c: Character) def attack( roll: Int, defender: B ): AttackResult =
    if (roll == 20) AttackResult.Crit
    else {
      if (roll + c.attackBonus(defender) >= c.playerClass.targetAcModifier(
        defender
      )) {
        AttackResult.Hit
      } else AttackResult.Miss
    }

  extension (c: Character) def calculateDamage(ar: AttackResult): Int = {
    val rawDamage = c.playerClass.baseDamage + c.strength.modifier
    val critMultiplier = if (ar == AttackResult.Crit) c.playerClass.critMultiplier else 1
    Math.max(1, rawDamage * critMultiplier)
  }

  extension (a: Character) def hitPoints: Int = {
    val cap = Math.max(
      1,
      a.playerClass.baseHitPoints + a.constitution.modifier
    ) * a.level
    cap - a.damage
  }

  extension (c: Character) def takeDamage(dmg: Int): Character =
    c.copy(damage = c.damage + dmg)

  extension (c: Character) def dead: Boolean =  hitPoints(c) <= 0

  extension[B: Combatant : Aligned] (character: Character) def attackBonus(defender: B): Int = {
    character.playerClass.attackStatMod(character) +
      character.playerClass.baseAttack(character) +
      character.playerClass.attackModifier(defender)
  }

  extension (c: Character) def acDexBonus: Int = c.dexterity.modifier
}