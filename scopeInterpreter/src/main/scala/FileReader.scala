import java.nio.file.Files
import java.nio.file.Paths

trait TFileReader {
  def read(path: String): String
}

object FileReader extends TFileReader {
  override def read(path: String): String = {
    new String(Files.readAllBytes(Paths.get(path)))
  }
}
