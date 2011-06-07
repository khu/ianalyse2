package com.ianalyse2.parser

import java.io.{InputStream}
import java.net.URL

object FailedCucumberParser {

  def parse(url:String) = {
    val lines = scala.io.Source.fromURL(new URL(url)).getLines
    parseLines(lines.toList)
  }

  def parseLines(lines:List[String]) = {
    var startCapture = false;
    var result = List[String]()
    val Scenario = """cucumber .+/(.+.feature):\d+ # Scenario: (.+)""".r

    for(line <- lines) {
      if (line.contains("Failing Scenarios:")) {
        startCapture = true;
      }
      if (startCapture) {
        try {
          val Scenario(fileName, scenarioName) = line
          result = result ::: List(fileName + "#" + scenarioName)
        } catch {
          case e =>
        }
      }
    }
    result
  }
}