package shopping

import org.scalatest.{FlatSpec, _}

abstract class UnitSpec extends FlatSpec with Matchers with OptionValues with Inside with Inspectors

class CartTest extends UnitSpec {

  behavior of "Cart"

  it should "calculate the total cost" in {
    val catalogue = Map("Apple" -> 60, "Orange" -> 25)
    val cart = new Cart(catalogue)
    val total = cart.totalCost(List("Apple", "Orange"))
    assert(total === 85)
  }

  it should "calculate the total cost as string" in {
    val catalogue = Map("Apple" -> 60, "Orange" -> 25)
    val cart = new Cart(catalogue)
    val total = cart.totalCostStr(List("Apple", "Orange"))
    assert(total === "£0.85")
  }

  it should "calculate the total cost over £1" in {
    val catalogue = Map("Apple" -> 60, "Orange" -> 25)
    val cart = new Cart(catalogue)
    val total = cart.totalCostStr(List("Apple", "Apple"))
    assert(total === "£1.20")
  }

  it should "return zero if cart is empty" in {
    val catalogue = Map("Apple" -> 60, "Orange" -> 25)
    val cart = new Cart(catalogue)
    val total = cart.totalCost(List.empty)
    assert(total === 0)
  }

  it should "return zero price if cart is empty" in {
    val catalogue = Map("Apple" -> 60, "Orange" -> 25)
    val cart = new Cart(catalogue)
    val total = cart.totalCostStr(List.empty)
    assert(total === "£0.00")
  }

  it should "throw exception if unknown item in cart" in {
    val catalogue = Map("Apple" -> 60, "Orange" -> 25)
    val cart = new Cart(catalogue)
    assertThrows[RuntimeException]{
      cart.totalCost(List("Orange","Lemon"))
    }
  }

}
