package com.dyercode.bowling

import scala.util.matching.Regex

class Game(val tries: String = "") {
  def bowl(pins: String): Game = {
    Game(pins + tries)
  }

  def splitIntoRounds(throws: String): List[List[Int]] = {
    val strike: Regex = "X(.*)".r
    val pinsOrGutter = "([1-9]|-)"
    val spare = s"$pinsOrGutter/(.*)".r
    val normal = s"$pinsOrGutter$pinsOrGutter(.*)".r

    def scoreToInt(s: String) = {
      if (s == "-") 0 else s.toInt
    }

    throws match {
      case ""        => Nil
      case strike(x) => List(10) :: splitIntoRounds(x)
      case spare(x, rest) =>
        val s = scoreToInt(x)
        List(s, 10 - s) :: splitIntoRounds(rest)
      case normal(f1, f2, rest) =>
        List(f1, f2).map(scoreToInt) :: splitIntoRounds(rest)
    }
  }

  def score: Int = {
    val frames: List[List[Int]] = splitIntoRounds(tries)

    def sumFrames(remainingRounds: List[List[Int]]): List[List[Int]] = {
      remainingRounds match {
        case Nil         => Nil
        case head :: Nil => head :: Nil
        case (10 :: _) :: tail =>
          List(10 + tail.flatten.take(2).sum) :: sumFrames(tail)
        case List(a, b) :: tail if a + b == 10 =>
          List(10 + tail.flatten.headOption.getOrElse(0)) :: sumFrames(tail)
        case a :: tail => a :: sumFrames(tail)
      }
    }

    val frameSums = sumFrames(frames)

    frameSums.take(10).flatten.sum
  }
}
