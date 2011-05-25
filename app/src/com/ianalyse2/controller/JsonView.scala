package com.ianalyse2.controller

import org.springframework.web.servlet.View
import java.util.Map
import java.lang.String
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}
import java.io.PrintWriter

class JsonView(val json:String) extends View {
  
  @Override
  def render(model: Map[String, _], reqest: HttpServletRequest, response: HttpServletResponse) {
    val printWriter: PrintWriter = response.getWriter
    printWriter.write(this.json);
    printWriter.flush
    printWriter.close
  }
  @Override
  def getContentType = {"text/x-json"}

}