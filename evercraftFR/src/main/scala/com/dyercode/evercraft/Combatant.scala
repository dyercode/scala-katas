package com.dyercode.evercraft

import simulacrum.typeclass

@typeclass trait Combatant[A] {
  def armorClass(a: A): Int
  def hitPoints(a: A): Int
  def attack[B: Combatant](roll: Int, d: B): AttackResult
  def takeDamage(a: A, ar: AttackResult): A

}

object Combatant {
  implicit class CombatantOps[A](a: A)(implicit c: Combatant[A]) {
    def hitPoints: Int = c.hitPoints(a)
    def armorClass: Int = c.armorClass(a)
    def takeDamage(ar: AttackResult): A = c.takeDamage(a, ar)
  }
}
