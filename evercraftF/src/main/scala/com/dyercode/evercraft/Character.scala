package com.dyercode.evercraft

case class Character(
    name: String,
    alignment: Alignment,
    experiencePoints: Int = 0,
    strength: Ability = Ability(),
    dexterity: Ability = Ability(),
    constitution: Ability = Ability(),
    wisdom: Ability = Ability(),
    intelligence: Ability = Ability(),
    charisma: Ability = Ability()
) {
  def gainXp(xp: Int): Character =
    copy(experiencePoints = experiencePoints + xp)

  def changeAlignment(alignment: Alignment): Character =
    copy(alignment = alignment)

  private val _armorClass: Int = 10
  private var _hitPoints = 5
  private val baseDamage = 1
  private val baseCriticalDamage = 2
  private val minimumAttackDamage = 1
  private val experiencePointsToLevel = 1000
  private val experiencePerAttack = 10
  private val criticalHitMatcher = 20

  def changeName(n: String): Character = copy(name = n)

  def changeStrength(s: Ability): Character = copy(strength = s)

  def changeDexterity(value: Ability): Character = copy(dexterity = value)

  def changeConstitution(value: Ability): Character = copy(constitution = value)

  def changeIntelligence(value: Ability): Character = copy(intelligence = value)

  def changeWisdom(value: Ability): Character = copy(wisdom = value)

  def changeCharisma(value: Ability): Character = copy(charisma = value)

  def hitPoints_-=(value: Int): Unit = _hitPoints = _hitPoints - value

  def hitPoints_=(value: Int): Unit = _hitPoints = value

  def levelBonusToHitPoints: Int = (5 + constitution.modifier) * level

  private def conBonusToBaseHitPoints: Int =
    if (constitution.modifier > 1) constitution.modifier else 1

  def hitPoints: Int =
    _hitPoints + conBonusToBaseHitPoints + levelBonusToHitPoints

  def armorClass: Int = _armorClass + dexterity.modifier

  def isAHit(roll: Int, target: Character): AttackResult = {
    roll match {
      case `criticalHitMatcher`                            => Critical
      case x if x + strength.modifier >= target.armorClass => Hit
      case _                                               => Miss
    }
  }

  def attack(target: Character, roll: Int): Character = {
    isAHit(roll, target) match {
      case Hit =>
        target damage normalDamage
        copy(experiencePoints = experiencePerAttack)
      case Critical =>
        target damage criticalDamage
        copy(experiencePoints = experiencePerAttack)
      case _ => this
    }
  }

  def damage(points: Int): Unit = _hitPoints -= points

  private def normalDamage(): Int = {
    val initialCalc = baseDamage + strength.modifier
    if (initialCalc >= 1) initialCalc else minimumAttackDamage
  }

  private def criticalDamage() = {
    val initialCalc = baseCriticalDamage + (strength.modifier * 2)
    if (initialCalc >= 1) initialCalc else minimumAttackDamage
  }

  def isDead: Boolean = hitPoints <= 0

  def level: Int = 1 + (experiencePoints / experiencePointsToLevel)
}
