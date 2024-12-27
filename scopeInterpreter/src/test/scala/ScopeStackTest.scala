import org.scalatest.funsuite.AnyFunSuite


class ScopeStackTest extends AnyFunSuite {
  val stackEmpty = new ScopeStackEmpty()
  test("Empty Stack"){
    assert(stackEmpty.getVal("abc").isEmpty)
    assert(stackEmpty.getVal("").isEmpty)
    assert(stackEmpty.getVal(" ").isEmpty)
  }
  def stackTest(stack: TScopeStack, depth: Int): Unit = {
    if(depth < 500) {
      val newStack = new ScopeStack(stack)
      newStack.setVal("abc", Some(depth))
      assert(newStack.getVal("abc").contains(depth))
      stackTest(newStack, depth + 1)
      assert(newStack.getVal("abc").contains(depth))
    } else {
      val newStack = new ScopeStack(stack)
      newStack.setVal("abc", None)
      assert(newStack.getVal("abc").isEmpty)
    }
  }
  test("Filled Stack") {
    stackTest(stackEmpty, 0)
  }
}
