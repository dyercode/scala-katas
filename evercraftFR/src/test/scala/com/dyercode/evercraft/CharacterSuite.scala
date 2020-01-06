package com.dyercode.evercraft

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must
import org.scalatest.{BeforeAndAfter, OneInstancePerTest}

class CharacterSuite
    extends AnyFunSuite
    with must.Matchers
    with BeforeAndAfter
    with OneInstancePerTest {

  test("has a name") {
    Character(name = "MyDude")
  }

}
