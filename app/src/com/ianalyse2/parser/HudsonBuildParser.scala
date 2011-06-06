package com.ianalyse2.parser


import xml.{Elem, XML}
import org.apache.commons.lang.{StringUtils}
import java.io.{InputStream}
import java.util.regex.{Matcher, Pattern}
import com.ianalyse2.domain.{ProjectConfig, Builds, Build}
import java.net.URL
import org.joda.time.{DateTimeZone, DateTime}

object HudonBuildParser {

  def parse() {
    new Builds()
  }

  def parse(config: ProjectConfig) = {
    val elem: Elem = XML.load(new URL(config.jobUrl))
    val buildSegments = elem \ "build"
    var builds = new Builds()
    for (buildSegment <- buildSegments) {
      val name: String = (buildSegment \ "name").text
      val url: String = (buildSegment \ "url").text
      val job = parseJob(url);
      builds = builds.:::(job)
    }
    builds
  }

  def apiXmlUrl(url:String) = {
      url + "/api/xml"
  }

  def consoleTextUrl(url:String) = {
      url + "/consoleText"
  }

  def parseJob(url: String) = {
    val elem: Elem = XML.load(new URL(apiXmlUrl(url)))
    val commitor = parseCommiter(elem)
    new Build(name((elem \ "url").text),
      (elem \ "number").text,
      new DateTime((elem \ "timestamp").text.toLong, DateTimeZone.UTC),
      (elem \ "duration").text.toInt,
      result((elem \ "result").text),
      commitor);
  }

  def parseJob(stream: InputStream) = {
    val elem: Elem = XML.load(stream)
    val commitor = parseCommiter(elem)
    new Build(name((elem \ "url").text),
      (elem \ "number").text,
      new DateTime((elem \ "timestamp").text.toLong, DateTimeZone.UTC),
      (elem \ "duration").text.toInt,
      result((elem \ "result").text),
      commitor);
  }

  def parseCommiter(elem: Elem) = {
    var commiters: List[String] = List[String]()
    var commitorSegment = elem \ "culprit"
    for (commitor <- commitorSegment) {
      commiters = commiters ::: List((commitor \ "fullName").text)
    }

    commiters
  }

  def name(workspace: String) = {
    val pattern: Pattern = Pattern.compile(".*/job/(.+)/.+")
    val matcher: Matcher = pattern.matcher(workspace)
    if (matcher.matches()) {
      matcher.group(1);
    } else {
      ""
    }
  }

  def result(result: String) = {
    !StringUtils.equals(result, "FAILURE")
  }
}
