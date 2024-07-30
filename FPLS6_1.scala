val inventory1: Map[String, (String, Int, Double)] = Map(
    "001" -> ("Book", 10, 45),
    "002" -> ("Pen", 100, 30),
    "003" -> ("Pencil", 75, 20)
)

val inventory2: Map[String, (String, Int, Double)] = Map(
    "001" -> ("Book", 20, 44),
    "004" -> ("Eraser", 50, 10),
    "001" -> ("Book", 5, 60)
)

val productList = inventory1.values.map(_._1).toList

def mergeInventory(inventory1: Map[String, (String, Int, Double)], inventory2: Map[String, (String, Int, Double)]): Map[String, (String, Int, Double)] = {
  var mergedInventory = inventory1

  for ((id, (name, qty, price)) <- inventory2) {
    if (mergedInventory.contains(id)) {
      val (existingName, existingQty, existingPrice) = mergedInventory(id)
      mergedInventory += (id -> (name, existingQty + qty, math.max(existingPrice, price)))
    } else {
      mergedInventory += (id -> (name, qty, price))
    }
  }

  mergedInventory
}

def viewProduct(mergedInventory: Map[String, (String, Int, Double)], productId: String): Unit = {
    mergedInventory.get(productId) match {
      case Some((name, qty, price)) =>
        println(s"Product ID: $productId, Name: $name, Quantity: $qty, Price: $price")
      case None =>
        println(s"Product ID: $productId not found in the inventory.")
    }
}

def main(args: Array[String]) = {

    println("Product names from inventory1: ")
    for(i <- 0 to productList.length-1){
        println(productList(i))
    }

    val totalValue: Double = inventory1.values.map { case (_, quantity, price) => quantity * price }.sum
    println("\nTotal value of all products in inventory1 = " + totalValue)

    val result = productList.isEmpty
    if(result == true){
        println("\nInventory1 is empty.")
    }else{
        println("\nInventory1 is non empty.")
    }
    
    val mergedInventory = mergeInventory(inventory1, inventory2)
    println("\nMerged inventory: ")
    mergedInventory.foreach { case (id, (name, qty, price)) =>
        println(s"Product ID: $id, Name: $name, Quantity: $qty, Price: $price")
    }
    
    val productIdToCheck = "003"
    println(s"\nChecking if product ID $productIdToCheck exists in inventory1 and printing its details if it does:")
    viewProduct(inventory1, productIdToCheck)

}