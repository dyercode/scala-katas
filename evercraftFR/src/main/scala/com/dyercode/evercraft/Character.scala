package com.dyercode.evercraft
//import com.dyercode.evercraft.PlayerClass.MoralFighter
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
  extension (a: Character) def alignment: Alignment = a._alignment


sealed trait AttackResult
case object Crit extends AttackResult
case object Hit extends AttackResult
case object Miss extends AttackResult

object Character {
  def changeAlignment(
      character: Character,
      alignment: Alignment
  ): Character = {
    character.copy(_alignment = alignment)
  }
//  implicit val characterAligned: Aligned[Character] = new Aligned[Character] {
//    override def alignment(a: Character): Alignment = a._alignment
//  }

  implicit val characterCombatant: Combatant[Character] =
    new Combatant[Character] {
      // TODO - consider returning and object with a breakdown of bonuses, rather than an Int
      // This will make doing things like ignoring specific modifiers cleaner to add.
      override def armorClass(a: Character): Int =
        10 + acDexBonus(a) + a.playerClass.acMod(a)
      override def attack[B](
          c: Character,
          roll: Int,
          defender: B
      )(implicit cb: Combatant[B], al: Aligned[B]): AttackResult =
        if (roll == 20) Crit
        else {
          if (roll + attackBonus(c, defender) >= c.playerClass.targetAcModifier(
                defender
              )) {
            Hit
          } else Miss
        }

      override def calculateDamage(c: Character, ar: AttackResult): Int = {
        val rawDamage = c.playerClass.baseDamage + c.strength.modifier
        val critMultiplier = if (ar == Crit) c.playerClass.critMultiplier else 1
        Math.max(1, rawDamage * critMultiplier)
      }

      override def hitPoints(a: Character): Int = {
        val cap = Math.max(
          1,
          a.playerClass.baseHitPoints + a.constitution.modifier
        ) * a.level
        cap - a.damage
      }

      override def takeDamage(c: Character, dmg: Int): Character =
        c.copy(damage = c.damage + dmg)

      override def dead(a: Character): Boolean = hitPoints(a) <= 0

      override def attackBonus[B](
          character: Character,
          defender: B
      )(implicit cb: Combatant[B], al: Aligned[B]): Int = {
        character.playerClass.attackStatMod(character) +
          character.playerClass.baseAttack(character) +
          character.playerClass.attackModifier(defender)
      }

      override def acDexBonus(a: Character): Int = a.dexterity.modifier
    }
}
