trait Tokenizer {
  /**
   * a method used to tokenize a string
   * @param input
   * the string that is to be tokenized
   * @return
   * a TokenStream of the tokens from the input string
   */
  def tokenize(input: String): TTokenStream
}

object Tokenizer extends Tokenizer {
  override def tokenize(input: String): TTokenStream = {
    // I am assuming that the tokens are divided by white space
    // we can discuss at the interview how to tokenize when it is not the case :-)
    TokenStream(input.split("\\s+").map[Token](
      (s:String) => {
        s match
          case "scope" => TokenScope()
          case "=" => TokenAssignment()
          case "{" => TokenLPar()
          case "}" => TokenRPar()
          case "print" => TokenPrint()
          case a: String if a.matches("\\d+") => TokenValue(a.toInt)
          case a: String => TokenVarName(a)
      }
    ))
  }
}
