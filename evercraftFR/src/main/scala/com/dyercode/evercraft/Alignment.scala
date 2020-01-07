package com.dyercode.evercraft

sealed trait Alignment

case object Good extends Alignment
case object Neutral extends Alignment
case object Evil extends Alignment
