package com.delprks.alphasorter

object AlphaSorter {

  implicit object ArrayOrdering extends Ordering[Array[String]] {
    val INT = "([0-9]+)".r

    def compare(a: Array[String], b: Array[String]): Int = {
      val l = Math.min(a.length, b.length)

      (0 until l).prefixLength(i => a(i) equals b(i)) match {
        case i if i == l => Math.signum(b.length - a.length).toInt
        case i => (a(i), b(i)) match {
          case (INT(c), INT(d)) => Math.signum(c.toInt - d.toInt).toInt
          case (c, d) => c compareTo d
        }
      }
    }
  }

  /**
    * > for adding multilingual support, we would need to convert non-English characters to their English equivalent in the replacements map.
    *   currently, it only maps the letter È to E, as a guide.
    *
    * > if a character, such as a quotation mark needs to be ignored from start of the string, it needs to be added to charsToIgnore
    *
    * > if you want leading words to be ignored, add them to wordsToIgnore
    **/
  def atoz(s: String): Array[String] = {
    val charsToIgnore = List("\"", "'", "“", "”", "`")
    val wordsToIgnore = List("the")
    val replacements = Map('\u00C8' -> "E").withDefault(s => s.toString)

    import java.text.Normalizer
    Normalizer.normalize(Normalizer.normalize(
      s.trim.toLowerCase,
      Normalizer.Form.NFKC),
      Normalizer.Form.NFD).replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
      .replaceAll(s"^(${charsToIgnore.mkString("|")})", "")
      .replaceAll(s"^(${wordsToIgnore.mkString("|")}) ", "")
      .flatMap(replacements.apply)
      .split(s"\\s+|(?=[0-9])(?<=[^0-9])|(?=[^0-9])(?<=[0-9])")
  }
}
