package shopping

class MultiPurchaseDiscountSpec extends UnitSpec {

  behavior of "MultiPurchaseDiscount"

  it should "calculate buy 1 get 1 free discount" in {
    val buyOneGetOneFree: MultiPurchaseDiscount = new MultiPurchaseDiscount(2)
    val discount = buyOneGetOneFree.apply(4, 20)
    assert(discount === 40)
  }

  it should "calculate buy 1 get 1 free discount for one item" in {
    val buyOneGetOneFree: MultiPurchaseDiscount = new MultiPurchaseDiscount(2)
    val discount = buyOneGetOneFree.apply(1, 20)
    assert(discount === 0)
  }

  it should "calculate 3 for 2 discount on 3 items" in {
    val threeForTwo: MultiPurchaseDiscount = new MultiPurchaseDiscount(3)
    val discount = threeForTwo.apply(3, 20)
    assert(discount === 20)
  }

  it should "calculate 3 for 2 discount on 4 items" in {
    val threeForTwo: MultiPurchaseDiscount = new MultiPurchaseDiscount(3)
    val discount = threeForTwo.apply(4, 20)
    assert(discount === 20)
  }


}
