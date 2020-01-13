package com.dyercode.evercraft

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must
import Combatant._

class RogueTest extends AnyFunSuite with must.Matchers {

  test("rogue crits do *3 damage") {
    Character(name = "Stabby", alignment = Neutral, playerClass = Rogue)
      .calculateDamage(ar = Crit) mustBe 3
  }

  test("ignores target's dex bonus when attacking") {
    val stabby =
      Character(name = "Stabby", alignment = Neutral, playerClass = Rogue)
    val stabee =
      Character(name = "stabee", alignment = Neutral, dexterity = Ability(20))

    stabby.attack(10, stabee) mustBe Hit
  }

  test("honors targets dex penalty when attacking") {
    val stabby =
      Character(name = "Stabby", alignment = Neutral, playerClass = Rogue)
    val stabee =
      Character(name = "stabee", alignment = Neutral, dexterity = Ability(1))

    stabby.attack(5, stabee) mustBe Hit
  }

  test("adds dex mod to attack instead of strenth") {
    val dex = Ability(14)
    val stabby =
      Character(
        name = "Stabby",
        alignment = Neutral,
        playerClass = Rogue,
        dexterity = dex
      )

    stabby.attackBonus mustBe dex.modifier

  }
}
