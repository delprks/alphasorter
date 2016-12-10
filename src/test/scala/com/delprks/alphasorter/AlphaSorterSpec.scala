package com.delprks.alphasorter

import org.scalacheck._
import org.scalacheck.Prop.{forAll, BooleanOperators}
import com.delprks.alphasorter.AlphaSorter._

object AlphaSorterSpec extends Properties("AlphaSorter") {

  property("atoz") = forAll(listOfUnicodeStrings) { l =>
    l.nonEmpty ==> {
      val sorted = l sortBy atoz
      (sorted, sorted.tail).zipped.forall(_.toLowerCase.trim <= _.toLowerCase.trim)
    }
  }

  private def listOfUnicodeStrings: Gen[List[String]] = {
    val charSet: Seq[Char] = ('A' to 'Z') ++ ('a' to 'z') ++ Seq(' ')

    val unicodeString: Gen[String] = Gen.chooseNum(21, 40).flatMap { n =>
      Gen.buildableOfN[String, Char](n, Gen.oneOf(charSet))
    }

    Gen.listOf(unicodeString)
  }

}
