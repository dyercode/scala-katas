package com.dyercode.evercraft

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must

class AbilityTests extends AnyFunSuite with must.Matchers {

  test("ability defaults to 10") {
    Ability().stat mustBe 10
  }

  test("ability must be 1 or greater") {
    Ability(1)
    assertThrows[IllegalArgumentException] {
      Ability(0)
    }
  }

  test("ability must be 20 or lower") {
    Ability(20)
    assertThrows[IllegalArgumentException] {
      Ability(21)
    }
  }

}
