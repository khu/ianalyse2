package com.ianalyse2.parser

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers


class FailedCucumberParserTest extends Spec with ShouldMatchers {

    val hudondXml = """
  Scenario: Display line graph for average time on market                                   # features/agentdesktop/reports/time_on_market_report.feature:15
    Given sold data in warehouse with the following details:                                # features/step_definitions/agentdesktop/report_steps.rb:1
      | agent_code | quantity | state | suburb   | postcode | segment     | channel | sold_at      | time_on_market |
      | XYZXYZ     | 1        | NSW   | RICHMOND | 3121     | Residential | Buy     | 1 month ago  | 200            |
      | XYZXYZ     | 1        | NSW   | RICHMOND | 3121     | Residential | Buy     | 3 months ago | 600            |
      | AABRIC     | 1        | NSW   | CARNEGIE | 3163     | Residential | Buy     | 6 months ago | 300            |
      | AABRIC     | 1        | VIC   | MALVERN  | 3003     | Residential | Buy     | 6 months ago | 1750           |
    And the agency "JELHAW" has a "time_on_market" report panel with the following details: # features/step_definitions/agentdesktop/report_steps.rb:55
      | Suburb List | RICHMOND\|3121   |

Failing Scenarios:
cucumber features/agentdesktop/reports/time_on_market_report.feature:15 # Scenario: Display line graph for average time on market
cucumber features/agentdesktop/reports/time_on_market_report.feature:39 # Scenario: Display days on market of active properties for current agent
cucumber features/agentdesktop/reports/time_on_market_report.feature:70 # Scenario: Should not display You line when your listing data is empty

"""

  describe("parse the xml documentation.") {
    it("should parse the build number from hudson build") {
      val arrayOfStrings: Array[String] = hudondXml.toString.split("\n")
      val failedTests = FailedCucumberParser.parseLines(arrayOfStrings.toList);
      failedTests.length should equal (3)
      failedTests(0) should equal ("time_on_market_report.feature#Display line graph for average time on market")
      failedTests(1) should equal ("time_on_market_report.feature#Display days on market of active properties for current agent")
      failedTests(2) should equal ("time_on_market_report.feature#Should not display You line when your listing data is empty")
    }
  }
}