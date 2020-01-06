package com.dyercode.evercraft

import com.dyercode.evercraft.Character._
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
    implicitly[Combatant[Character]].armorClass mustBe 10
  }

  test("as a combatant I want to have hitPoints") {
    implicitly[Combatant[Character]].hitPoints mustBe 5
  }

  test("roll must meet or beat opponent's armor class to hit") {
    val billy = Character(name = "Billy", alignment = Good)
    val baddy = Character(name = "Baddy", alignment = Good)

    // todo - an example of why this is probably not how I'm supposed to use these
    Combatant[Character].attack(11, baddy) mustBe Hit

    Combat.atk(billy, 11, baddy) mustBe Hit
    Combat.atk(billy, 10, baddy) mustBe Hit
    Combat.atk(billy, 9, baddy) mustBe Miss
  }
}
