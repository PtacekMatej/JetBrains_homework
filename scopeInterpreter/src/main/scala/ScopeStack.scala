import scala.collection.mutable

trait TScopeStack {
  /**
   * a method used to getting the value of a variable
   * @param variableName
   * the name of the variable
   * @return
   * optional value of the variable, None, if the value had not been set
   */
  def getVal(variableName: String): Option[Int]
}

class ScopeStackEmpty extends TScopeStack {
  override def getVal(variableName: String): Option[Int] = None
}

trait TScopeStackFull extends TScopeStack {
  /**
   * a method used to set the variable value
   * @param variableName
   * the name of the variable
   * @param variableValue
   * the new value for the variable
   */
  def setVal(variableName: String, variableValue: Option[Int]): Unit
}

class ScopeStack(next: TScopeStack) extends TScopeStackFull {
  private val variables: mutable.Map[String, Option[Int]] = mutable.Map[String, Option[Int]]()
  override def getVal(variableName: String): Option[Int] = {
    val result = variables.get(variableName)
    result.getOrElse(next.getVal(variableName))
  }
  override def setVal(variableName: String, variableValue: Option[Int]): Unit = {
    variables.put(variableName, variableValue)
  }
}
