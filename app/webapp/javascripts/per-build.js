function per_build(data, baseurl) {
    var chart = new Highcharts.Chart({
        chart: {
            renderTo: 'per-build',
            defaultSeriesType: 'scatter',
            zoomType: 'xy'
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
        yAxis: {
            title: {
                text: 'Time'
            }
        },
        tooltip: {
            formatter: function() {
                    return 'Build Number <a style="color:blue" href="'
                            + baseurl
                            + this.point.config[2]
                            + '/"> '
                            + this.point.config[2]
                            + '</a>';
            }
        },
        legend: {
            layout: 'vertical',
            align: 'left',
            verticalAlign: 'top',
            x: 100,
            y: 70,
            floating: true,
            backgroundColor: '#FFFFFF',
            borderWidth: 1
        },
        plotOptions: {
            scatter: {
                marker: {
                    radius: 5,
                    states: {
                        hover: {
                            enabled: true,
                            lineColor: 'rgb(100,100,100)'
                        }
                    }
                },
                states: {
                    hover: {
                        marker: {
                            enabled: false
                        }
                    }
                }
            }
        },
        series: [{
            name: 'Failed',
            color: '#B90016',
            data: data.failed

        }, {
            name: 'Passed',
            color: '#52A622',
            data: data.passed

        }]
    });
}
