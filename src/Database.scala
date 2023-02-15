import scala.collection.mutable

class Database(items: Seq[(Int, String, Int)] = Seq.empty[(Int, String, Int)]) extends ShoppingCart
{
  private var _storedItems: mutable.Buffer[StoreItem] = mutable.Buffer.empty[StoreItem]

  items.foreach(temp => this.store(new StoreItem(temp._1, temp._2, temp._3)))

  def getStoredItems: Array[StoreItem] = _storedItems.toArray
  def storedItems: mutable.Buffer[StoreItem] = _storedItems

  override def delete(id: Int): Array[StoreItem] =
  {
    val temp = storedItems.find(_.id == id)
    if(temp.isDefined)
      storedItems -= temp.get
    storedItems.toArray
  }

  override def search(name: String): Array[StoreItem] =
  {
    val ret = storedItems.filter(_.name.equals(name))
    if(ret.nonEmpty)
      ret.head.logAction("found", name)
    else
      println(name+" not found")

    ret.toArray[StoreItem]
  }

  override def sortByValueAsc(): Array[StoreItem] = storedItems.toArray.sortWith(_.value<_.value)

  override def sortByValueDesc(): Array[StoreItem] = storedItems.toArray.sortWith(_.value>_.value)

  override def store(item: StoreItem): Array[StoreItem] =
  {
    storedItems += item
    item.logAction("stored", item.name)
    storedItems.toArray
  }

  override def sumUp(): Int = storedItems.map(_.value).sum

  def filterByName(name: String, items: Array[StoreItem]): Array[StoreItem] = items.filter(_.name.contains(name)).sortWith(_.value<_.value)

}