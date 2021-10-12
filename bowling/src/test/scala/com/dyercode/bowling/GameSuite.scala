package com.dyercode.bowling

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must

class GameSuite extends AnyFunSuite with must.Matchers {

  test("when I bowl a frame and knock down some pins it must be recorded") {
    Game().bowl("1-").score mustBe 1
  }

  test("a perfect game must score 300") {
    Game("XXXXXXXXXXXX").score mustBe 300
  }

  test("a gutter game is zero points") {
    Game("--------------------").score mustBe 0
  }

  test("a spare scores adds the next roll") {
    Game("1/-5").score mustBe 15
    Game("1/5-").score mustBe 20
  }

  test("game with non-special number of pins hit is straightforward") {
    Game("11111111111111111111").score mustBe 20
  }
}
