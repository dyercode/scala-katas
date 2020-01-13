package com.dyercode.evercraft

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must
import com.dyercode.evercraft.Combatant._

class PlayerClassTest extends AnyFunSuite with must.Matchers {
  test("character's base hp is modified by class") {
    Character(name = "default", alignment = Neutral).hitPoints mustBe 5
    Character(name = "fighty", alignment = Neutral, playerClass = Fighter).hitPoints mustBe 10
    Character(
      name = "fighty",
      alignment = Neutral,
      playerClass = Fighter,
      xp = 1000
    ).hitPoints mustBe 20
  }
}
