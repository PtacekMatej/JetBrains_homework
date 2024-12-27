trait Parser {
  /**
   * a method used to parse a token stream
   * @param tokens
   * the token stream that is to be parsed
   * @return
   * the root node of the abstract syntax tree that had been parsed
   */
  def parse(tokens: TTokenStream): AST_Node
}



//   S -> A*
//   A -> var = val
//   A -> print var
//   A -> scope { S }



class ParserS(next: => Parser) extends Parser {
  override def parse(tokens: TTokenStream): AST_Node = {
    println(tokens.peek())
    val result = AST_Scope()
    while tokens.peek() != TokenEOF() && tokens.peek() != TokenRPar() do
      result.addChild(next.parse(tokens))
    result
  }
}

class ParserAssignment extends Parser {
  override def parse(tokens: TTokenStream): AST_Node = {
    val varName = tokens.matchToken(TokenVarName("")) match
      case TokenVarName(name) => name
    tokens.matchToken(TokenAssignment())
    new AST_Assignment(
      varName,
      if tokens.peek() == TokenValue(0) then
        tokens.matchToken(TokenValue(0)) match
          case TokenValue(value) => AST_Number(value)
      else
        tokens.matchToken(TokenVarName("")) match
          case TokenVarName(value) => AST_Variable(value)

    )
  }
}

class ParserPrint extends Parser {
  override def parse(tokens: TTokenStream): AST_Node = {
    tokens.matchToken(TokenPrint())
    new AST_Print(tokens.matchToken(TokenVarName("")) match
      case TokenVarName(name) => name
    )
  }
}

class parserScope(scopeContent: => Parser) extends Parser {
  override def parse(tokens: TTokenStream): AST_Node = {
    tokens.matchToken(TokenScope())
    tokens.matchToken(TokenLPar())
    val result = scopeContent.parse(tokens)
    tokens.matchToken(TokenRPar())
    result
  }
}

class ParserEither(options: IterableOnce[(Token, Parser)]) extends Parser {
  private val parsers = options.iterator.toArray

  override def parse(tokens: TTokenStream): AST_Node = {
    parsers
      .find { case (token, _) => token == tokens.peek() }
      .map { case (_, parser) => parser.parse(tokens) }
      .getOrElse(throw new Exception("No suitable option for parser"))
  }
}






