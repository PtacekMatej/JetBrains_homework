trait Tokenizer {
  def tokenize(input: String): TTokenStream
}

object Tokenizer extends Tokenizer {
  override def tokenize(input: String): TTokenStream = {
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
