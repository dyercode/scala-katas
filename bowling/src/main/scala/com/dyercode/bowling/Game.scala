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
      case "" => Nil
      case strike(x) => List(10) :: splitIntoRounds(x)
      case spare(x, rest) => List(x.toInt, 10 - x.toInt) :: splitIntoRounds(rest)
      case normal(f1, f2, rest) => List(f1, f2).map(scoreToInt) :: splitIntoRounds(rest)
    }
  }

  def score: Int = {
    val frames: List[List[Int]] = splitIntoRounds(tries)

    def sumFrames(remainingRounds: List[List[Int]]): List[List[Int]] = {
      remainingRounds match {
        case Nil => Nil
        case head :: Nil => head :: Nil
        case head :: tail if head.head == 10 => List(10 + tail.flatten.take(2).sum) :: sumFrames(tail)
        case _ :: tail => sumFrames(tail)
      }
    }

    val frameSums = sumFrames(frames)

    frameSums.take(10).flatten.sum

  }
}

object Game {
  def apply(tries: String = ""): Game = {
    new Game(tries)
  }
}
