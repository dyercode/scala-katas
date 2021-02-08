package com.dyercode.evercraft

import org.scalacheck.Prop.{forAll, propBoolean, throws}
import org.scalacheck.Properties
import org.scalacheck.Gen
import org.scalatest.matchers.must
import com.dyercode.evercraft.Character
import com.dyercode.evercraft.Paladin

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
