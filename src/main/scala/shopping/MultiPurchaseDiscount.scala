package shopping

/**
  * A discount on multiple purchases of a product.
  * You must buy a 'qualifying' number of products in order
  * to get the discount
  */
class MultiPurchaseDiscount(qualifying: Int) {

  /**
    * Calculates the amount of the discount. For each qualifying number
    * of purchases the discount is the basic price of the product.
    */
  def apply(purchaseCount: Int, price: Int): Int = {
      purchaseCount / qualifying * price
    }
}
