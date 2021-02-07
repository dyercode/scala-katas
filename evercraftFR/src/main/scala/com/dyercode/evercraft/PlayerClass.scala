package com.dyercode.evercraft

import com.dyercode.evercraft.Combatant

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

  def baseHitPoints: Int = 5

  def critMultiplier: Int = 2

  def baseAttack(ch: Character): Int = ch.level / 2

  def attackStatMod(ch: Character): Int = ch.strength.modifier

  def targetAcModifier[A: Combatant](ch: A): Int = ch.armorClass

  def acMod(ch: Character): Int = 0

  def attackModifier[A: Combatant : Aligned](d: A): Int = 0

  def checkRequirements[A: Aligned](a: A): Boolean = true
}

object PlayerClass {
  type MoralFighter[A] = Combatant[A] with Aligned[A]
}

object DefaultClass extends PlayerClass

object Fighter extends PlayerClass {
  override def baseHitPoints: Int = 10

  override def baseAttack(ch: Character): Int = ch.level
}

object Rogue extends PlayerClass {
  override def critMultiplier: Int = 3

  override def targetAcModifier[A: Combatant](ch: A): Int = {
    ch.armorClass - Math.max(0, ch.acDexBonus)
  }

  override def attackStatMod(ch: Character): Int = ch.dexterity.modifier
}

object Monk extends PlayerClass {
  override def baseHitPoints: Int = 6

  override def baseDamage: Int = 3

  override def baseAttack(ch: Character): Int =
    (1 to ch.level).count(l => l % 2 == 0 || l % 3 == 0)

  override def acMod(ch: Character): Int = Math.max(0, ch.wisdom.modifier)
}

object Paladin extends PlayerClass {
  override def checkRequirements[A: Aligned](a: A): Boolean = a.alignment match {
    case Alignment.Good => true
    case x => {
      require(false, s"Paladin must be good but was $x");
      false
    }
  }

  override def attackModifier[A: Combatant : Aligned](d: A): Int = {
    d.alignment match {
      case Alignment.Evil => 2
      case _ => 0
    }
  }
}
