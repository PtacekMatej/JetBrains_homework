


@main
def main(args: String*): Unit = {
  val argsArr = args.toArray
  if(argsArr.length != 1) {
    println("Wrong number of arguments")
    return
  }
  lazy val parserS: Parser = new ParserS(parserA)
  lazy val parserA: Parser = new ParserEither(List(
    (TokenScope(), new parserScope(parserS)),
    (TokenVarName(""), new ParserAssignment()),
    (TokenPrint(), new ParserPrint())
  ))

  parserS.parse(
    Tokenizer.tokenize(FileReader.read(argsArr(0)))
  ).eval(
    new ScopeStack(new ScopeStackEmpty())
  )


}
