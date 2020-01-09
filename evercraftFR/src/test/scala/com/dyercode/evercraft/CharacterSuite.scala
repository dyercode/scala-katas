package com.dyercode.evercraft

import com.dyercode.evercraft.Character._
import com.dyercode.evercraft.Combatant._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must
import org.scalatest.prop.{TableDrivenPropertyChecks, TableFor2}

class CharacterSuite
    extends AnyFunSuite
    with must.Matchers
    with TableDrivenPropertyChecks {

  test("has a name and alignment") {
    val myDude = Character(name = "MyDude", alignment = Neutral)
    myDude.name mustBe "MyDude"
    myDude.alignment mustBe Neutral
  }

  test("can change to any of the alignments") {
    val billy = Character(name = "Billy", alignment = Good)
    val badBilly = changeAlignment(billy, Evil)
    val madBilly = changeAlignment(badBilly, Neutral)

    billy.alignment mustBe Good
    badBilly.alignment mustBe Evil
    madBilly.alignment mustBe Neutral
  }

  test("as a combatant I want to have armor class") {
    Character(name = "billy", alignment = Good).armorClass mustBe 10
  }

  test("as a combatant I want to have hitPoints") {
    Character(name = "billy", alignment = Good).hitPoints mustBe 5
  }

  test("roll must meet or beat opponent's armor class to hit") {
    val billy = Character(name = "Billy", alignment = Good)
    val baddy = Character(name = "Baddy", alignment = Evil)

    billy.attack(11, baddy) mustBe Hit
    billy.attack(10, baddy) mustBe Hit
    billy.attack(9, baddy) mustBe Miss
  }

  test(
    "if attack is successful, other character takes 1 point of damage when hit"
  ) {
    val baddy = Character(name = "Baddy", alignment = Good)
    val ar = baddy.attack(19, baddy)
    val damage = baddy.calculateDamage(ar)
    val hitBaddy = baddy.takeDamage(damage)
    hitBaddy.hitPoints mustBe 4
  }

  test(
    "if a roll is a natural 20 then a critical hit is dealt and damage is doubled"
  ) {
    val billy = Character(name = "Billy", alignment = Good)
    val baddy = Character(name = "Baddy", alignment = Good)
    val damage = billy.calculateDamage(Crit)
    val hitBaddy = baddy.takeDamage(damage)
    hitBaddy.hitPoints mustBe 3
  }

  test("a character with 0 or less hitpoints is dead") {
    val corpse = Character("corpse", Neutral, damage = 5)
    val splotch = Character("splotch", Neutral, damage = 6)

    corpse.dead mustBe true
    splotch.dead mustBe true
  }

  test("a character has abilities") {
    val unique = Character("unique", Neutral)
    unique.strength mustBe Ability()
    unique.dexterity mustBe Ability()
    unique.constitution mustBe Ability()
    unique.wisdom mustBe Ability()
    unique.intelligence mustBe Ability()
    unique.charisma mustBe Ability()
  }

  test("adds strength modifier to attack roll") {
    val billy =
      Character(name = "Billy", alignment = Good, strength = Ability(12))
    val baddy = Character(name = "Baddy", alignment = Evil)

    billy.attack(10, baddy) mustBe Hit
    billy.attack(9, baddy) mustBe Hit
    billy.attack(8, baddy) mustBe Miss
  }

  test("adds strength modifier to damage") {
    val billy =
      Character(name = "Billy", alignment = Good, strength = Ability(12))
    billy.calculateDamage(Hit) mustBe 2
  }

  test("strength modifier to damage is doubled on a crit") {
    val billy =
      Character(name = "Billy", alignment = Good, strength = Ability(12))
    billy.calculateDamage(Crit) mustBe 4
  }

  test("minimum damage is always 1") {
    val wimpy =
      Character(name = "Wimpy", alignment = Good, strength = Ability(1))
    wimpy.calculateDamage(Hit) mustBe 1
    wimpy.calculateDamage(Crit) mustBe 1
  }

  test("dexterity modifier is added to armor class") {
    val dodgy =
      Character(name = "Dodgy", alignment = Good, dexterity = Ability(12))
    dodgy.armorClass mustBe 11
  }

  test("constitution modifier is added to hitpoints") {
    val tuffboi =
      Character(name = "Tuffboi", alignment = Good, constitution = Ability(12))
    tuffboi.hitPoints mustBe 6
  }

  test("hp min is always 1 even with low con") {
    val frailboi =
      Character(name = "Frailboi", alignment = Good, constitution = Ability(1))
    frailboi.hitPoints mustBe 1
  }

  test("character can gain experience") {
    val learny = Character("Learny", Neutral)
    val learned = learny.gainXp(1)
    learned.xp mustBe 1
  }

  test("character has level") {
    val learny = Character("Learny", Neutral)
    learny.level mustBe 1
  }

  ignore("attacking adds 10xp") {}

  val xpLevel: TableFor2[Int, Int] = Table(
    ("xp", "level"),
    (0, 1),
    (999, 1),
    (1000, 2),
    (1999, 2),
    (2000, 3),
    (2999, 3),
    (3000, 4),
    (3999, 4),
    (4000, 5),
    (4999, 5),
    (5000, 6),
    (5999, 6),
    (6000, 7),
    (6999, 7),
    (7000, 8),
    (7999, 8),
    (8000, 9),
    (9990, 9),
    (9000, 10),
    (9999, 10),
    (10_000, 11),
    (10_999, 11),
    (11_000, 12),
    (11_999, 12),
    (12_000, 13),
    (12_999, 13),
    (13_000, 14),
    (13_999, 14),
    (14_000, 15),
    (14_999, 15),
    (15_000, 16),
    (15_999, 16),
    (16_000, 17),
    (16_999, 17),
    (17_000, 18),
    (17_999, 18),
    (18_000, 19),
    (18_999, 19),
    (19_000, 20)
  )

  forAll(xpLevel) { (xp: Int, level: Int) =>
    test(s"character is level $level at ${xp}xp") {
      val learny = Character("Learny", Neutral)
      val learned = learny.gainXp(1000)
      learned.level mustBe 2
      val moreLearned = learned.gainXp(1000)
      moreLearned.level mustBe 3
    }
  }

  test("hit points increase at each level") {
    val hpLevel = Table(
      ("hp", "level"),
      (14, 1000),
      (21, 2000),
      (28, 3000),
      (35, 4000),
      (42, 5000),
      (49, 6000),
      (56, 7000),
      (63, 8000),
      (70, 9000),
      (77, 10_000),
      (84, 11_000),
      (91, 12_000),
      (98, 13_000),
      (105, 14_000),
      (112, 15_000),
      (119, 16_000),
      (126, 17_000),
      (133, 18_000),
      (140, 19_000)
    )

    forAll(hpLevel) { (hp: Int, xp: Int) =>
      Character(
        name = "Healthy",
        alignment = Neutral,
        xp = xp,
        constitution = Ability(14)
      ).hitPoints mustBe hp
    }
  }

  test("each even level increases attack bonus by one") {
    val attackLvl = Table(
      ("level", "level"),
      (1, 1000),
      (1, 2000),
      (2, 3000),
      (2, 4000),
      (3, 5000),
      (3, 6000),
      (4, 7000),
      (4, 8000),
      (5, 9000),
      (5, 10_000),
      (6, 11_000),
      (6, 12_000),
      (7, 13_000),
      (7, 14_000),
      (8, 15_000),
      (8, 16_000),
      (9, 17_000),
      (9, 18_000),
      (10, 19_000)
    )

    forAll(attackLvl) { (attack: Int, xp: Int) =>
      val billy =
        Character(name = "Billy", alignment = Good, xp = xp)

      billy.attackBonus mustBe attack
    }
  }

}
