package com.ianalyse2.service

import org.springframework.stereotype.Service
import java.util.TimerTask
import com.ianalyse2.domain.ProjectsConfig
import com.ianalyse2.util.LogHelper
import java.lang.String

@Service
class AnalyseService extends TimerTask with LogHelper {
  override def run() {
    val url: String = System.getProperty("url")
    val configs = new ProjectsConfig(url);
    configs.start
    configs.init
  }
}