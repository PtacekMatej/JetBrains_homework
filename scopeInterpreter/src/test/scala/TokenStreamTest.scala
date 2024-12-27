import org.scalatest.funsuite.AnyFunSuite


class TokenStreamTest extends AnyFunSuite {
  val tokens: List[Token] = List(TokenScope(), TokenPrint(), TokenValue(2), TokenRPar())
  val tokenStream1 = new TokenStream(tokens)
  val tokenStream2 = new TokenStream(tokens)
  val tokenStream3 = new TokenStream(tokens :+ TokenVarName("abc"))
  test("TokenStream equality") {
    assert(tokenStream1 == tokenStream2)
    assert(tokenStream1 != tokenStream3)
    assert((tokens :+ TokenEOF()) != tokenStream1)
  }
  test("TokenStream hashCode") {
    assert(tokenStream1.hashCode() == tokenStream2.hashCode())
    assert(tokenStream1.hashCode() != tokenStream3.hashCode())
    assert((tokens :+ TokenEOF()).hashCode() != tokenStream1.hashCode())
  }
  test("TokenStream content") {
    for(token <- tokens :+ TokenEOF()) {
      assert( tokenStream1.peek() == token)
      assert( tokenStream1.matchToken(token) == token)
    }
  }
}
