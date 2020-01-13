package com.dyercode.evercraft

trait Combatant[A] {
  def armorClass(a: A): Int
  def hitPoints(a: A): Int
  def attack[B: Combatant](a: A, roll: Int, d: B): AttackResult
  def calculateDamage(a: A, ar: AttackResult): Int
  def takeDamage(a: A, ar: Int): A
  def dead(a: A): Boolean
  def attackBonus(a: A): Int
  def acDexBonus(a: A): Int
}

object Combatant {
  implicit class CombatantOps[A](a: A)(implicit c: Combatant[A]) {
    def hitPoints: Int = c.hitPoints(a)
    def armorClass: Int = c.armorClass(a)
    def calculateDamage(ar: AttackResult): Int = c.calculateDamage(a, ar)
    def takeDamage(damage: Int): A = c.takeDamage(a, damage)
    def attack[B: Combatant](roll: Int, defender: B): AttackResult =
      c.attack(a, roll, defender)
    def dead: Boolean = c.dead(a)
    def attackBonus: Int = c.attackBonus(a)
    def acDexBonus: Int = c.acDexBonus(a)
  }
}
