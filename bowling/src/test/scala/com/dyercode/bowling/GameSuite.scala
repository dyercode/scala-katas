package com.dyercode.bowling

import org.scalacheck.Gen
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.*
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class GameSuite
    extends AnyFunSuite
    with Matchers
    with ScalaCheckPropertyChecks {

  test("when I bowl a frame and knock down some pins it must be recorded") {
    Game().bowl("1-").score mustBe 1
  }

  test("a perfect game must score 300") {
    Game("XXXXXXXXXXXX").score mustBe 300
  }

  test("spares score the next ball") {
    Game("-5-/").score mustBe 15
    Game("-5-/11").score mustBe 18
  }

  test("a gutter game is zero points") {
    Game("--------------------").score mustBe 0
  }

  test("impty game is 0") {
    Game("").score mustBe 0
  }

  test("it knocks down less than all is a straight sum") {
    def subZero: Int => String = {
      case 0 => "-"
      case x => x.toString
    }

    forAll(Gen.listOfN(20, Gen.choose(0, 4))) { (rs: List[Int]) =>
      Game(rs.map(subZero).mkString).score mustBe rs.sum
    }
  }
}
