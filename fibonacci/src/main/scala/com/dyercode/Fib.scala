package com.dyercode.fib

import scala.annotation.tailrec

@tailrec
final def fib(n: Int, a: Int = 0, b: Int = 1): Int = n match {
  case 1 => a
  case 2 => b
  case _ => fib(n - 1, b, a + b)
}
