package com.dyercode

import org.scalatest.{FunSuite, MustMatchers}

class FibSuite extends FunSuite with MustMatchers {

  final def fib(n: Int): Int = n match {
    case 1 => 0
    case 2 => 1
    case _ => fib(n - 1) + fib(n - 2)
  }

  test("third is 1") {
    fib(3) mustBe 1
  }

  test("fourth is 2") {
    fib(4) mustBe 2
  }

  test("fifth is 3") {
    fib(5) mustBe 3
  }

  test("sixth is 5") {
    fib(6) mustBe 5
  }
}
