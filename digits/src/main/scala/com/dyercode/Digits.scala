package com.dyercode.digits

import io.github.iltotore.iron.*
import io.github.iltotore.iron.constraint.numeric.*

object Digits {
  def toDigits(i: Int): Stream[Int] = i.toString.map(_.asDigit).toStream
  def ds(n: Int = 0): Stream[Int] = toDigits(n) #::: ds(n + 1)

  def digits(n: Int :| Positive): Int = ds().drop(n).head

}
