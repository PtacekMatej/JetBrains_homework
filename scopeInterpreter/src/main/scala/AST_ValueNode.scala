trait AST_ValueNode {
  /**
   * A method that evaluates the abstract syntax tree rooted in this node
   * @param variables
   * a ScopeStack of the variables used during the interpretation
   */
  def eval(variables: ScopeStack): Option[Int]
}

/**
 * an abstract syntax tree node representing a number value
 * @param value
 * the value of the node
 */
class AST_Number(value: Int) extends AST_ValueNode {
  override def eval(variables: ScopeStack): Option[Int] = Some(value)
}

/**
 * an abstract syntax tree node representing a variable
 * @param name
 * the name of the variable that this node represents
 */
class AST_Variable(name: String) extends AST_ValueNode {
  override def eval(variables: ScopeStack): Option[Int] = {
    variables.getVal(name)
  }
}
