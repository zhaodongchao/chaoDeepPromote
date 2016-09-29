Ext.define('MyExt.component.WorkFlowManager', {
			extend : 'Ext.panel.Panel',
			id : 'MyExt.component.WorkFlowManager',
			bodyStyle : "padding: 5px;",
			collapsible : false,
			layout : 'border',
			listeners : {
				render : function(p) {
					p.body.mask('Loading...');
				},
				delay : 50
			},
			initComponent : function() {

				this.grid_north = Ext.create('Ext.grid.Panel', {
							id : 'WorkFlowManager.grid_north',
							title : '流程部署信息',
							store : "",
							columns : [{
										header : 'Name',
										dataIndex : 'name'
									}, {
										header : 'Email',
										dataIndex : 'email',
										flex : 1
									}, {
										header : 'Phone',
										dataIndex : 'phone'
									}]
						});
				this.grid_center = Ext.create('Ext.grid.Panel', {
							id : 'WorkFlowManager.grid_center',
							title : '流程部署信息',
							store : "",
							columns : [{
										header : 'Name',
										dataIndex : 'name'
									}, {
										header : 'Email',
										dataIndex : 'email',
										flex : 1
									}, {
										header : 'Phone',
										dataIndex : 'phone'
									}]
						});
				this.grid_south = Ext.create('Ext.grid.Panel', {
							id : 'WorkFlowManager.grid_south',
							title : '流程部署信息',
							store : "",
							columns : [{
										header : 'Name',
										dataIndex : 'name'
									}, {
										header : 'Email',
										dataIndex : 'email',
										flex : 1
									}, {
										header : 'Phone',
										dataIndex : 'phone'
									}]
						});
			}
		})