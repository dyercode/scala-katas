package com.dyercode.evercraft

import org.scalacheck.Prop.{forAll, propBoolean, throws}
import org.scalacheck.Properties
import org.scalatest.matchers.must
import com.dyercode.evercraft.Character
import com.dyercode.evercraft.Paladin

class PaladinPropsTests extends Properties("Paladin") with must.Matchers {
  property("paladin attack bonus must be one per level") = forAll { (n: Int) =>
    n >= 1 && n <= 20 ==> {
      val pally = Character(
        name = "pally",
        xp = (n - 1) * 1000,
        _alignment = Alignment.Good,
        playerClass = Paladin)
      println(s"attack bonus: ${pally.attackBonus(pally)}, level: n")
      pally.attackBonus(pally) == n
    }
  }
}
