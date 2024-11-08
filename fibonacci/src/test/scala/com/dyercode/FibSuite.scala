package com.dyercode.fib

import org.scalacheck.Gen
import org.scalacheck.Prop.forAll
import org.scalacheck.Prop.propBoolean
import org.scalacheck.Properties
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must
import org.scalatest.matchers.must.*

import scala.util.Random

class FibSuite extends AnyFunSuite with Matchers {
  test("first is the first seed") {
    val expected: Int = Random().nextInt()
    fib(1, expected) mustBe expected
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

class FibPropsTests extends Properties("Fib") {
  val posInts = Gen.choose(0, Int.MaxValue)

  property("first is the first seed") = forAll(posInts) { (n: Int) =>
    fib(1, n) == n
  }

  property("second is sum of seeds") = forAll(posInts, posInts) {
    (n: Int, m: Int) =>
      fib(2, n, m) == m
  }

  property("third is sum of seeds") = forAll(posInts, posInts) {
    (n: Int, m: Int) =>
      fib(3, n, m) == n + m
  }
}
