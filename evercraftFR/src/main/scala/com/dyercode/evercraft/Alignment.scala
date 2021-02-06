package com.dyercode.evercraft

sealed trait Alignment

case object Good extends Alignment
case object Neutral extends Alignment
case object Evil extends Alignment

trait Aligned[A] {
  def alignment(a: A): Alignment
}

object Aligned {
  implicit class AlignedOps[A](a: A)(implicit al: Aligned[A]) {
    def alignment: Alignment = al.alignment(a)
  }
}
