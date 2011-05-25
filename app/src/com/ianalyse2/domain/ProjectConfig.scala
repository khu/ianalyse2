package com.ianalyse2.domain

import xml.{Elem, XML}
import com.ianalyse2.parser.HudonBuildParser
import com.ianalyse2.util.LogHelper

class ProjectConfig(val name: String, val url: String) extends LogHelper {
  private var list: List[ProjectConfig] = List()

  def instantiate() = {
    val project: Project = new Project(this)
    logger.info("started parsing [" + project.config.name + "]")
    val builds = HudonBuildParser.parse(this)
    project.update(builds)
    logger.info("finished parsing [" + project.config.name + "] with " + builds.length + " builds")
    project
  }

  def jobUrl = {
    this.url + "api/xml"
  }
}