package shopping

class CartTest extends UnitSpec {

  behavior of "Cart"

  val catalogue = Map("Apple" -> 60, "Orange" -> 25)

  val threeForTwo = new MultiPurchaseDiscount(3)

  val oneFree = new MultiPurchaseDiscount(2)

  it should "calculate the total cost" in {
    val cart = new Cart(catalogue)
    val total = cart.totalCost(List("Apple", "Orange"))
    assert(total === 85)
  }

  it should "calculate the total cost as string" in {
    val cart = new Cart(catalogue)
    val total = cart.totalCostStr(List("Apple", "Orange"))
    assert(total === "£0.85")
  }

  it should "calculate the total cost over £1" in {
    val cart = new Cart(catalogue)
    val total = cart.totalCostStr(List("Apple", "Apple"))
    assert(total === "£1.20")
  }

  it should "return zero if cart is empty" in {
    val cart = new Cart(catalogue)
    val total = cart.totalCost(List.empty)
    assert(total === 0)
  }

  it should "return zero price if cart is empty" in {
    val cart = new Cart(catalogue)
    val total = cart.totalCostStr(List.empty)
    assert(total === "£0.00")
  }

  it should "throw exception if unknown item in cart" in {
    val cart = new Cart(catalogue)
    assertThrows[RuntimeException] {
      cart.totalCost(List("Orange", "Lemon"))
    }
  }

  it should "calculate the total cost after discounts" in {
    val discounts = Map("Apple" -> oneFree)
    val cart = new Cart(catalogue, discounts)
    val total = cart.totalCost(List("Apple", "Orange", "Apple"))
    assert(total === 85)
  }

  it should "calculate the total cost after 3 for 2 discounts" in {
    val discounts = Map("Orange" -> threeForTwo)
    val cart = new Cart(catalogue, discounts)
    val sevenOranges: Seq[String] = 1 to 7 map { _ => "Orange" }
    val total = cart.totalCost(sevenOranges)
    assert(total === (7 - 2) * 25)
  }

  it should "calculate the total cost after multiple discounts" in {
    val discounts = Map("Apple" -> oneFree, "Orange" -> threeForTwo)
    val cart = new Cart(catalogue, discounts)
    val sevenOranges: Seq[String] = 1 to 7 map { _ => "Orange" }
    val fourApples: Seq[String] = 1 to 4 map { _ => "Apple" }
    val total = cart.totalCost(sevenOranges ++ fourApples)
    assert(total === (7 - 2) * 25 + (4 - 2) * 60)
  }

  it should "calculate zero cost with discounts" in {
    val discounts = Map("Orange" -> threeForTwo)
    val cart = new Cart(catalogue, discounts)
    val total = cart.totalCost(List.empty)
    assert(total === 0)
  }

}
