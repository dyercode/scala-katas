package com.dyercode.leap

import org.scalatest.{MustMatchers, FunSuite}

class LeapYearSuite extends FunSuite with MustMatchers {

  def classifyYear(year: Int): YearClassification = {
    year match {
      case x if x % 400 == 0 => AtypicalLeap
      case x if x % 100 == 0 => AtypicalCommon
      case x if x % 4 == 0 => TypicalLeap
      case _ => TypicalCommon
    }
  }

  test("leap year must be divisible by 4") {
    classifyYear(4) must be (TypicalLeap)
  }

  test("a leap year must not be divisible by 100") {
    classifyYear(100) must be (AtypicalCommon)
  }

  test("unless it is divisible by 400") {
    classifyYear(400) must be (AtypicalLeap)
  }

  test("a year not divisble by 4 is a typical common year") {
    classifyYear(7) must be (TypicalCommon)
  }

}