package com.dyercode

import org.scalatest.{MustMatchers, WordSpec}

class TriangleSpec extends WordSpec with MustMatchers {
  //  Can be joined into an equilateral triangle. (example: 3, 3, 3)
  //  Can be joined into an isosceles triangle. (example: 5, 5, 3);
  //  Can be joined into a right triangle. (example: 3, 4, 5);
  //  Can only be joined into a triangle that is not one of the preceding cases. (example: 2, 3, 4)
  //  Cannot be made into a triangle. (example: 2, 3, 5) [Warning: might look isosceles at first glance.]

  "A triangle" when {
    "which is equilateral" can {
      "be identified as such" in {
        identify(List(3, 3, 3)) mustBe Equilateral
      }
    }
  }

  def identify(sides: List[Int]): Triangle = {
    Equilateral
  }

  sealed trait Triangle

  final case object Equilateral extends Triangle

}

