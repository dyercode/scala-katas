package com.dyercode.evercraft

import com.dyercode.evercraft.PlayerClass.MoralFighter

trait Combatant[A] {
  def armorClass(a: A): Int
  def hitPoints(a: A): Int
  def attack[B](a: A, roll: Int, d: B)(
      implicit cb: Combatant[B],
      al: Aligned[B]
  ): AttackResult
  def calculateDamage(a: A, ar: AttackResult): Int
  def takeDamage(a: A, ar: Int): A
  def dead(a: A): Boolean
  def attackBonus[B](a: A, b: B)(implicit cb: Combatant[B], al: Aligned[B]): Int
  def acDexBonus(a: A): Int
}

object Combatant {
  implicit class CombatantOps[A](a: A)(implicit c: Combatant[A]) {
    def hitPoints: Int = c.hitPoints(a)
    def armorClass: Int = c.armorClass(a)
    def calculateDamage(ar: AttackResult): Int = c.calculateDamage(a, ar)
    def takeDamage(damage: Int): A = c.takeDamage(a, damage)
    def attack[B](
        roll: Int,
        defender: B
    )(implicit cb: Combatant[B], al: Aligned[B]): AttackResult =
      c.attack(a, roll, defender)
    def dead: Boolean = c.dead(a)
    def attackBonus[B](
        defender: B
    )(implicit cb: Combatant[B], al: Aligned[B]): Int =
      c.attackBonus(a, defender)
    def acDexBonus: Int = c.acDexBonus(a)
  }
}
