package com.dyercode.evercraft

case class Ability (score:Int = 10){
  require(score <= 20 && score >= 1)

  def modifier = (score / 2) - 5
}
