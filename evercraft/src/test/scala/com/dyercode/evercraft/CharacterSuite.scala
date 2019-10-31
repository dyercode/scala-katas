package com.dyercode.evercraft

import org.scalatest.{
  MustMatchers,
  OneInstancePerTest,
  BeforeAndAfter,
  FunSuite
}

class CharacterSuite
    extends FunSuite
    with MustMatchers
    with BeforeAndAfter
    with OneInstancePerTest {

  val name = "name"
  val defaultHitPoints = 5
  val defaultArmorClass: Int = 10
  val meetACRoll = 10
  val beatRoll = 11
  val missRoll = 9
  val naturalTwenty = 20
  var character: Character = _
  val victim = new Character("victim", Neutral)

  before {
    character = new Character(name, Neutral)
  }

  test("character must have a mutable name") {
    character.name = name
    assert(character.name === name)
  }

  test("character alignment must be able to be good") {
    character.alignment = Good
    assert(character.alignment === Good)
  }

  test("character alignment must be able to be Neutral") {
    character.alignment = Neutral
    assert(character.alignment === Neutral)
  }

  test("character alignment must be able to be Evil") {
    character.alignment = Evil
    assert(character.alignment === Evil)
  }

  test("character must be able to change alignment") {
    character.alignment = Good
    assert(character.alignment === Good)
    character.alignment = Evil
    assert(character.alignment === Evil)
  }

  test("character armor class must default to the correct value") {
    assert(character.armorClass === defaultArmorClass)
  }

  test("character must hit when roll is higher than AC") {
    assert(character.isAHit(beatRoll, victim) === Hit)
  }

  test("character must hit when roll meets AC") {
    assert(character.isAHit(meetACRoll, victim) === Hit)
  }

  test("character must not hit when roll does not meet AC") {
    assert(character.isAHit(missRoll, victim) === Miss)
  }

  test(
    "If attack is successful, other character takes 1 point of damage when hit"
  ) {
    val initialHitPoints = victim.hitPoints
    character attack (victim, beatRoll)
    assert(victim.hitPoints === initialHitPoints - 1)
  }

  test("If a roll is a natural 20 then a critical hit") {
    assert(character.isAHit(naturalTwenty, victim) === Critical)
  }

  test("is dealt and the damage is doubled") {
    val expectedHitPoints = victim.hitPoints - 2
    character.attack(victim, naturalTwenty)
    assert(victim.hitPoints === expectedHitPoints)
  }

  test("when hit points are 0, the character is dead") {
    character damage character.hitPoints
    assert(character.isDead)
  }

  test("when hit points are less than zero, the character is dead") {
    character.hitPoints = -1
  }

  test("character must have a default strength ability") {
    character.strength.score must be(10)
  }

  test("character must have a default dexterity ability") {
    character.dexterity.score must be(10)
  }

  test("character must have a default constitution ability") {
    character.constitution.score must be(10)
  }

  test("character must have a default wisdom ability") {
    character.wisdom.score must be(10)
  }

  test("character must have a default intelligence ability") {
    character.intelligence.score must be(10)
  }

  test("character must have a default charisma ability") {
    character.charisma.score must be(10)
  }

  test("strength modifier must be added to attack") {
    character.strength = Ability(18)
    val attackRoll = meetACRoll - character.strength.modifier
    character.isAHit(attackRoll, victim) must be(Hit)
  }

  test("strength modifier must be added to damage") {
    character.strength = Ability(18)
    val initialVictimHitPoints = victim.hitPoints
    character.attack(victim, meetACRoll)
    val expectedResultingHitPoints = initialVictimHitPoints - (1 + character.strength.modifier)
    victim.hitPoints must be(expectedResultingHitPoints)
  }

  test("critical hits must instead add double the strength modifier to damage") {
    val newChar = character.strength = Ability(18)
    val initialVictimHitPoints = victim.hitPoints
    val expectedResultingHitPoints = initialVictimHitPoints - (10)
    newChar.attack(victim, naturalTwenty)
    victim.hitPoints must be(expectedResultingHitPoints)
  }

  test("minimum damage must be 1 on a normal hit") {
    character.strength = Ability(1)
    val initialHitPoints = victim.hitPoints
    character.attack(victim, victim.armorClass - character.strength.modifier)
    victim.hitPoints must be(initialHitPoints - 1)
  }
  test("minimum damage must be 1 on a crit") {
    val newChar = character.strength = Ability(1)
    val initialHitPoints = victim.hitPoints
    newChar.attack(victim, naturalTwenty)
    victim.hitPoints must be(initialHitPoints - 1)
  }

  test("dexterityModifier must be added to armor class") {
    val newCharacter = character.dexterity = Ability(18)
    newCharacter.armorClass must be(defaultArmorClass + 4)
  }

  test(
    "constitution modifier and level bonus must be added to hit points at level 1"
  ) {
    val newChar = character.constitution = Ability(18)
    val levelBonus = newChar.constitution.modifier + 5
    newChar.hitPoints must be(defaultHitPoints + levelBonus + 4)
  }

  test("when an attack hits character must gain 10xp") {
    val startingXP = character.experiencePoints
    character.attack(victim, victim.armorClass)
    character.experiencePoints must be(startingXP + 10)
  }

  test("when an attack is a crit character must gain 10xp") {
    val startingXP = character.experiencePoints
    character.attack(victim, naturalTwenty)
    character.experiencePoints must be(startingXP + 10)
  }

  test("character must start with 0xp") {
    character.experiencePoints must be(0)
  }

  test("character must start at level 1") {
    character.level must be(1)
  }

  test("character level must increase for every thousand experience it has") {
    (1 to 4).toList foreach { x =>
      character.experiencePoints = x * 1000
      character.level must be(x + 1)
    }
  }

  test("character hitPoints must increase by 5 plus con mod for each level") {
    val startingHP = character.hitPoints
    character.experiencePoints += 1000
    character.hitPoints must be(
      startingHP + 5 + character.constitution.modifier
    )
  }

  test("for each level 1 is added to attack roll for every even level achieved") {}
}
