package com.dyercode.evercraft

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must
import com.dyercode.evercraft.Alignment._
import Character._

class PlayerClassTest extends AnyFunSuite with must.Matchers {
  test("character's base hp can be modified by class") {
    Character(name = "default", _alignment = Neutral).hitPoints mustBe 5
    Character(name = "fighty", _alignment = Neutral, playerClass = Fighter).hitPoints mustBe 10
    Character(
      name = "fighty",
      _alignment = Neutral,
      playerClass = Fighter,
      xp = 1000
    ).hitPoints mustBe 20
  }

  test("class attack modifier per level can vary") {
    val baddy = Character(name = "billy", _alignment = Neutral)
    Character(
      name = "fighty",
      _alignment = Neutral,
      playerClass = Fighter,
      xp = 3000
    ).attackBonus(baddy) mustBe 4
  }
}
