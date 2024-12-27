import java.nio.file.Files
import java.nio.file.Paths

trait TFileReader {
  /**
   * @param path
   * the path to the file
   * @return
   * the content of the file as a String
   */
  def read(path: String): String
}

object FileReader extends TFileReader {
  override def read(path: String): String = {
    new String(Files.readAllBytes(Paths.get(path)))
  }
}
