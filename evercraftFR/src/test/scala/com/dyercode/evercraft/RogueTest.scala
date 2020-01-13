package com.dyercode.evercraft

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must
import Combatant._

class RogueTest extends AnyFunSuite with must.Matchers {

  test("rogue crits do *3 damage") {
    Character(name = "Stabby", alignment = Neutral, playerClass = Rogue)
      .calculateDamage(ar = Crit) mustBe 3
  }
}
