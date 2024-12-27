import org.scalatest.funsuite.AnyFunSuite


class AST_ValueTest extends AnyFunSuite {
  val stack = new ScopeStack(new ScopeStackEmpty())
  stack.setVal("a", Some(1))
  test("AST value number") {
    assert( new AST_Number(1).eval(stack).contains(1))
    assert( new AST_Number(12).eval(stack).contains(12))
  }
  test("AST value variable") {
    assert(new AST_Variable("a").eval(stack).contains(1))
    assert(new AST_Variable("b").eval(stack).isEmpty)
  }

}
