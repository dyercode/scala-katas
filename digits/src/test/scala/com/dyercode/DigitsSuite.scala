package com.dyercode.digits

import io.github.iltotore.iron.autoRefine
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.*

import Digits.{digits, ds}

class DigitsSuite extends AnyFunSuite with Matchers {

  test("hundredth is 5 they say") {
    digits(100) mustBe 5
  }

  test("starts right") {
    ds(1).take(11).toList mustBe List(1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 0)
  }

  test("make sure it's right for the right reason") {
    digits(1) mustBe 1
    digits(10) mustBe 1
    digits(11) mustBe 0
    digits(98) mustBe 5
    digits(99) mustBe 4
    digits(101) mustBe 5
    digits(102) mustBe 5
    digits(103) mustBe 6
  }
}
