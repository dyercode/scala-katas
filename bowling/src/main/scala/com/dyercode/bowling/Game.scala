package com.dyercode.bowling

import scala.util.matching.Regex

case class Game(val tries: String = "") {
  def bowl(pins: String): Game = {
    Game(pins + tries)
  }

  def splitIntoRounds(throws: String): List[List[Int]] = {
    type Pin = '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9'
    type Gutter = '-'
    type PinOrGutter = Pin | Gutter

    val scoreToInt = (c: Char) =>
      c match {
        case _: Gutter => 0
        case p: Pin    => p.asDigit
      }

    def inner(throws: List[Char]): List[List[Int]] = throws match {
      case Nil         => Nil
      case 'X' :: rest => List(10) :: inner(rest)
      case (a: PinOrGutter) :: '/' :: rest =>
        List(a.toInt, 10 - a.toInt) :: inner(rest)
      case (a: PinOrGutter) :: (b: PinOrGutter) :: rest =>
        List(a, b).map(scoreToInt) :: inner(rest)
    }

    inner(throws.toCharArray.toList)
  }

  def score: Int = {
    val frames: List[List[Int]] = splitIntoRounds(tries)

    def scoreStrikesAndSpares(
        remainingRounds: List[List[Int]]
    ): List[List[Int]] =
      remainingRounds match {
        case Nil         => Nil
        case head :: Nil => head :: Nil
        case List(10) :: tail =>
          List(10 + tail.flatten.take(2).sum) :: scoreStrikesAndSpares(tail)
        case List(a, b) :: tail if a + b == 10 =>
          List(10 + tail.flatten.take(1).sum) :: scoreStrikesAndSpares(tail)
        case head :: tail => head :: scoreStrikesAndSpares(tail)
      }

    scoreStrikesAndSpares(frames)
      .take(10)
      .flatten
      .sum

  }
}
