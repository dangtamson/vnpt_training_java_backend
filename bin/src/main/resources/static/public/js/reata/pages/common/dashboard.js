$(document)
		.ready(
				function() {

					$("#chart-line").highcharts({

						title : {
							text : null
						},

						subtitle : {
							text : null
						},
						xAxis : {
							categories : [ 'T1', 'T2', 'T3' ],
							crosshair : true
						},
						yAxis : {
							title : {
								text : null
							}
						},
						legend : {
							layout : 'vertical',
							align : 'center',
							verticalAlign : 'bottom'
						},

						plotOptions : {
							series : {
								label : {
									connectorAllowed : false
								}
							}
						},

						series : [ {
							name : 'Khách hàng',
							data : [ 43934, 52503, 57177 ]
						} ],

						responsive : {
							rules : [ {
								condition : {
									maxWidth : 500
								},
								chartOptions : {
									legend : {
										layout : 'horizontal',
										align : 'center',
										verticalAlign : 'bottom'
									}
								}
							} ]
						}

					});
					$("#chart-bar")
							.highcharts(
									{
										chart : {
											type : 'column',
											renderTo: 'container'
										},
										title : {
											text : '',
										},
										subtitle : {
											text : '',
										},
										xAxis : {
											categories : [ 'Vay ODA',
													'Vay Ngân quỹ NN',
													'Vay VDB' ],
											crosshair : true
										},
										yAxis : {
											min : 0,
											title : {
												text : ''
											}
										},
										credits : {
											enabled : false
										},
										tooltip : {
											headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
											pointFormat : '<tr><td style="color:{series.color};padding:0">{series.name}: </td>'
													+ '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
											footerFormat : '</table>',
											shared : true,
											useHTML : false
										},
										plotOptions : {
											column : {
												pointPadding : 0,
												borderWidth : 0
											}
										},
										series : [ {
											name : 'Nợ đầu kỳ',
											data : [ 49.9, 129.2, 144.0 ],
											color : '#4995c1'

										}, {
											name : 'Vay trong kỳ',
											data : [ 83.6, 150, 180 ],
											color : '#d1d5dd'

										}, {
											name : 'Dư nợ cuối kỳ',
											data : [ 65.9, 135.2, 150.0 ],
											color : '#4CAF50'
										} ]
									});
				});