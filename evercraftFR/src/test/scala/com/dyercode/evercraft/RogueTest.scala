package com.dyercode.evercraft

import com.dyercode.evercraft.Alignment._
import com.dyercode.evercraft.AttackResult._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must

class RogueTest extends AnyFunSuite with must.Matchers {

  test("rogue crits do *3 damage") {
    Character(name = "Stabby", _alignment = Neutral, playerClass = Rogue)
      .calculateDamage(ar = Crit) mustBe 3
  }

  test("ignores target's dex bonus when attacking") {
    val stabby =
      Character(name = "Stabby", _alignment = Neutral, playerClass = Rogue)
    val stabee =
      Character(name = "stabee", _alignment = Neutral, dexterity = Ability(20))

    stabby.attack(10, stabee) mustBe Hit
  }

  test("honors targets dex penalty when attacking") {
    val stabby: Character =
      Character(name = "Stabby", _alignment = Neutral, playerClass = Rogue)
    val stabee: Character =
      Character(name = "stabee", _alignment = Neutral, dexterity = Ability(1))

    stabby.attack(5, stabee) mustBe Hit
  }

  test("adds dex mod to attack instead of strenth") {
    val dex = Ability(14)
    val stabby: Character =
      Character(
        name = "Stabby",
        _alignment = Neutral,
        playerClass = Rogue,
        dexterity = dex
      )

    stabby.attackBonus(Character("dunno", _alignment = Neutral)) mustBe dex.modifier

  }
}
