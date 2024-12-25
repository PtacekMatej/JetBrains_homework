import scala.collection.mutable

trait AST_Node {
  def eval(variables: ScopeStack): Unit
}

class AST_Scope extends AST_Node {
  private val children = mutable.ArrayBuffer[AST_Node]()
  override def eval(variables: ScopeStack): Unit = {
    val newStack = new ScopeStack(variables) //
    for(child <- children)
      child.eval(newStack)
  }

  def addChild(child: AST_Node): Unit = {
    children.appendAll(List(child))
  }
}

class AST_Assignment(name: String, newValue: Int) extends AST_Node {
  override def eval(variables: ScopeStack): Unit = {
    variables.setVal(name, newValue)
  }
}

class AST_Print(name: String) extends AST_Node {
  override def eval(variables: ScopeStack): Unit = {
    println(
      variables.getVal(name) match
        case Some(value) => value
        case None => "null"
    )
  }
}
