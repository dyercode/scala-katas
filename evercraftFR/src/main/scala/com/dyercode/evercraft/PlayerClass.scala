package com.dyercode.evercraft
import Combatant._

trait PlayerClass {
  /*
    changes in hit points
    changes in attack and damage
    increased critical range or damage
    bonuses/penalties versus other classes
    special abilities
    alignment limitations
   */

  def baseHitPoints: Int = 5
  def critMultiplier: Int = 2
  def attackModifier(ch: Character): Int = ch.level / 2
  def attackStatMod(ch: Character): Int = ch.strength.modifier
  def targetAcModifier[A: Combatant](ch: A): Int = ch.armorClass
}

object DefaultClass extends PlayerClass

object Fighter extends PlayerClass {
  override def baseHitPoints: Int = 10
  override def attackModifier(ch: Character): Int = ch.level
}

object Rogue extends PlayerClass {
  override def critMultiplier: Int = 3
  override def targetAcModifier[A: Combatant](ch: A): Int = {
    ch.armorClass - Math.max(0, ch.acDexBonus)
  }
  override def attackStatMod(ch: Character): Int = ch.dexterity.modifier
}
