package com.dyercode.evercraft

import org.scalatest.{FunSuite, MustMatchers, OneInstancePerTest}

class AbilitySuite extends FunSuite with MustMatchers with OneInstancePerTest {
  var ability: Ability = _

  val abilityModifierChart = Map[Int, Int](
    1 -> -5,
    2 -> -4,
    3 -> -4,
    4 -> -3,
    5 -> -3,
    6 -> -2,
    7 -> -2,
    8 -> -1,
    9 -> -1,
    10 -> 0,
    11 -> 0,
    12 -> 1,
    13 -> 1,
    14 -> 2,
    15 -> 2,
    16 -> 3,
    17 -> 3,
    18 -> 4,
    19 -> 4,
    20 -> 5
  )

  test("score must default to 10") {
    val ability = Ability()
    ability.score must be (10)
  }

  test("score must be able to be set as low as 1") {
    val ability = Ability(1)
    assert(ability.score === 1)
  }

  test("score must not be lower than 1") {
    intercept[IllegalArgumentException] {
      Ability(0)
    }
  }

  test("score must be able to be set as high as 20") {
    val ability = Ability(20)
    assert(ability.score === 20)
  }

  test("score must not be higher than 20") {
    intercept[IllegalArgumentException] {
      Ability(21)
    }
  }

  test("modifier must give the proper value for a given ability score") {
    abilityModifierChart foreach {
      case (score: Int, value: Int) => {
        assert(Ability(score).modifier === value)
      }
    }
  }
}
