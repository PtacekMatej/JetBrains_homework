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

class AST_Assignment(name: String, newValue: AST_ValueNode) extends AST_Node {
  override def eval(variables: ScopeStack): Unit = {
    variables.setVal(name, newValue.eval(variables))
  }
}

class AST_Print(name: String) extends AST_Node {
  override def eval(variables: ScopeStack): Unit = {
    println(
      variables.getVal(name).getOrElse("null")
    )
  }
}
