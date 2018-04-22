package com.dyercode.evercraft

sealed trait AttackResult

case object Miss extends AttackResult
case object Hit extends AttackResult
case object Critical extends AttackResult
