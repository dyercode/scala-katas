package com.dyercode.triangle
import io.github.iltotore.iron.*
import io.github.iltotore.iron.constraint.collection.*

type Lengths = List[Int] :| FixedLength[3]
case class Legs(a: Int, b: Int, c: Int)
object Legs {
  def apply(l: Lengths): Legs = {
    val List(a, b, c) = l
    Legs(a, b, c)
  }
}

enum Triangle {
  case Equilateral, Isosceles, Right, Other, IncompatibleSegments
}

private def isRight(a: Int, b: Int, c: Int): Boolean = {
  (a * a) + (b * b) == c * c
}

object Triangle {
  def identify(legs: Legs): Triangle = {
    val List(a, b, c) = List(legs.a, legs.b, legs.c).sorted
    (a, b, c) match
      case (a, _, c) if a == c           => Equilateral
      case (a, b, c) if isRight(a, b, c) => Right
      case (_, b, c) if b == c           => Isosceles
      case (a, b, c) if (a + b < c)      => IncompatibleSegments
      case _                             => Other
  }
}
