package com.dyercode.evercraft

trait Combatant[A] {
  extension [B: Raced](a: A) def armorClass(attacker: B): Int
  extension (a: A) def hitPoints: Int
  extension [B : Combatant : Aligned : Raced] (a: A) def attack(roll: Int, d: B): AttackResult
  extension [B : Aligned : Raced] (a: A) def calculateDamage(ar: AttackResult, b : B): Int
  extension (a: A) def takeDamage(ar: Int): A
  extension (a: A) def dead: Boolean
  extension [B: Combatant : Aligned : Raced] (a: A) def attackBonus(b: B): Int
  extension (a: A) def acDexBonus: Int
}
