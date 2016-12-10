package com.delprks.alphasorter.util

import com.typesafe.config.ConfigFactory

trait Config {
  lazy val config = ConfigFactory.load()

  lazy val charsToIgnore = config.getStringList("alphasorter.atoz.chars-to-ignore")
  lazy val wordsToIgnore = config.getStringList("alphasorter.atoz.words-to-ignore")
}
