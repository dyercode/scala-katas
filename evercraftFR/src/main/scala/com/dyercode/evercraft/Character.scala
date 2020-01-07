package com.dyercode.evercraft

import com.dyercode.evercraft.Combatant._
case class Character(name: String, alignment: Alignment, _hitPoints: Int = 5)

sealed trait AttackResult
case object Hit extends AttackResult
case object Miss extends AttackResult

object Character {
  def changeAlignment(character: Character, alignment: Alignment): Character = {
    character.copy(alignment = alignment)
  }

  implicit val characterCombatant: Combatant[Character] =
    new Combatant[Character] {
      override def armorClass(a: Character): Int = 10
      override def attack[A](roll: Int, d: A)(
          implicit defender: Combatant[A]
      ): AttackResult =
        if (roll >= defender.armorClass(d)) Hit else Miss

      override def hitPoints(a: Character): Int = a._hitPoints
      override def takeDamage(c: Character, ar: AttackResult): Character =
        ar match {
          case Hit => c.copy(_hitPoints = c._hitPoints - 1)
          case _   => c
        }
    }
}

object Combat {
  def damage[A](result: AttackResult, baddy: A)(
      implicit defender: Combatant[A]
  ): A = ???

  def atk[A, B](a: A, r: Int, b: B)(
      implicit ca: Combatant[A],
      cb: Combatant[B]
  ): AttackResult = ca.attack(r, b)
}