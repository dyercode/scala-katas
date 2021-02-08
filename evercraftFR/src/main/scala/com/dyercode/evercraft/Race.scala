package com.dyercode.evercraft

trait Raced[A] {
  extension (a: A) def race: Race
}

trait Race {
  def strengthModifier: Int = 0
  def constitutionModifier: Int = 0
  def intelligenceModifier: Int = 0
  def wisdomModifier: Int = 0
  def charismaModifier: Int = 0
  def acMod: Int = 0
  def hitPointModifier(character: Character): Int = 0
  def attackModifier[D: Raced](defender: D): Int = 0
  def extraDamage[D: Raced](defender: D): Int = 0
}

case object DefaultRace extends Race

case object Orc extends Race {
  override def strengthModifier: Int = 2
  override def intelligenceModifier: Int = -1
  override def wisdomModifier: Int = -1
  override def charismaModifier: Int = -1
  override def acMod: Int = 2
}


case object Dwarf extends Race {
  override def constitutionModifier: Int = 1
  override def charismaModifier: Int = -1
  override def hitPointModifier(character: Character): Int = character.constitutionModifier
  override def attackModifier[D : Raced](defender: D): Int = defender.race match {
    case Orc => 2
    case _ => 0
  }
  override def extraDamage[D: Raced](defender: D): Int = defender.race match {
    case Orc => 2
    case _ => 0
  }
}