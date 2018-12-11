package com.dyercode.evercraft


case class Character(
  var name: String,
  var alignment: Alignment,
  _strength: Ability = Ability(),
  _dexterity: Ability = Ability(),
  _constitution: Ability = Ability(),
  _wisdom: Ability = Ability(),
  _intelligence: Ability = Ability(),
  _charisma: Ability = Ability()
) {
  private val _armorClass: Int = 10
  private var _hitPoints = 5
  private val baseDamage = 1
  private val baseCriticalDamage = 2
  private val minimumAttackDamage = 1
  private val experiencePointsToLevel = 1000
  private val experiencePerAttack = 10
  private val criticalHitMatcher = 20
  var experiencePoints = 0

  def strength_=(value: Ability): Character = this.copy(_strength = value)

  def strength: Ability = _strength

  def dexterity_=(value: Ability): Character = this.copy(_dexterity = value)

  def dexterity: Ability = _dexterity

  def constitution_=(value: Ability): Character = this.copy(_constitution = value)

  def constitution: Ability = _constitution

  def intelligence_=(value: Ability): Character = this.copy(_intelligence = value)

  def intelligence: Ability = _intelligence

  def wisdom_=(value: Ability): Character = this.copy(_wisdom = value)

  def wisdom: Ability = _wisdom

  def charisma_=(value: Ability): Character = this.copy(_charisma = value)

  def charisma: Ability = _charisma

  def hitPoints_-=(value: Int): Unit = _hitPoints = _hitPoints - value

  def hitPoints_=(value: Int): Unit = _hitPoints = value

  def levelBonusToHitPoints: Int = (5 + constitution.modifier) * level

  private def conBonusToBaseHitPoints: Int = if (constitution.modifier > 1) constitution.modifier else 1

  def hitPoints: Int = _hitPoints + conBonusToBaseHitPoints + levelBonusToHitPoints

  def armorClass: Int = _armorClass + dexterity.modifier

  def isAHit(roll: Int, target: Character): AttackResult = {
    roll match {
      case `criticalHitMatcher` => Critical
      case x if x + strength.modifier >= target.armorClass => Hit
      case _ => Miss
    }
  }

  def attack(target: Character, roll: Int): Unit = {
    isAHit(roll, target) match {
      case Hit =>
        target damage normalDamage
        experiencePoints += experiencePerAttack
      case Critical =>
        target damage criticalDamage
        experiencePoints += experiencePerAttack
      case _ =>
    }
  }

  def damage(points: Int) = _hitPoints -= points

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
