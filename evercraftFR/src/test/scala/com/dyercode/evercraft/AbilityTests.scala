package com.dyercode.evercraft

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must
import org.scalatest.prop.{TableDrivenPropertyChecks, TableFor2}

class AbilityTests
    extends AnyFunSuite
    with must.Matchers
    with TableDrivenPropertyChecks {
  val statModifiers: TableFor2[Int, Int] = Table(
    ("value", "modifier"),
    (1, -5),
    (2, -4),
    (3, -4),
    (4, -3),
    (5, -3),
    (6, -2),
    (7, -2),
    (8, -1),
    (9, -1),
    (10, 0),
    (11, 0),
    (12, 1),
    (13, 1),
    (14, 2),
    (15, 2),
    (16, 3),
    (17, 3),
    (18, 4),
    (19, 4),
    (20, 5)
  )

  test("ability defaults to 10") {
    Ability().value mustBe 10
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

  forAll(statModifiers) { (value: Int, modifier: Int) =>
    test(s"ability modifier for $value is $modifier") {
      Ability(value).modifier mustEqual modifier
    }
  }

}
