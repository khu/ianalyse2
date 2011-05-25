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
    <script type="text/javascript" src="/ianalyse2/javascripts/per-build.js"></script>
</head>
<body>
	<div class="container" id="header">
		<span id="title">iAnalyze</span>
		<span id="subtitle">&nbsp;&nbsp;&nbsp;&nbsp;Tell more about your builds</span>
	</div>
	<br/>
	<div class="container frame">
		<h1>Project ${project.name()}</h1>
		<div class="span-4 border big-text-box">
			<div class="number">${project.length()}</div> <div class="desc">build</div>
		</div>
		<div class="span-4 border big-text-box build-success">
			<div class="number">${project.passCount()}</div> <div class="desc">successful</div>
		</div>
		<div class="span-4 border big-text-box build-failed">
			<div class="number">${project.failedCount()}</div> <div class="desc">failed</div>
		</div>
		<div class="span-6 border big-text-box build-running">
			<div class="number">${project.passRate()}%</div> <div class="desc">success rate</div>
		</div>
		<div class="span-6 last big-text-box build-running">
			<div class="number">${project.avgDuration()} min</div> <div class="desc">taken by last build</div>
		</div>
	</div>
	<br/>

	<div class="container">
		<h2>Passed and failed checkins for the commitors</h2>
        <div id="per-commitor">
        </div>
        <script>
        jQuery(document).ready(function() {
        $.ajax({
          url: "/ianalyse2/project/${project.name()}/commitors.json",
          success: function(data, textStatus, jqXHR){
                var obj = jQuery.parseJSON(jqXHR.responseText);
                render_commitors(obj)
          }
        });
        })
        </script>
	</div>
	<div class="container">
		<h2>Date and Time of each build</h2>
        <div id="per-build">
        </div>
        <script type="text/javascript">
        jQuery(document).ready(function() {
        $.ajax({
          url: "/ianalyse2/project/${project.name()}/perbuild.json",
          success: function(data, textStatus, jqXHR){
                var obj = jQuery.parseJSON(jqXHR.responseText);
                per_build(obj,
                "http://deadlock.netbeans.org/hudson/job/${project}/")
          }
        });
        })
        </script>
	</div>

	<div class="container">
		<h2>Date and Time of each build</h2>
        <div id="per-day">
        </div>
        <script type="text/javascript">
        var chart = new Highcharts.Chart({
        chart: {
            renderTo: 'per-day'
        },
        title: {
            text: ''
        },
        xAxis: {
            type: 'datetime',
            dateTimeLabelFormats: { // don't display the dummy year
                month: '%e. %b',
                year: '%b'
            }
        },
        yAxis: [{ // Primary yAxis
            labels: {
                formatter: function() {
                    return this.value +'times';
                },
                style: {
                    color: '#89A54E'
                }
            },
            title: {
                text: 'Builds',
                style: {
                    color: '#89A54E'
                }
            }
        }, { // Secondary yAxis
            title: {
                text: 'Pass rate',
                style: {
                    color: '#4572A7'
                }
            },
            labels: {
                formatter: function() {
                    return this.value +' %';
                },
                style: {
                    color: '#4572A7'
                }
            },
            opposite: true
        }],
        tooltip: {
            formatter: function() {
                var s = ''+
                        this.x  +': '+ this.y;
                return s;
            }
        },
        labels: {
            items: [{
                html: '',
                style: {
                    left: '40px',
                    top: '8px',
                    color: 'black'
                }
            }]
        },
        plotOptions: {
            column: {
                stacking: 'normal',
                borderWidth: 0
            }
        },
        series: [
        {
            name: 'Failed',
            color: '#B90016',
            type: 'column',
            data: [[Date.UTC(2010, 3,11), 3],[Date.UTC(2010, 3,13), 7],[Date.UTC(2010, 3,12), 9]]
        },{
            name: 'Passed',
            color: '#52A622',
            type: 'column',
            data: [[Date.UTC(2010, 3,11), 12],[Date.UTC(2010, 3,13), 12],[Date.UTC(2010, 3,12), 13]]
        }
        ,{
            name: 'Pass rate',
            color: '#4572A7',
            type: 'line',
            yAxis: 1,
            data: [[Date.UTC(2010, 3, 16), 12], [Date.UTC(2010, 3,19), 12], [Date.UTC(2010, 3,20), 19]]

        }]
    });
        </script>
	</div>


	<br/>
	<div id="footer" class="container">
		<hr/>
		<a href="#">contributors</a> | <a href="#">project home page</a>
		<hr class="space"/>
	</div>
</body>
</html>
