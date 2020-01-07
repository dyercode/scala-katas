package com.dyercode.evercraft

import com.dyercode.evercraft.Character._
import com.dyercode.evercraft.Combatant._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must
import org.scalatest.{BeforeAndAfter, OneInstancePerTest}

class CharacterSuite
    extends AnyFunSuite
    with must.Matchers
    with BeforeAndAfter
    with OneInstancePerTest {

  test("has a name and alignment") {
    val myDude = Character(name = "MyDude", alignment = Neutral)
    myDude.name mustBe "MyDude"
    myDude.alignment mustBe Neutral
  }

  test("can change to any of the alignments") {
    val billy = Character(name = "Billy", alignment = Good)
    val badBilly = changeAlignment(billy, Evil)
    val madBilly = changeAlignment(badBilly, Neutral)

    billy.alignment mustBe Good
    badBilly.alignment mustBe Evil
    madBilly.alignment mustBe Neutral
  }

  test("as a combatant I want to have armor class") {
    Character(name = "billy", alignment = Good).armorClass mustBe 10
  }

  test("as a combatant I want to have hitPoints") {
    Character(name = "billy", alignment = Good).hitPoints mustBe 5
  }

  test("roll must meet or beat opponent's armor class to hit") {
    val billy = Character(name = "Billy", alignment = Good)
    val baddy = Character(name = "Baddy", alignment = Evil)

    billy.attack(11, baddy) mustBe Hit
    billy.attack(10, baddy) mustBe Hit
    billy.attack(9, baddy) mustBe Miss
  }

  test(
    "if attack is successful, other character takes 1 point of damage when hit"
  ) {
    val baddy = Character(name = "Baddy", alignment = Good)
    val hitBaddy = baddy.takeDamage(Hit)
    hitBaddy.hitPoints mustBe 4
  }

  test(
    "if a roll is a natural 20 then a critical hit is dealt and damage is doubled"
  ) {
    val billy = Character(name = "Billy", alignment = Good)
    val baddy = Character(name = "Baddy", alignment = Good)
    val ar = billy.attack(20, baddy)
    val hitBaddy = baddy.takeDamage(ar)
    hitBaddy.hitPoints mustBe 3
  }

  test("a character with 0 or less hitpoints is dead") {
    val corpse = Character("corpse", Neutral, _hitPoints = 0)
    val splotch = Character("splotch", Neutral, _hitPoints = -1)

    corpse.dead mustBe true
    splotch.dead mustBe true
  }

  test("a character has abilities") {
    val unique = Character("unique", Neutral)
    unique.strength mustBe Ability()
    unique.dexterity mustBe Ability()
    unique.constitution mustBe Ability()
    unique.wisdom mustBe Ability()
    unique.intelligence mustBe Ability()
    unique.charisma mustBe Ability()
  }
}
