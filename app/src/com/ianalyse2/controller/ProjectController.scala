package com.ianalyse2.controller

import org.springframework.stereotype.Controller
import com.ianalyse2.util.LogHelper
import com.ianalyse2.domain.Projects
import org.springframework.web.bind.annotation.{PathVariable, RequestMethod, RequestMapping}
import org.springframework.web.servlet.ModelAndView
import collection.JavaConversions

@Controller
@RequestMapping(Array("/project"))
class ProjectController extends LogHelper {
  @RequestMapping(value = Array("/{project}.html"), method = Array(RequestMethod.GET))
  def index(@PathVariable project: String) = {
    new ModelAndView("project/index",
      JavaConversions.asJavaMap(Map("project" -> Projects.find(project))))
  }

  @RequestMapping(value = Array("/{project}/commitors.json"), method = Array(RequestMethod.GET))
  def commitors(@PathVariable project: String) = {
    new JsonView(Projects.find(project).commitorSummary.asJson);
  }

  @RequestMapping(value = Array("/{project}/perbuild.json"), method = Array(RequestMethod.GET))
  def perBuild(@PathVariable project: String) = {
    new JsonView(Projects.find(project).perBuild.asJson);
  }

  @RequestMapping(value = Array("/{project}/perday.json"), method = Array(RequestMethod.GET))
  def perDay(@PathVariable project: String) = {
    new JsonView(Projects.find(project).perDay.asJson);
  }
}