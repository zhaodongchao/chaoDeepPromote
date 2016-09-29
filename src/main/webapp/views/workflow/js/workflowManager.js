$(document).ready(function() {

			$('#processDeployment').DataTable({
						"processing" : false,
						"serverSide" : true,
						"bPaginate": false,  //是否显示分页
						"bFilter": false, //搜索栏
						"bScrollCollapse": true,  //当显示的数据不足以支撑表格的默认的高度时，依然显示纵向的滚动条。(默认是false)  
						"bInfo": false, //显示表格信息
                        "bAutoWidth": true,  //自适应宽度
                        "bSort": false, //是否支持排序功能
						'sAjaxDataProp': 'data',
						"ajax" : {
							"url" : "/erp/workflowManager/findDeployments",
							"dataType" : "json"
						},
						"columns" : [{
									"data" : "id"
								}, {
									"data" : "name"
								}, {
									"data" : "deploymentTime" 
								}, {
									"data" : "category"
								}, {
									"data" : "tenantId"
								}]
					});
			$('#processDefinition').DataTable({
						"processing" : false,
						"serverSide" : true,
						"bPaginate": false,  //是否显示分页
						"bFilter": false, //搜索栏
						"bScrollCollapse": true,  //当显示的数据不足以支撑表格的默认的高度时，依然显示纵向的滚动条。(默认是false)  
						"bInfo": false, //显示表格信息
						"bAutoWidth": true,  //自适应宽度
						"bSort": false, //是否支持排序功能
						"sAjaxDataProp": 'data',//这个属性用来修改服务端数据数据项的引用名
						"ajax" : {
							"url" : "/erp/workflowManager/findProcessDefinitions",
							"dataType" : "json"
						},
						"columns" : [{
									"data" : "id"
								}, {
									"data" : "key"
								}, {
								    "data" : "category"
								},{
									"data" : "diagramResourceName"
								}, {
									"data" :  "resourceName"
								}, {
									"data" : "version"
								}],
							"order": []	
					});
			/* 使用jquery.form提交流程部署 */
			$("form").submit(function(evt) {
						evt.preventDefault();
						$("form").ajaxSubmit({
									url : "/erp/workflowManager/newDeploy",
									dataType : "json",
									type : "POST",
									success : function(data) {
										console.log("data", data);
									},
									error : function(error) {
										console.log("error", error);
									}
								});
					});

		});