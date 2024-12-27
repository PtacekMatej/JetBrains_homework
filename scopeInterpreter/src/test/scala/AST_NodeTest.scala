import org.scalatest.funsuite.AnyFunSuite


class AST_NodeTest extends AnyFunSuite {
  test("AST Assignment") {
    val stack = new ScopeStack(new ScopeStackEmpty())
    new AST_Assignment("a", AST_Number(1)).eval(stack)
    assert(stack.getVal("a").contains(1))
    assert(stack.getVal("b").isEmpty)
    new AST_Assignment("b", AST_Variable("a")).eval(stack)
    assert(stack.getVal("a").contains(1))
    assert(stack.getVal("b").contains(1))
    new AST_Assignment("b", AST_Variable("c")).eval(stack)
    assert(stack.getVal("a").contains(1))
    assert(stack.getVal("b").isEmpty)
  }
  test("AST Scope") {
    val stack = new ScopeStack(new ScopeStackEmpty())
    stack.setVal("a", Some(1))
    val scope = new AST_Scope()
    scope.addChild(
      new AST_Assignment("a", AST_Number(2))
    )
    scope.addChild(
      new AST_Assignment("b", AST_Number(1))
    )
    scope.eval(stack)


    assert(stack.getVal("a").contains(1))
    assert(stack.getVal("b").isEmpty)
  }
}
