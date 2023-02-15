trait Item
{
  var _id: Int
  var _name: String
  var _value: Int

  def id: Int = _id
  def name: String = _name
  def value: Int = _value

  def id_=(newId: Int): Unit = _id=newId
  def name_=(newName: String): Unit = _name=newName
  def value_=(newValue: Int): Unit = _value=newValue
}