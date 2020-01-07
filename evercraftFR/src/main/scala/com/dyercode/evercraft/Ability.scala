package com.dyercode.evercraft

case class Ability(stat: Int = 10) {
  require(stat > 0 && stat < 21)
}
