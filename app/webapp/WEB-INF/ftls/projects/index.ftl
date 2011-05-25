<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Projects</title>
	<!-- Framework CSS -->
	<link rel="stylesheet" href="/ianalyse2/css/screen.css" type="text/css" media="screen, projection">
	<link rel="stylesheet" href="/ianalyse2/css/plugins/buttons/screen.css" type="text/css" media="screen, projection">
	<link rel="stylesheet" href="/ianalyse2/css/main.css" type="text/css" media="screen, projection">
	<link rel="stylesheet" href="/ianalyse2/css/print.css" type="text/css" media="print">
	<!--[if lt IE 8]><link rel="stylesheet" href="/ianalyse2/css/ie.css" type="text/css" media="screen, projection"><![endif]-->

    <script type="text/javascript" src="/ianalyse2/javascripts/jquery-1.5.2.min.js"></script>
    <script type="text/javascript" src="/ianalyse2/javascripts/highcharts.js"></script>
    <script type="text/javascript" src="/ianalyse2/javascripts/exporting.js"></script>
    <script type="text/javascript" src="/ianalyse2/javascripts/commitors.js"></script>
</head>
<body>
	<div class="container" id="header">
		<span id="title">iAnalyze</span>
		<span id="subtitle">&nbsp;&nbsp;&nbsp;&nbsp;Tell more about your builds</span>
	</div>
	<br/>
	<div class="container frame">
		<div>We have analyzed 1 hudson server and get</div>
		<div class="span-6 border big-text-box">
			<div class="number">${projects.count()}</div> <div class="desc">projects</div>
		</div>
		<div class="span-6 border big-text-box build-success">
			<div class="number">${projects.passedCount()}</div> <div class="desc">successful</div>
		</div>
		<div class="span-6 border big-text-box build-failed">
			<div class="number">${projects.failedCount()}</div> <div class="desc">failed</div>
		</div>
	</div>
    <div class="container">
		<hr/>
		<p>Select <a>all</a>, <a>none</a> projects to <a href="compare.html">compare them</a>.</p>
		<form action="compare.html" method="GET">
			<table border="0" cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<th class="span-8">Name</th>
						<th class="span-4">Pass Count</th>
						<th class="span-4">Failed Count</th>
						<th class="span-4">Success Rate</th>
						<th class="span-4">Avg Duration</th>
					</tr>
				</thead>
				<tbody>
				    <#list projects.names() as name>
				    <#assign project = projects.find(name)>
					<tr>
						<td>
						    <input type="checkbox" disabled checked>
						    <a href="/ianalyse2/project/${name}.html">
						        ${name}
						    </a>
						</td>
						<td class="build-success">${project.passCount()}</td>
						<td class="build-failed">${project.failedCount()}</td>
						<td>${project.passRate()}%</td>
						<td>${project.avgDuration()} min</td>
					</tr>
					</#list>
				</tbody>
			</table>
			<button class="positive">Compare all </button>
		</form>
	</div>

	<br/>
	<br/>
	<br/>
	<div id="footer" class="container">
		<hr/>
		<a href="#">contributors</a> | <a href="#">project home page</a>
		<hr class="space"/>
	</div>
</body>
</html>
