package com.dyercode.evercraft

import com.dyercode.evercraft.Combatant
import com.dyercode.evercraft.Alignment._

trait PlayerClass {
  /*
    changes in hit points
    changes in attack and damage
    increased critical range or damage
    bonuses/penalties versus other classes
    special abilities
    alignment limitations
   */

  def baseDamage: Int = 1
  def extraDamage[D : Aligned](defender: D): Int = 0
  def baseHitPoints: Int = 5
  def critMultiplier[A: Aligned](defender: A): Int = 2
  def baseAttack(ch: Character): Int = ch.level / 2
  def attackStatMod(ch: Character): Int = ch.strengthModifier
  def targetAcModifier[A: Combatant](ch: A): Int = 0
  def acMod(self: Character): Int = 0
  def attackModifier[A: Combatant : Aligned](d: A): Int = 0
  def checkRequirements[A: Aligned](a: A): Boolean = true
}

object DefaultClass extends PlayerClass

object Fighter extends PlayerClass {
  override def baseHitPoints: Int = 10

  override def baseAttack(ch: Character): Int = ch.level
}

object Rogue extends PlayerClass {
  override def critMultiplier[A: Aligned](a: A): Int = 3

  override def targetAcModifier[A: Combatant](ch: A): Int = {
    -Math.max(0, ch.acDexBonus)
  }

  override def attackStatMod(ch: Character): Int = ch.dexterityModifier
}

object Monk extends PlayerClass {
  override def baseHitPoints: Int = 6

  override def baseDamage: Int = 3

  override def baseAttack(ch: Character): Int =
    (1 to ch.level).count(l => l % 2 == 0 || l % 3 == 0)

  override def acMod(self: Character): Int = Math.max(0, self.wisdomModifier)
}

object Paladin extends PlayerClass {
  override def baseAttack(ch: Character): Int = ch.level
  
  override def extraDamage[D : Aligned](defender: D): Int = defender.alignment match {
    case Evil => 2
    case _ => 0
  }
  
  override def critMultiplier[A: Aligned](d: A): Int = d.alignment match {
    case Evil => 3
    case _ => {
      println(d); 2
    }
  }
  
  override def checkRequirements[A: Aligned](a: A): Boolean = a.alignment match {
    case Alignment.Good => true
    case x => {
      require(false, s"Paladin must be good but was $x");
      false
    }
  }

  override def attackModifier[A: Combatant : Aligned](d: A): Int = d.alignment match {
    case Alignment.Evil => 2
    case _ => 0
  }
}
