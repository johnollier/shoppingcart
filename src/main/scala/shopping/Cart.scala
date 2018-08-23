package shopping

trait MoneyFormatter {
  def poundFormat(pencePrice: Int): String = {
    "£" + String.valueOf(pencePrice / 100) + "." + padZeros(pencePrice % 100)
  }

  private def padZeros(i : Int): String = {
    String.valueOf( 100 + i ).substring(1)
  }
}

class Cart(catalogue: Map[String, Int]) extends MoneyFormatter {

  def totalCost(products: Seq[String]): Int = {
    products.map(catalogue.getOrElse(_, throw new RuntimeException("Unknown item in cart"))).sum
  }

  def totalCostStr(products: Seq[String]): String = {
    poundFormat(totalCost(products))
  }

}

