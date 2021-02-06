package com.dyercode.evercraft

enum Alignment:
  case Good, Neutral, Evil

trait Aligned[A]:
  extension (a: A) def alignment: Alignment
