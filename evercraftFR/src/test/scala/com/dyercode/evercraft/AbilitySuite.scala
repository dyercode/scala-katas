package com.dyercode.evercraft

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must

class AbilitySuite extends AnyFunSuite with must.Matchers {

  test("ability defaults to 10") {
    Ability().stat mustBe 10
  }

}
