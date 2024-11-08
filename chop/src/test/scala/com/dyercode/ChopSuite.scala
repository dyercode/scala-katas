package com.dyercode.chop

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must

class ChopSuite extends AnyFunSuite with must.Matchers {

  // Write a binary chop method that takes an integer search target and a sorted array of integers.
  // It should return the integer index of the target in the array, or -1 if the target is not in the array.
  // The signature will logically be:

  // chop(int, array_of_int)  -> int

  // You can assume that the array has less than 100,000 elements.
  // For the purposes of this Kata, time and memory performance are not issues
  // (assuming the chop terminates before you get bored and kill it, and that you have enough RAM to run it).

  import Chop._
  test("chop premade test very small lists") {
    chop(3, Nil) mustBe NotFound
    chop(3, List(1)) mustBe NotFound
    chop(1, List(1)) mustBe Index(0)

  }
  test("chop 1,3,5 tests with existence") {
    chop(1, List(1, 3, 5)) mustBe Index(0)
    chop(3, List(1, 3, 5)) mustBe Index(1)
    chop(5, List(1, 3, 5)) mustBe Index(2)
  }

  test("chop 1,3,5 tests without existence") {
    chop(0, List(1, 3, 5)) mustBe NotFound
    chop(2, List(1, 3, 5)) mustBe NotFound
    chop(4, List(1, 3, 5)) mustBe NotFound
    chop(6, List(1, 3, 5)) mustBe NotFound
  }

  test("chop 1,3,5,7 tests with existence") {
    chop(1, List(1, 3, 5, 7)) mustBe Index(0)
    chop(3, List(1, 3, 5, 7)) mustBe Index(1)
    chop(5, List(1, 3, 5, 7)) mustBe Index(2)
    chop(7, List(1, 3, 5, 7)) mustBe Index(3)
  }

  test("chop 1,3,5,7 tests without existence") {
    chop(0, List(1, 3, 5, 7)) mustBe NotFound
    chop(2, List(1, 3, 5, 7)) mustBe NotFound
    chop(4, List(1, 3, 5, 7)) mustBe NotFound
    chop(6, List(1, 3, 5, 7)) mustBe NotFound
    chop(8, List(1, 3, 5, 7)) mustBe NotFound
  }
}
