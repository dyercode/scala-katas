package com.dyercode.evercraft

import com.dyercode.evercraft.Character._
import com.dyercode.evercraft.Alignment._
import com.dyercode.evercraft.AttackResult._
import org.scalatest.matchers.must
import org.scalatest.wordspec.AnyWordSpec

// can (should) in
class PaladinTest extends AnyWordSpec with must.Matchers {
  "A Paladin" when {
    val pally =
      Character(name = "Pally", _alignment = Good, playerClass = Paladin)
    "fighting an evil character" must {
      val baddy: Character =
        Character(name = "Baddy", _alignment = Evil)

      "add 2 to attack" in {
        pally.attack(8, baddy) mustBe Hit
      }

      "add 2 to damage" in {
        pally.calculateDamage(Hit)
      }

      "must be good" in {
        an[IllegalArgumentException] must be thrownBy {
          Character(name = "NeutPally", _alignment = Neutral, playerClass = Paladin)
        }
        an[IllegalArgumentException] must be thrownBy {
          Character(name = "BadPally", _alignment = Evil, playerClass = Paladin)
        }
      }
    }
  }
}
