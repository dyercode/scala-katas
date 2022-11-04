package com.dyercode

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must

import scala.annotation.tailrec

class ChopSuite extends AnyFunSuite with must.Matchers {

  // Write a binary chop method that takes an integer search target and a sorted array of integers.
  // It should return the integer index of the target in the array, or -1 if the target is not in the array.
  // The signature will logically be:

  // chop(int, array_of_int)  -> int
  // chop(int, array_of_int): Option[Int] option better than magic number -1

  // You can assume that the array has less than 100,000 elements.
  // For the purposes of this Kata, time and memory performance are not issues
  // (assuming the chop terminates before you get bored and kill it, and that you have enough RAM to run it).

  def chop(i: Int, l: List[Int]): Option[Int] = {

    def findMid(s: Int): Int = (s / 2) - 1

    @tailrec def search(high: Int, low: Int = 0): Option[Int] = {
      if (low > high) None
      else {
        val mid = (low + high) / 2
        l(mid).compare(i) match {
          case 0 => Some(mid)
          case 1 => search(mid - 1, low)
          case -1 => search(high, mid + 1)
        }
      }
    }

    search(l.size - 1)
  }

  test("chop premade test very small lists") {
    chop(3, Nil) mustBe None
    chop(3, List(1)) mustBe None
    chop(1, List(1)) mustBe Some(0)

  }
  test("chop 1,3,5 tests with existence") {
    chop(1, List(1, 3, 5)) mustBe Some(0)
    chop(3, List(1, 3, 5)) mustBe Some(1)
    chop(5, List(1, 3, 5)) mustBe Some(2)
  }

  test("chop 1,3,5 tests without existence") {
    chop(0, List(1, 3, 5)) mustBe None
    chop(2, List(1, 3, 5)) mustBe None
    chop(4, List(1, 3, 5)) mustBe None
    chop(6, List(1, 3, 5)) mustBe None
  }

  test("chop 1,3,5,7 tests with existence") {
    chop(1, List(1, 3, 5, 7)) mustBe Some(0)
    chop(3, List(1, 3, 5, 7)) mustBe Some(1)
    chop(5, List(1, 3, 5, 7)) mustBe Some(2)
    chop(7, List(1, 3, 5, 7)) mustBe Some(3)
  }

  test("chop 1,3,5,7 tests without existence") {
    chop(0, List(1, 3, 5, 7)) mustBe None
    chop(2, List(1, 3, 5, 7)) mustBe None
    chop(4, List(1, 3, 5, 7)) mustBe None
    chop(6, List(1, 3, 5, 7)) mustBe None
    chop(8, List(1, 3, 5, 7)) mustBe None
  }
}
