
import java.util.Arrays

trait TTokenStream {
  /**
   * peek at the first token in the stream
   * @return
   * the token that is at the front of the token stream
   */
  def peek(): Token

  /**
   * a method used to match a token in the token stream
   * @param token
   * the token that should be at the front of the stream
   * @return
   * the matched token
   * @throws Exception
   * when the parameter does not match the front of the stream
   */
  def matchToken(token: Token): Token
}

class TokenStream(ts: IterableOnce[Token]) extends TTokenStream {
  private val tokens: Array[Token] = ts.iterator.toArray :+ TokenEOF()
  private var idx: Int = 0

  override def equals(obj: Any): Boolean = {
    obj match
      case a: TokenStream =>
        tokens.sameElements(a.tokens)
      case _ => false
  }

  override def hashCode(): Int = {
    val arrayHash = tokens.foldLeft(0)((hash, token) => 31 * hash + token.hashCode())
    arrayHash + 3 * classOf[TokenStream].hashCode()
  }

  override def peek(): Token = tokens(idx)

  override def matchToken(token: Token): Token = {
    if tokens(idx) == token then
      idx = idx + 1
      tokens(idx - 1)
    else
      throw Exception("failed to match token:\n    Expected " + tokens(idx).toString + "\n    Got " + token.toString)
  }
}
