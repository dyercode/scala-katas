package com.dyercode.evercraft

import org.scalacheck.Gen
import org.scalacheck.Prop.forAll
import org.scalacheck.Prop.propBoolean
import org.scalacheck.Properties
import org.scalatest.matchers.must

class PaladinPropsTests extends Properties("Paladin") with must.Matchers {
  def makePaladin(lvl: Int): Character = {
    Character(
      name = "pally",
      xp = (lvl - 1) * 1000,
      _alignment = Alignment.Good,
      playerClass = Paladin)
  }

  property("paladin attack bonus must be one per level") = forAll(Gen.choose(1,20)) { (n: Int) =>
      val pally = makePaladin(n)
      pally.attackBonus(pally) == n
  }
}
