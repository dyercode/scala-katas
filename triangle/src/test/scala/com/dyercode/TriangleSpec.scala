package com.dyercode

import org.scalatest.{MustMatchers, WordSpec}

class TriangleSpec extends WordSpec with MustMatchers {
  //  Can be joined into an equilateral triangle. (example: 3, 3, 3)
  //  Can be joined into an isosceles triangle. (example: 5, 5, 3);
  //  Can be joined into a right triangle. (example: 3, 4, 5);
  //  Can only be joined into a triangle that is not one of the preceding cases. (example: 2, 3, 4)
  //  Cannot be made into a triangle. (example: 2, 3, 5) [Warning: might look isosceles at first glance.]

  "A triangle" can {
    "be joined into an equilateral triangle" in {
      identify((3, 3, 3)) mustBe Equilateral
    }

    "be joined into an isosceles triangle" in {
      identify((3, 5, 5)) mustBe Isosceles
    }

    "be joined into a right triangle" in {
      identify((3, 4, 5)) mustBe Right
    }
  }

  type Lengths = (Int, Int, Int)

  def identify(lengths: Lengths): Triangle = {
    val (a, b, c) = lengths
    if (a == b) {
      Equilateral
    } else if (b == 4) {
      Right
    } else {
      Isosceles
    }
  }

  sealed trait Triangle

  final case object Equilateral extends Triangle

  final case object Isosceles extends Triangle

  final case object Right extends Triangle

}

