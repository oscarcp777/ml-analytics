
function getColumnChar(data){
	var config={
    		options: {
  		      chart: {
  		        type: 'column'
  		      },
  		      plotOptions: {
  		            series: {
  		                borderWidth: 0,
  		                dataLabels: {
  		                    enabled: true,
  		                    format: '{point.y:.1f}'
  		                }
  		            }
  		        }
  		    },
          title: {
              text: data.title
          },
          subtitle: {
              text: 'Fuente: <a href="http://developers.mercadolibre.com/"/>http://developers.mercadolibre.com/</a>'
          },
          xAxis: {
              title: {
                  text: data.xAxis
              },
          type: 'category'
          },
          yAxis: {
              min: 0,
              title: {
                  text: data.yAxis
              }
          },
          legend: {
              layout: 'vertical',
              align: 'right',
              verticalAlign: 'top',
              x: -40,
              y: 40,
              floating: true,
              borderWidth: 1,
              backgroundColor:  '#FFFFFF',
              shadow: true
          },
          credits: {
              enabled: false
          },
          series: data.series
	  };
	return config;
}
function getLineChar(data){
	var config={
			chart: {
	            type: 'spline',
	            animation: Highcharts.svg, // don't animate in old IE
	            plotShadow: true,
	            plotBorderWidth: 2
	        },
	        title: {
	            text:data.title
	        },
	          subtitle: {
	              text: 'Fuente: <a href="http://developers.mercadolibre.com/"/>http://developers.mercadolibre.com/</a>'
	          },
	        xAxis: {
	       		categories: data.serieX,
	        	gridLineWidth: 1,
	        	title: {
	                text:data.titleX
	            },
                labels: {
                    overflow: 'justify'
                }
	        },
	        yAxis: {
	            title: {
	                text: data.titleY
	            }
	        },
	        tooltip: {
	        	crosshairs: true,
		        shared: false
	        },
	        legend: {
	            enabled: true
	        },
	        exporting: {
	            enabled: true
	        },
	        series: data.series
	    };
	return config;
}

function getPieChar(data){
	var config={
			chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: true
            },
			title : {
				text : data.title
			},
	          subtitle: {
	              text: 'Fuente: <a href="http://developers.mercadolibre.com/"/>http://developers.mercadolibre.com/</a>'
	          },
			tooltip: {
        	    pointFormat: '{series.name}: <b>{point.y}</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                        style: {
                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                        }
                    },
                    showInLegend: true
                }
            },
			series : [ {
				type : 'pie',
				name : data.name,
				data : data.data
			} ]
	};
	return config;
}
function getSolidGaugeChar(data){
var config={
	     chart: {
	            type: 'solidgauge'
	        },

	        title: data.title,
	          
	        pane: {
	            center: ['50%', '85%'],
	            size: '140%',
	            startAngle: -90,
	            endAngle: 90,
	            background: {
	                backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || '#EEE',
	                innerRadius: '60%',
	                outerRadius: '100%',
	                shape: 'arc'
	            }
	        },

	        tooltip: {
	            enabled: false
	        },

	        // the value axis
	        yAxis: {
	            stops: [
	                [0.1, '#55BF3B'], // green
	                [0.5, '#DDDF0D'], // yellow
	                [0.9, '#DF5353'] // red
	            ],
	            lineWidth: 0,
	            minorTickInterval: null,
	            tickPixelInterval: 400,
	            tickWidth: 0,
	            title: {
	                y: -70
	            },
	            labels: {
	                y: 16
	            }
	        },

	        plotOptions: {
	            solidgauge: {
	                dataLabels: {
	                    y: 5,
	                    borderWidth: 0,
	                    useHTML: true
	                }
	            }
	        },
	        yAxis: {
	            min: 0,
	            max: data.max,
	            title: {
	                text: data.xAxis
	            }
	        },

	        credits: {
	            enabled: false
	        },

	        series: [{
	            name: data.title,
	            data: data.series,
	            dataLabels: {
	                format: '<div style="text-align:center"><span style="font-size:35px;color:' +
	                    ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y}</span><br/>' +
	                       '<span style="font-size:16px;color:silver">'+data.xAxis+'</span></div>'
	            },
	            tooltip: {
	                valueSuffix: data.xAxis
	            }
	        }]
	};
	return config;
}
