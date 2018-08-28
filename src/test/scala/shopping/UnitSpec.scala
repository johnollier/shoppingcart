package shopping

import org.scalatest.{FlatSpec, _}


/**
  * Convenience super class incorporating various Scala Test traits
  */
abstract class UnitSpec extends FlatSpec with Matchers with OptionValues with Inside with Inspectors
