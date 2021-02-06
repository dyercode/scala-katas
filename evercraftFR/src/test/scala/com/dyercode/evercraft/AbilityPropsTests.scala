package com.dyercode.evercraft

import org.scalacheck.Prop.{forAll, propBoolean, throws}
import org.scalacheck.Properties
import org.scalatest.matchers.must

class AbilityPropsTests extends Properties("Ability") with must.Matchers {
  /*
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
   */

  property("ability must be between 1 and 20") = forAll { (n: Int) =>
    n < 1 || n > 20 ==> throws(classOf[IllegalArgumentException])(Ability(n)) &&
    n >= 1 && n <= 20 ==> !throws(classOf[Exception])(
      Ability(n)
    )
  }

//  forAll(statModifiers) { (value: Int, modifier: Int) =>
//    test(s"ability modifier for $value is $modifier") {
//      Ability(value).modifier mustEqual modifier
//    }
//  }

}
