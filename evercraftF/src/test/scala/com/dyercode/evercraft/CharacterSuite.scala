package com.dyercode.evercraft

import org.scalatest.{BeforeAndAfter, FunSuite, MustMatchers}

class CharacterSuite extends FunSuite with MustMatchers with BeforeAndAfter {

  val name = "name"
  val defaultHitPoints = 5
  val defaultArmorClass: Int = 10
  val meetACRoll = 10
  val beatRoll = 11
  val missRoll = 9
  val naturalTwenty = 20
  val victim = new Character("victim", Neutral)

  test("changing character name must be a new character") {
    val char = Character(name, Neutral)
    val newChar = char.changeName("fish")
    char.name mustBe "name"
    newChar.name mustBe "fish"
  }

  //These next three are really more behavioral that unit testy :/
  test("character alignment must be able to be good") {
    val character = Character(name, Good)
    character.alignment mustBe Good
  }

  test("character alignment must be able to be Neutral") {
    val character = Character("name", Neutral)
    character.alignment mustBe Neutral
  }

  test("character alignment must be able to be Evil") {
    val character = Character(name, Evil)
    character.alignment mustBe Evil
  }

  test("character must be able to change alignment") {
    val character = Character(name, Good)
    val newChar = character.changeAlignment(Evil)
    character.alignment mustBe Good
    newChar.alignment mustBe Evil
  }

  test("character armor class must default to the correct value") {
    val character = Character(name, Neutral)
    character.armorClass mustBe defaultArmorClass
  }

  test("character must hit when roll is higher than AC") {
    val character = Character(name, Neutral)
    character.isAHit(beatRoll, victim) mustBe Hit
  }

  test("character must hit when roll meets AC") {
    val character = Character(name, Neutral)
    character.isAHit(meetACRoll, victim) mustBe Hit
  }

  test("character must not hit when roll does not meet AC") {
    val character = Character("name", Neutral)
    character.isAHit(missRoll, victim) mustBe Miss
  }

  test("If attack is successful, other character takes 1 point of damage when hit") {
    val initialHitPoints = victim.hitPoints
    val character = Character("name", Neutral)
    character attack(victim, beatRoll)
    assert(victim.hitPoints === initialHitPoints - 1)
  }

  test("If a roll is a natural 20 then a critical hit") {
    val character = Character("name", Neutral)
    assert(character.isAHit(naturalTwenty, victim) === Critical)
  }

  test("is dealt and the damage is doubled") {
    val expectedHitPoints = victim.hitPoints - 2
    val character = Character("name", Neutral)
    character.attack(victim, naturalTwenty)
    assert(victim.hitPoints === expectedHitPoints)
  }

  test("when hit points are 0, the character is dead") {
    val character = Character("name", Neutral)
    character damage character.hitPoints
    assert(character.isDead)
  }

  test("when hit points are less than zero, the character is dead") {
    val character = Character("name", Neutral)
    character.hitPoints = -1
  }

  test("character must have a default strength ability") {
    val character = Character("name", Neutral)
    character.strength.score must be(10)
  }

  test("character must have a default dexterity ability") {
    val character = Character("name", Neutral)
    character.dexterity.score must be(10)
  }

  test("character must have a default constitution ability") {
    val character = Character("name", Neutral)
    character.constitution.score must be(10)
  }

  test("character must have a default wisdom ability") {
    val character = Character("name", Neutral)
    character.wisdom.score must be(10)
  }

  test("character must have a default intelligence ability") {
    val character = Character("name", Neutral)
    character.intelligence.score mustBe 10
  }

  test("character must have a default charisma ability") {
    val character = Character("name", Neutral)
    character.charisma.score must be(10)
  }

  test("strength modifier must be added to attack") {
    val character = Character("name", Neutral, strength = Ability(18))
    val attackRoll = meetACRoll - character.strength.modifier
    character.isAHit(attackRoll, victim) must be(Hit)
  }

  test("strength modifier must be added to damage") {
    val character = Character("name", Neutral, strength = Ability(18))
    val initialVictimHitPoints = victim.hitPoints
    character.attack(victim, meetACRoll)
    val expectedResultingHitPoints = initialVictimHitPoints - (1 + character.strength.modifier)
    victim.hitPoints must be(expectedResultingHitPoints)
  }

  test("critical hits must instead add double the strength modifier to damage") {
    val character = Character("name", Neutral, strength = Ability(18))
    val initialVictimHitPoints = victim.hitPoints
    val expectedResultingHitPoints = initialVictimHitPoints - 10
    character.attack(victim, naturalTwenty)
    victim.hitPoints must be(expectedResultingHitPoints)
  }

  test("minimum damage must be 1 on a normal hit") {
    val character = Character("name", Neutral, strength = Ability(1))
    val initialHitPoints = victim.hitPoints
    character.attack(victim, victim.armorClass - character.strength.modifier)
    victim.hitPoints must be(initialHitPoints - 1)
  }
  test("minimum damage must be 1 on a crit") {
    val character = Character("name", Neutral, strength = Ability(1))
    val initialHitPoints = victim.hitPoints
    character.attack(victim, naturalTwenty)
    victim.hitPoints must be(initialHitPoints - 1)
  }

  test("dexterityModifier must be added to armor class") {
    val character = Character("name", Neutral, dexterity = Ability(18))
    character.armorClass must be(defaultArmorClass + 4)
  }

  test("constitution modifier and level bonus must be added to hit points at level 1") {
    val character = Character("name", Neutral, constitution = Ability(18))
    val levelBonus = character.constitution.modifier + 5
    character.hitPoints must be(defaultHitPoints + levelBonus + 4)
  }

  test("when an attack hits character must gain 10xp") {
    val character = Character("name", Neutral)
    val startingXP = character.experiencePoints
    character
      .attack(victim, victim.armorClass)
      .experiencePoints must be(startingXP + 10)
  }

  test("when an attack is a crit character must gain 10xp") {
    val character = Character("name", Neutral)
    val startingXP = character.experiencePoints
    character
      .attack(victim, naturalTwenty)
      .experiencePoints must be(startingXP + 10)
  }

  test("character must start with 0xp") {
    val character = Character("name", Neutral)
    character.experiencePoints mustBe 0
  }

  test("character must start at level 1") {
    Character("name", Neutral).level mustBe 1
  }

  test("character level must increase for every thousand experience it has") {
    val character = Character("name", Neutral)
    (1 to 4) foreach { x =>
      character.gainXp(x * 1000).level must be(x + 1)
    }
  }

  test("character hitPoints must increase by 5 plus con mod for each level") {
    val character = Character("name", Neutral)
    val startingHP = character.hitPoints
    character.gainXp(1000).hitPoints must be(startingHP + 5 + character.constitution.modifier)
  }

  test("for each level 1 is added to attack roll for every even level achieved") {

  }
}
