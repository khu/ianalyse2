package com.ianalyse2.parser

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers
import java.io.{ByteArrayInputStream, InputStream}
import org.joda.time.{DateTime, DateTimeZone}

class HudsonBuildParserTest extends Spec with ShouldMatchers {

    val hudondXml = <freeStyleBuild>
      <action>
        <cause>
          <shortDescription>Started by timer</shortDescription>
        </cause>
      </action>
      <action></action>
      <action></action>
      <building>false</building>
      <duration>75565</duration>
      <fullDisplayName>analytics-server #1167</fullDisplayName>
      <id>2009-09-28_03-15-23</id>
      <keepLog>false</keepLog>
      <number>1167</number>
      <result>FAILURE</result>
      <timestamp>1254107723000</timestamp>
      <url>http://deadlock.netbeans.org/hudson/job/analytics-server/1167/</url>
      <builtOn></builtOn>
      <changeSet>
        <kind>hg</kind>
      </changeSet>
      <culprit>
        <absoluteUrl>http://deadlock.netbeans.org/hudson/user/Martin%20Schovanek%20_mschovanek@netbeans.org_
        </absoluteUrl>
        <fullName>Martin Schovanek _mschovanek@netbeans.org_</fullName>
      </culprit>
      <culprit>
        <absoluteUrl>http://deadlock.netbeans.org/hudson/user/satyaranjan@netbeans.org</absoluteUrl>
        <fullName>satyaranjan@netbeans.org</fullName>
      </culprit>
      <culprit>
        <absoluteUrl>http://deadlock.netbeans.org/hudson/user/Jesse%20Glick%20_jglick@netbeans.org_</absoluteUrl>
        <fullName>Jesse Glick
          &lt;
          jglick@netbeans.org
          &gt;
        </fullName>
      </culprit>
      <culprit>
        <absoluteUrl>http://deadlock.netbeans.org/hudson/user/Jindrich%20Sedek%20_jsedek@netbeans.org_</absoluteUrl>
        <fullName>Jindrich Sedek
          &lt;
          jsedek@netbeans.org
          &gt;
        </fullName>
      </culprit>
    </freeStyleBuild>

  describe("parse the xml documentation.") {
    it("should parse the build number from hudson build") {
      val build = HudonBuildParser.parseJob(
        new ByteArrayInputStream(hudondXml.toString.getBytes));
      build.number should equal("1167")
    }

    it("should parse the build result from hudson build") {
      val build = HudonBuildParser.parseJob(
        new ByteArrayInputStream(hudondXml.toString.getBytes));
      build.passed should equal(false)
    }

    it("should parse the build duration from hudson build") {
      val build = HudonBuildParser.parseJob(
        new ByteArrayInputStream(hudondXml.toString.getBytes));
      build.duration should equal(75565)
    }

    it("should parse the build name from hudson build") {
      val build = HudonBuildParser.parseJob(
        new ByteArrayInputStream(hudondXml.toString.getBytes));
      build.name should equal("analytics-server")
    }

    it("should parse the commitor from hudson build") {
      val build = HudonBuildParser.parseJob(
        new ByteArrayInputStream(hudondXml.toString.getBytes));
      build.commitors.length should equal(4)
    }
  }
}