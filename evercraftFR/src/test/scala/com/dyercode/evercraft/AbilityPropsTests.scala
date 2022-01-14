package com.dyercode.evercraft

import org.scalacheck.Prop.{forAll, propBoolean, throws}
import org.scalacheck.{Gen, Properties}

class AbilityPropsTests extends Properties("Ability") {
  val abilityGen: Gen[Int] = Gen.choose(1, 20)

  property("ability must be between 1 and 20") = forAll { (n: Int) =>
    n < 1 || n > 20 ==> throws(classOf[IllegalArgumentException])(Ability(n)) &&
    n >= 1 && n <= 20 ==> !throws(classOf[Exception])(Ability(n))
  }

  property("modifier always the same for the the same ability") =
    forAll(abilityGen) { (n: Int) =>
      Ability(n).modifier == Ability(n).modifier
    }

  property("10/11 are neutral modifiers") = forAll(abilityGen) { (n: Int) =>
    n match {
      case 10 => Ability(n).modifier == 0
      case 11 => Ability(n).modifier == 0
      case _  => Ability(n).modifier != 0
    }
  }

  property("modifier cannot go decrease when ability increases") =
    forAll(abilityGen, abilityGen) { (n: Int, o: Int) =>
      n != o ==> {
        if n > o then Ability(n).modifier >= Ability(o).modifier
        else Ability(n).modifier <= Ability(o).modifier
      }
    }

  property(
    "modifier goes only changes by 1 in one direction when ability changes by 1"
  ) = forAll(Gen.choose(1, 18)) { (n: Int) =>
    val lowModifier = Ability(n).modifier
    val highModifier = Ability(n + 2).modifier
    Math.abs(lowModifier - highModifier) == 1
  }
}
