package com.dyercode.evercraft

case class Ability(value: Int = 10) {
  require(value > 0 && value < 21)

  def modifier: Int = Math.floorDiv(value - 10, 2)
}
