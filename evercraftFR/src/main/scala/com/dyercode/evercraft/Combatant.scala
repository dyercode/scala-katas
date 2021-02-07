package com.dyercode.evercraft

trait Combatant[A] {
  extension (a: A) def armorClass: Int
  extension (a: A) def hitPoints: Int
  extension [B : Combatant : Aligned] (a: A) def attack(roll: Int, d: B): AttackResult
  extension (a: A) def calculateDamage(ar: AttackResult): Int
  extension (a: A) def takeDamage(ar: Int): A
  extension (a: A) def dead: Boolean
  extension [B: Combatant : Aligned] (a: A) def attackBonus(b: B): Int
  extension (a: A) def acDexBonus: Int
}
