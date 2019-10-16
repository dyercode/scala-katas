package com.dyercode

package object fizzbuzz {

  def fizzBuzz(numbers: List[Int]): List[String] = {
    numbers map {
      case x if isFizzBuzz(x) => "FizzBuzz"
      case x if isFizz(x)     => "Fizz"
      case x if isBuzz(x)     => "Buzz"
      case x                  => x.toString
    }
  }

  private def divisibleBy(n: Int, x: Int): Boolean = x % n == 0

  private def numberContains(n: Int, x: Int): Boolean =
    x.toString contains n.toString

  private def divisibleByOrContains(n: Int)(x: Int) =
    divisibleBy(n, x) || numberContains(n, x)

  def isFizz: Int => Boolean = divisibleByOrContains(3)

  def isBuzz: Int => Boolean = divisibleByOrContains(5)

  def isFizzBuzz(n: Int): Boolean = isFizz(n) && isBuzz(n)

}
