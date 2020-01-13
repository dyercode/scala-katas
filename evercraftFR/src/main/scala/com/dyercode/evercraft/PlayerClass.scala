package com.dyercode.evercraft

trait PlayerClass {
  /*
    changes in hit points
    changes in attack and damage
    increased critical range or damage
    bonuses/penalties versus other classes
    special abilities
    alignment limitations
   */

  def baseHitPoints: Int
  def attackModifier(character: Character): Int
}

object DefaultClass extends PlayerClass {
  override def baseHitPoints: Int = 5

  override def attackModifier(ch: Character): Int = ch.level / 2
}

object Fighter extends PlayerClass {
  override def baseHitPoints: Int = 10

  override def attackModifier(ch: Character): Int = ch.level
}
