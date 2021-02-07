package com.dyercode.evercraft

import com.dyercode.evercraft.Alignment._
import com.dyercode.evercraft.AttackResult._
import com.dyercode.evercraft.Character._
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.must
import org.scalatest.prop.TableDrivenPropertyChecks

class MonkTest
    extends AnyFunSpec
    with must.Matchers
    with TableDrivenPropertyChecks {
  describe("A monk") {
    val monk: Character =
      Character(name = "Monky", _alignment = Neutral, playerClass = Monk)

    it("has 6 hp per level") {
      monk.hitPoints mustBe 6
    }
    it("does 3 points of damage instead of 1 when successfully attacking") {
      monk.calculateDamage(ar = Hit, defender = monk) mustBe 3
    }

    it("adds Wisdom to Armor Class in addition to Dexterity") {
      monk
        .copy(wisdom = Ability(14), dexterity = Ability(12))
        .armorClass mustBe 13
    }

    it("does not subtract wisdom penalty from ac") {
      monk
        .copy(wisdom = Ability(1))
        .armorClass mustBe 10
    }

    describe("attack roll") {
      val monkAttackBonus = Table(
        ("level", "xp", "attack bonus"),
        (1, 0, 0),
        (2, 1000, 1),
        (3, 2000, 2),
        (4, 3000, 3),
        (5, 4000, 3),
        (6, 5000, 4),
        (7, 6000, 4),
        (8, 7000, 5),
        (9, 8000, 6),
        (10, 9000, 7),
        (11, 10000, 7),
        (12, 11000, 8),
        (13, 12000, 8),
        (14, 13000, 9),
        (15, 14000, 10),
        (16, 15000, 11),
        (17, 16000, 11),
        (18, 17000, 12),
        (19, 18000, 12),
        (20, 19000, 13)
      )

      forAll(monkAttackBonus) { (level: Int, xp: Int, bonus: Int) =>
        it(s"is ${bonus} at level ${level}") {
          monk.copy(xp = xp).attackBonus(monk) mustBe bonus
        }
      }
    }
  }
}
