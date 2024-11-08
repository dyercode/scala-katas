package com.dyercode.chop

import scala.annotation.tailrec

enum Chop {
  case NotFound
  case Index(index: Int)
}

def chop(i: Int, l: List[Int]): Chop = {
  @tailrec def search(high: Int, low: Int = 0): Chop = {
    if (low > high) Chop.NotFound
    else {
      val mid = (low + high) / 2
      l(mid).compare(i) match {
        case 0  => Chop.Index(mid)
        case 1  => search(mid - 1, low)
        case -1 => search(high, mid + 1)
      }
    }
  }

  search(l.size - 1)
}
