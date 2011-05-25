package com.ianalyse2.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMethod, RequestMapping}
import org.springframework.web.servlet.ModelAndView
import com.ianalyse2.util.LogHelper
import java.util.HashMap
import com.ianalyse2.domain.{Project, Projects}
import collection.JavaConversions
import collection.immutable.Map
import java.lang.String

@Controller
@RequestMapping(Array("/projects"))
class ProjectsController extends LogHelper {
  @RequestMapping(value = Array("/index"), method = Array(RequestMethod.GET))
  def index() = {
    new ModelAndView("projects/index",
      JavaConversions.asJavaMap(Map("projects" -> Projects)))
  }

  @RequestMapping(value = Array("/compare.html"), method = Array(RequestMethod.GET))
  def compare() = {
    val data: HashMap[String, java.util.List[Project]] =
      new HashMap[String, java.util.List[Project]]();
    data.put("projects", JavaConversions.asJavaList(Projects.inOrder))
    new ModelAndView("projects/compare", data)
  }

  @RequestMapping(value = Array("/compare.json"), method = Array(RequestMethod.GET))
  def compareJson() = {
    val json: String = Projects.passRates.asJson
    new JsonView(json);
  }
}