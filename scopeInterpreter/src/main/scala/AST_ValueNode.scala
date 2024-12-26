trait AST_ValueNode {
  def eval(variables: ScopeStack): Option[Int]
}

class AST_Number(value: Int) extends AST_ValueNode {
  override def eval(variables: ScopeStack): Option[Int] = Some(value)
}

class AST_Variable(name: String) extends AST_ValueNode {
  override def eval(variables: ScopeStack): Option[Int] = {
    variables.getVal(name)
  }
}
