Ext.define("MyExt.model.TreeModel", { // 定义树节点数据模型
	    extend:'Ext.data.Model',
		fields : [{
					name : "id",
					type : "string"
				}, {
					name : "text",
					type : "string"
				}, {
					name : "iconCls",
					type : "string"
				}, {
					name : "leaf",
					type : "boolean"
				}, {
					name : 'menuType',
					type : 'string'
				}, {
					name : 'menuUri',
					type : 'string'
				}],
		proxy:{
		       type:'ajax',
		       actionMethods:{
		           read:'POST'
		       },
		       api:{
		         create:'',
		         read:'../sm/menu',
		         update:'',
		         destroy:''
		       },
		       reader:{
		       	  type:'json',
		       	  root:'result'
		       	  
		       }
		       
		}		
	});