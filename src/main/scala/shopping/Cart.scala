package shopping

trait MoneyFormatter {
  def poundFormat(pencePrice: Int): String = {
    "£" + String.valueOf(pencePrice / 100) + "." + padZeros(pencePrice % 100)
  }

  private def padZeros(i: Int): String = {
    String.valueOf(100 + i).substring(1)
  }
}

class Cart(catalogue: Map[String, Int], discounts: Map[String, MultiPurchaseDiscount]) extends MoneyFormatter {

  def this(catalogue: Map[String, Int]) {
    this(catalogue, Map.empty)
  }


  def totalCost(purchases: Seq[String]): Int = {
    val baseCost = purchases.map(catalogue.getOrElse(_, throw new RuntimeException("Unknown item in cart"))).sum
    val productCounts: Map[String, Int] = purchases.map(p => (p, purchases.count(_ == p))).toMap
    val discountSum = productCounts.map { e =>
      discounts.get(e._1).map { d =>
        d.apply(e._2, catalogue(e._1))
      }.getOrElse(0)
    }.sum
    baseCost - discountSum
  }

  def totalCostStr(products: Seq[String]): String = {
    poundFormat(totalCost(products))
  }

}

