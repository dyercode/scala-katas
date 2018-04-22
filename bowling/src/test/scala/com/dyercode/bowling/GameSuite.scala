package com.dyercode.bowling

import org.scalatest.{FunSuite, MustMatchers}

class GameSuite extends FunSuite with MustMatchers {

  test("when I bowl a frame and knock down some pins it must be recorded") {
    Game().bowl("1-").score mustBe 1
  }

  test("a perfect game must score 300") {
    Game("XXXXXXXXXXXX").score mustBe 300
  }

  test("a gutter game is zero points") {
    Game("--------------------").score mustBe 0
  }
}
