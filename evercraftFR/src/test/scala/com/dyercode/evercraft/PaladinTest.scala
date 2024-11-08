package com.dyercode.evercraft
import com.dyercode.evercraft.Alignment.*
import com.dyercode.evercraft.AttackResult.*
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
        val nonEvilDamage = pally.calculateDamage(Hit, baddy.copy(_alignment = Neutral))
        pally.calculateDamage(Hit, baddy) mustBe nonEvilDamage + 2
      }

      "do triple damage on a crit" in {
        pally.calculateDamage(Crit, baddy) mustBe (pally.calculateDamage(Crit, pally) * 1.5 + 6)
      }
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
