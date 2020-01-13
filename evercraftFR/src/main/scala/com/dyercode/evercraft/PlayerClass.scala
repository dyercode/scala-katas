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
}

//object Class {
//  implicit class ClassOps[A](a: A)(implicit c: Class[A]) {
//    def baseHitPoints: Int = c.baseHitPoints
//  }
//}

object DefaultClass extends PlayerClass {
//  implicit val defaultClass: Class[Fighter] = new Class[DefaultCharacterClass] {
//    override def baseHitPoints(a: DefaultCharacterClass): Int = 5
//  }
  override def baseHitPoints: Int = 5
}

object Fighter extends PlayerClass {
  override def baseHitPoints: Int = 10
}
