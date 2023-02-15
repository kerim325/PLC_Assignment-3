object MainApp {

  def readCSV(fileName: String): Seq[(Int, String, Int)] =
  {
    val src =  io.Source.fromFile(fileName)
    val ret = src.getLines().toSeq.tail.map{ line =>
      val Array(col1, col2, col3) = line.split(",").map(_.trim);
      (col1.toInt, col2, col3.toInt);
    }
    ret
  }

  def main(args: Array[String]): Unit =
  {
    val seq = readCSV("src\\data.csv")
    val database: Database = new Database(seq)

    println("--- SUM UP ---")
    println(database.sumUp())

    println("--- FILTERED BY ASUS ---")
    val filteredByASUS = database.filterByName("ASUS", database.getStoredItems)
    filteredByASUS.foreach(temp => println(temp.name))
    println(filteredByASUS.length)

    println("--- FILTERED BY Lenovo ---")
    val filteredByLenovo = database.filterByName("Lenovo", database.getStoredItems)
    filteredByLenovo.foreach(temp => println(temp.name))
    println(filteredByLenovo.length)

    println("--- FILTERED BY HP ---")
    val filteredByHP = database.filterByName("HP", database.getStoredItems)
    filteredByHP.foreach(temp => println(temp.name))
    println(filteredByHP.length)

    println("--- SORTED ITEMS ---")
    val sortedItemsDescending = database.sortByValueDesc()
    sortedItemsDescending.foreach(temp => println(temp.name + " " + temp.value))

  }
}