import scala.collection.mutable

trait TScopeStack {
  def getVal(variableName: String): Option[Int]
}

class ScopeStackEmpty extends TScopeStack {
  override def getVal(variableName: String): Option[Int] = None
}

class ScopeStack(next: TScopeStack) extends TScopeStack {
  private val variables: mutable.Map[String, Option[Int]] = mutable.Map[String, Option[Int]]()
  override def getVal(variableName: String): Option[Int] = {
    val result = variables.get(variableName)
    result.getOrElse(next.getVal(variableName))
  }
  def setVal(variableName: String, variableValue: Option[Int]): Unit = {
    variables.put(variableName, variableValue)
  }
}
