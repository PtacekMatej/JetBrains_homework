trait Token

case class TokenScope() extends Token
case class TokenLPar() extends Token
case class TokenRPar() extends Token
case class TokenVarName(name: String) extends Token {
  override def equals(obj: Any): Boolean = obj match {
    case _: TokenVarName => true
    case _ => false
  }

  override def hashCode(): Int = classOf[TokenVarName].hashCode
}
case class TokenAssignment() extends Token
case class TokenValue(value: Int) extends Token {
  override def equals(obj: Any): Boolean = obj match {
    case _: TokenValue => true
    case _ => false
  }

  override def hashCode(): Int = classOf[TokenValue].hashCode
}
case class TokenPrint() extends Token
case class TokenEOF() extends Token
