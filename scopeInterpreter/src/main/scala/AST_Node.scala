import scala.collection.mutable

trait AST_Node {
  /**
   * A method that evaluates the abstract syntax tree rooted in this node
   * @param variables
   * a ScopeStack of the variables used during the interpretation
   */
  def eval(variables: ScopeStack): Unit
}


/**
 * an abstract syntax tree node used to represent a scope
 */
class AST_Scope extends AST_Node {
  private val children = mutable.ArrayBuffer[AST_Node]()
  override def eval(variables: ScopeStack): Unit = {
    val newStack = new ScopeStack(variables) //
    for(child <- children)
      child.eval(newStack)
  }

  /**
   * a method used for nodes into this scope
   * @param child
   * the node that is to be added as a child to this node
   */
  def addChild(child: AST_Node): Unit = {
    children.appendAll(List(child))
  }
}


/**
 * an abstract syntax tree node used to represent an assignment into a variable
 * @param name
 * name of the variable that is being assigned to
 * @param newValue
 * an abstract syntax tree value node representing the new value of the variable
 */
class AST_Assignment(name: String, newValue: AST_ValueNode) extends AST_Node {
  override def eval(variables: ScopeStack): Unit = {
    variables.setVal(name, newValue.eval(variables))
  }
}

/**
 * an abstract syntax tree node used to represent printing a variable
 * @param name
 * the name of the variable that is being printed
 */
class AST_Print(name: String) extends AST_Node {
  override def eval(variables: ScopeStack): Unit = {
    println(
      variables.getVal(name).getOrElse("null")
    )
  }
}
