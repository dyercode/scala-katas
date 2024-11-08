package com.dyercode.triangle

import io.github.iltotore.iron.*
import org.scalatest.matchers.must.*
import org.scalatest.wordspec.AnyWordSpec

import scala.util.Random

class TriangleSpec extends AnyWordSpec with Matchers {
  //  Can be joined into an equilateral triangle. (example: 3, 3, 3)
  //  Can be joined into an isosceles triangle. (example: 5, 5, 3);
  //  Can be joined into a right triangle. (example: 3, 4, 5);
  //  Can only(sic) be joined into a triangle that is not one of the preceding cases. (example: 2, 3, 4)
  //  Cannot be made into a triangle. (example: 2, 3, 5) [Warning: might look isosceles at first glance.]
  // var random: Random = null

  import Triangle._

  /*
  var order: List[Lengths => Int] = null

  override def beforeAll(): Unit = {
    order = Random.shuffle(
      List(
        (l: Lengths) => l._1,
        (l: Lengths) => l._2,
        (l: Lengths) => l._3
      )
    )
  }

  // there has got to be a less insane way to do this.
  // it doesn't scale, it swaps around types too many tims
  def shuffle(ls: Lengths): Lengths = {
    (
      order(0)(ls),
      order(1)(ls),
      order(2)(ls)
    )

  }

   */
  def shuffledLegs(a: Int, b: Int, c: Int): Legs = {
    val l: Lengths = Random.shuffle(List(a, b, c)).refineUnsafe
    Legs(l)
  }

  "A triangle" can {
    "be joined into an equilateral triangle" in {
      identify(Legs(3, 3, 3)) mustBe Equilateral
    }

    "be joined into an isosceles triangle" in {
      identify(shuffledLegs(3, 5, 5)) mustBe Isosceles
    }

    "be joined into a right triangle" in {
      identify(shuffledLegs(3, 4, 5)) mustBe Right
    }

    "be joined into an other triangle. (example: 2, 3, 5) [Warning: might look isosceles at first glance.]" in {
      identify(shuffledLegs(2, 3, 5)) mustBe Other
    }

    "not be physically impossible" in {
      identify(shuffledLegs(1, 1, 3)) mustBe IncompatibleSegments
    }
  }
}
