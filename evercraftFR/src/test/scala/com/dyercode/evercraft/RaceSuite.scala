package com.dyercode.evercraft

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.must
import com.dyercode.evercraft.Character._
import com.dyercode.evercraft.AttackResult._

class RaceSpec extends AnyWordSpec with must.Matchers {


  //  As a player I want to play an Orc so that I can be crude, drunk, and stupid
  //  +2 to Strength Modifier, -1 to Intelligence, Wisdom, and Charisma Modifiers
  //  +2 to Armor Class due to thick hide
  "a player" when {
    val dwarf: Character = Character(name = "dorf", _alignment = Alignment.Neutral, race = Dwarf)
    val orc: Character = Character(name = "orclin", _alignment = Alignment.Neutral, race = Orc)
    "drunk and stupid plays an orc" should {
      "have +2 strength modifier" in {
        orc.strengthModifier mustBe 2
      }

      "have -1 to intelligence modifier" in {
        orc.intelligenceModifier mustBe -1
      }

      "have -1 to wisdom modifier" in {
        orc.wisdomModifier mustBe -1
      }

      "have -1 to charisma modifier" in {
        orc.charismaModifier mustBe -1
      }

      "has +2 armor class" in {
        orc.armorClass mustBe 12
      }
    }

    "drunker dwarfs do more boring race stuff" should {
      "+1 to Constitution Modifier" in {
        dwarf.constitutionModifier mustBe 1
      }
      "-1 to Charisma Modifier" in {
        dwarf.charismaModifier mustBe -1
      }

      "doubles Constitution Modifier when adding to hit points per level (if positive)" in {
        val pudgyDwarf = dwarf.copy(constitution = Ability(14))
        val thiccDwarf = dwarf.copy(constitution = Ability(18), xp = 3000)

        pudgyDwarf.hitPoints mustBe 11
        thiccDwarf.hitPoints mustBe 60
      }

      "+2 bonus to attack when attacking orcs(Dwarves hate Orcs)" in {
        dwarf.attackBonus(orc) mustBe 2
      }

      "+2 bonus to damage when attacking orcs(Dwarves hate Orcs)" in {
        dwarf.calculateDamage(Hit, orc) mustBe (dwarf.calculateDamage(Hit, dwarf) + 2)
        dwarf.calculateDamage(Crit, orc) mustBe (dwarf.calculateDamage(Crit, dwarf) + 4)
      }
    }
  }

}
