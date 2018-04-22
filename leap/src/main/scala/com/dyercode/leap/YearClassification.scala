package com.dyercode.leap

sealed trait YearClassification

case object TypicalLeap extends YearClassification
case object AtypicalLeap extends YearClassification
case object AtypicalCommon extends YearClassification
case object TypicalCommon extends YearClassification
