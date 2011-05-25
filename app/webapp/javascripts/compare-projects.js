jQuery(document).ready(function() {
$.ajax({
  url: "compare.json",
  success: function(data, textStatus, jqXHR){
        var obj = jQuery.parseJSON(jqXHR.responseText);
        render_compare_projects(obj)
  }
});
})

function render_compare_projects(json) {
        var chart = new Highcharts.Chart({
        chart: {
            renderTo: 'compare-pass-rate'
        },
        title: {
            text: ''
        },
        xAxis: {
            categories:json["names"],
            labels: { rotation: 45, align: 'left' }
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
            data: json["failed"]
        },{
            name: 'Passed',
            color: '#52A622',
            type: 'column',
            data: json["passed"]
        }
        ,{
            name: 'Pass rate',
            color: '#4572A7',
            type: 'line',
            yAxis: 1,
            data: json["rate"]

        }]
    });
}