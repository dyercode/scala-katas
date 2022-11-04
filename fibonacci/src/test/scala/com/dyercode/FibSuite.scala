package com.dyercode

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must

import scala.annotation.tailrec

class FibSuite extends AnyFunSuite with must.Matchers {

  @tailrec
  final def fib(n: Int, a: Int = 0, b: Int = 1): Int = n match {
    case 1 => a
    case 2 => b
    case _ => fib(n - 1, b, a + b)
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
