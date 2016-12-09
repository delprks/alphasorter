package com.delprks.alphasorter

import com.delprks.alphasorter.AlphaSorter._

import org.specs2.mutable.Specification

class AlphaSorter$Test extends Specification {

  "AlphaSorter atoz sort" should {
    "sort based on alphabetical order" in {
      val listOfStrings: List[String] = List("B string", "A string")

      listOfStrings sortBy atoz shouldEqual List("A string", "B string")
    }

    "ignore character casing" in {
      val listOfStrings: List[String] = List("B string", "A string", "a string")

      listOfStrings sortBy atoz shouldEqual List("A string", "a string", "B string")
    }

    "ignore quotations when sorting" in {
      val listOfStrings: List[String] = List("'B string'", "A string", "\"D string\"", "`E string`", "“F string”", "'C string'")

      listOfStrings sortBy atoz shouldEqual List("A string", "'B string'", "'C string'", "\"D string\"", "`E string`", "“F string”")
    }

    "ignore 'the' from beginning of string" in {
      val listOfStrings: List[String] = List("the B string", "The A string", "C string")

      listOfStrings sortBy atoz shouldEqual List("The A string", "the B string", "C string")
    }

    "ignore 'the' from beginning of string when it is in quotation marks" in {
      val listOfStrings: List[String] = List("1 string", "'the B string'", "\"The A string\"", "C string")

      listOfStrings sortBy atoz shouldEqual List("1 string", "\"The A string\"", "'the B string'", "C string")
    }

    "sort the strings that start with numbers naturally" in {
      val listOfStrings: List[String] = List("1 string", "10 string", "2 string", "11 string")

      listOfStrings sortBy atoz shouldEqual List("1 string", "2 string", "10 string", "11 string")
    }

    "sort the strings that start with numbers naturally, even if they start with 'the' or are wrapped in quotations" in {
      val listOfStrings: List[String] = List("1 string", "the 10 string", "'2 string'", "the 9 string", "'the 0 string'")

      listOfStrings sortBy atoz shouldEqual List("'the 0 string'", "1 string", "'2 string'", "the 9 string", "the 10 string")
    }

    "sort the accented characters in-between English words - if an equivalent English character has been specified" in {
      val listOfStrings: List[String] = List("D string", "F string", "È string")

      listOfStrings sortBy atoz shouldEqual List("D string", "È string", "F string")
    }

  }
}
