package com.dyercode.evercraft
import com.dyercode.evercraft.Combatant._

case class Character(
    name: String,
    alignment: Alignment,
    _hitPoints: Int = 5,
    strength: Ability = Ability(),
    dexterity: Ability = Ability(),
    constitution: Ability = Ability(),
    intelligence: Ability = Ability(),
    wisdom: Ability = Ability(),
    charisma: Ability = Ability()
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
      override def armorClass(a: Character): Int = 10
      override def attack[B: Combatant](
          a: Character,
          roll: Int,
          d: B
      ): AttackResult =
        if (roll == 20) Crit
        else {
          if (roll + a.strength.modifier >= d.armorClass) Hit else Miss
        }

      override def hitPoints(a: Character): Int = a._hitPoints
      override def takeDamage(c: Character, ar: AttackResult): Character =
        ar match {
          case Crit => c.copy(_hitPoints = c._hitPoints - 2)
          case Hit  => c.copy(_hitPoints = c._hitPoints - 1)
          case _    => c
        }

      override def dead(a: Character): Boolean = a._hitPoints <= 0
    }
}
