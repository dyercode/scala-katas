package com.dyercode.fizzbuzz

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must

class FizzBuzzSuite extends AnyFunSuite with must.Matchers {
  val OneToOneHundredList: List[Int] = (1 to 100).toList

  test("fizzBuzz must replace all multiples of 3 with a word containing Fizz") {
    val fizzes = OneToOneHundredList filter (_ % 3 == 0)
    val fizzedList = fizzBuzz(OneToOneHundredList)
    fizzes foreach (x => fizzedList(x - 1) must include("Fizz"))
  }

  test(
    "fizzBuzz must replace all multiples of five with a word containing Buzz"
  ) {
    val buzzes = OneToOneHundredList filter (_ % 5 == 0)
    val buzzedList = fizzBuzz(OneToOneHundredList)
    buzzes foreach (x => buzzedList(x - 1) must include("Buzz"))
  }

  test("fizzBuzz must fizz all numbers that contain 3") {
    val fizzes = OneToOneHundredList filter (_.toString contains '3')
    val fizzedList = fizzBuzz(OneToOneHundredList)
    fizzes foreach (x => fizzedList(x - 1) must include("Fizz"))
  }

  test("fizzBuzz must buzz all numbers that contain 5") {
    val buzzes = OneToOneHundredList filter (_.toString contains '5')
    val buzzedList = fizzBuzz(OneToOneHundredList)
    buzzes foreach (x => buzzedList(x - 1) must include("Buzz"))
  }

  test("numbers with 3 and 5 must be fizzbuzz") {
    fizzBuzz(35 :: Nil) must be(List("FizzBuzz"))
  }

  test("numbers which are multiples 3 and 5 must be fizzbuzz") {
    fizzBuzz(60 :: Nil) must be(List("FizzBuzz"))
  }
}
