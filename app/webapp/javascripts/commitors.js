function render_commitors(json) {
        var chart = new Highcharts.Chart({
        chart: {
            renderTo: "per-commitor"
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
        }]
    });
}