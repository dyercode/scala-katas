package com.dyercode.evercraft
import com.dyercode.evercraft.Combatant._

case class Character(
    name: String,
    alignment: Alignment,
    strength: Ability = Ability(),
    dexterity: Ability = Ability(),
    constitution: Ability = Ability(),
    intelligence: Ability = Ability(),
    wisdom: Ability = Ability(),
    charisma: Ability = Ability(),
    damage: Int = 0
)

sealed trait AttackResult
case object Crit extends AttackResult
case object Hit extends AttackResult
case object Miss extends AttackResult

object Character {
  def changeAlignment(character: Character, alignment: Alignment): Character = {
    character.copy(alignment = alignment)
  }

  implicit val characterCombatant: Combatant[Character] =
    new Combatant[Character] {
      override def armorClass(a: Character): Int = 10 + a.dexterity.modifier
      override def attack[B: Combatant](
          a: Character,
          roll: Int,
          d: B
      ): AttackResult =
        if (roll == 20) Crit
        else {
          if (roll + a.strength.modifier >= d.armorClass) Hit else Miss
        }

      override def calculateDamage(c: Character, ar: AttackResult): Int = {
        val rawDamage = 1 + c.strength.modifier
        val critMultiplier = if (ar == Crit) 2 else 1
        Math.max(1, rawDamage * critMultiplier)
      }
      override def hitPoints(a: Character): Int =
        5 + a.constitution.modifier - a.damage
      override def takeDamage(c: Character, dmg: Int): Character =
        c.copy(damage = c.damage + dmg)

      override def dead(a: Character): Boolean = hitPoints(a) <= 0
    }
}
