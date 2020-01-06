package com.dyercode.evercraft
import simulacrum._

case class Character(name: String, alignment: Alignment)

sealed trait AttackResult
case object Hit extends AttackResult
case object Miss extends AttackResult

object Character {
  def changeAlignment(character: Character, alignment: Alignment): Character = {
    character.copy(alignment = alignment)
  }

  implicit val characterCombatant: Combatant[Character] =
    new Combatant[Character] {
      override def armorClass: Int = 10

      override def hitPoints: Int = 5

      override def attack[B](roll: Int, d: B)(
          implicit defender: Combatant[Character]
      ): AttackResult =
        if (roll >= defender.armorClass) Hit else Miss
    }
}

object Combat {
  def atk[A, B](a: A, r: Int, b: B)(
      implicit ca: Combatant[A],
      cb: Combatant[B]
  ): AttackResult = ca.attack(r, cb)
}
