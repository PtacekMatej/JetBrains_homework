import org.scalatest.funsuite.AnyFunSuite


class TokenTest extends AnyFunSuite {
  test("Token VarName") {
    assert(TokenVarName("a") == TokenVarName("a"))
    assert(TokenVarName("a") == TokenVarName("b"))
    assert(TokenVarName("a") != TokenValue(1))
    assert(TokenVarName("a") != TokenPrint())
  }
  test("Token Value") {
    assert(TokenValue(1) == TokenValue(1))
    assert(TokenValue(1) == TokenValue(2))
    assert(TokenValue(1) != TokenVarName("a"))
    assert(TokenValue(1) != TokenPrint())
  }
}
