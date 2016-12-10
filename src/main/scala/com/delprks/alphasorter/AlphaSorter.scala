package com.delprks.alphasorter

import com.delprks.alphasorter.util.Config

object AlphaSorter extends Config {

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
    * for adding multilingual support, we would need to convert non-English characters to their English equivalent in the replacements map.
    * currently, it only maps the letter È to E, as a guide.
    **/
  def atoz(s: String): Array[String] = {
    val replacements = Map('\u00C8' -> "E").withDefault(s => s.toString)

    import java.text.Normalizer
    Normalizer.normalize(Normalizer.normalize(
      s.trim.toLowerCase,
      Normalizer.Form.NFKC),
      Normalizer.Form.NFD).replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
      .replaceAll(s"^(${String.join("|", charsToIgnore)})", "")
      .replaceAll(s"^(${String.join("|", wordsToIgnore)}) ", "")
      .flatMap(replacements.apply)
      .split(s"\\s+|(?=[0-9])(?<=[^0-9])|(?=[^0-9])(?<=[0-9])")
  }
}
