package com.dyercode.fizzbuzz

enum FB:
  case Fizz, Buzz, FizzBuzz

object FB {
  def apply(i: Int): FB | Int = i match {
    case x if isFizzBuzz(x) => FizzBuzz
    case x if isFizz(x)     => Fizz
    case x if isBuzz(x)     => Buzz
    case x                  => x
  }
}

def fizzBuzz(numbers: List[Int]): List[String] =
  numbers.map(FB(_).toString)

private def divisibleByOrContains(desired: Int)(underTest: Int) = {
  underTest.divisibleBy(desired) || underTest.contains(desired)
}

def isFizz: Int => Boolean = divisibleByOrContains(3)

def isBuzz: Int => Boolean = divisibleByOrContains(5)

def isFizzBuzz(n: Int): Boolean = isFizz(n) && isBuzz(n)

trait Containable[T]:
  extension (x: T) def contains(y: T): Boolean

given Containable[Int] with
  extension (x: Int)
    def contains(y: Int): Boolean = x.toString contains y.toString

trait Divisible[T]:
  extension (x: T) def divisibleBy(y: T): Boolean

given Divisible[Int] with
  extension (x: Int) def divisibleBy(y: Int): Boolean = x % y == 0
