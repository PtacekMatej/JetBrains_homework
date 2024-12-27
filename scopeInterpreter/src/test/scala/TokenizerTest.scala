import org.scalatest.funsuite.AnyFunSuite


class TokenizerTest extends AnyFunSuite {
  test("tokenize") {
    assert( 
      Tokenizer.tokenize(
        "scope { abc = 3 print xy }"
      ) == TokenStream(
        List(
          TokenScope(), TokenLPar(), TokenVarName(""),
          TokenAssignment(), TokenValue(0), TokenPrint(),
          TokenVarName(""), TokenRPar()
        )
      )
    )
  }
}
