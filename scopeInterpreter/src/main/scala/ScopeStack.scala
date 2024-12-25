import scala.collection.mutable

trait TScopeStack {
  def getVal(variableName: String): Option[Int]
}

class ScopeStackEmpty extends TScopeStack {
  override def getVal(variableName: String): Option[Int] = None
}

class ScopeStack(next: TScopeStack) extends TScopeStack {
  private val variables: mutable.Map[String, Int] = mutable.Map[String, Int]()
  override def getVal(variableName: String): Option[Int] = {
    val result = variables.get(variableName)
    result match
      case Some(_) => result
      case None => next.getVal(variableName)
  }
  def setVal(variableName: String, variableValue: Int): Unit = {
    variables.put(variableName, variableValue)
  }
}
