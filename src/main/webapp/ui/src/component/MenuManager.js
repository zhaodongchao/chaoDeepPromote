Ext.define('MyExt.component.MenuManager', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.MenuManager',
	requires:['MyExt.model.MenuModel','Ext.ux.TreePicker'],
	id : 'MyExt.component.MenuManager',
	stripeRows : true,
	columnLines : true,
	selModel : 'Ext.selection.CheckboxModel',
	renderTo : Ext.getBody(),
	editWindow : null,// 自定义此属性，是对grid数据添加修改的窗口
	initComponent : function() {
		this.store = Ext.create('Ext.data.JsonStore', {
					storeId: 'menuStore',
					model : 'MyExt.model.MenuModel',
					pageSize : 30,
					autoLoad : true,
					remoteSort : false,
					proxy : {
						type : 'ajax',
						url : basePath + '/menu/list',
						reader : {
							type : 'json',
							root : 'result',
							totalProperty : 'total'// 用与分页时的记录总数
						}
					}
				});
		this.columns = [{
					xtype : 'rownumberer'
				}, {
					hidden : true,
					dataIndex : 'menuId'
				}, {
					hidden : true,
					dataIndex : 'parentId'
				}, {
					text : '菜单名称',
					width : 120,
					dataIndex : 'menuName'
				}, {
					text : '菜单等级',
					width : 75,
					// renderer : 'usMoney',
					dataIndex : 'menuLevel'
				}, {
					text : 'URL',
					width : 240,
					dataIndex : 'menuUri'
				}, {
					text : '是否是叶子',
					width : 75,
					dataIndex : 'isLeaf'
				}, {
					text : '创建时间',
					width : 160,
					dataIndex : 'createTime',
					xtype : 'datecolumn',
					format : 'Y-m-d H:i:s'

				}, {
					text : '菜单类型',
					width : 100,
					dataIndex : 'menuType'
				}, {
					text : '父菜单',
					width : 120,
					dataIndex : 'parentName'
				}];
		this.selModel = Ext.create('Ext.selection.CheckboxModel', {
					mode : 'SINGLE',
					showHeaderCheckbox : false
				});
		this.tbar = ['模糊搜索：', {
					xtype : 'textfield',
					id : this.cssid + 's-keyword',
					emptyText : '用户名称或字母'
				}, {
					text : "搜索",
					scope : this,
					iconCls : 'icon-search',
					handler : function() {
						this.store.reload();
					}
				}, {
					text : '新增',
					scope : this,
					iconCls : 'icon-add',
					handler : function() {
						this.initEditWindow('add');
					}
				}, {
					text : '修改',
					scope : this,
					iconCls : 'icon-edit',
					handler : function() {
						this.initEditWindow('edit');
					}
				}, {
					text : '删除',
					scope : this,
					iconCls : 'icon-del',
					handler : function() {
						alert('delete');
					}
				}, {
					text : '导出',
					scope : this,
					iconCls : 'icon-download',
					handler : function() {
						this.export2Excel();
					}
				}];
		this.bbar = [Ext.create('Ext.PagingToolbar', {
					store : this.store,
					border : false,
					pagesize : 10,
					displayInfo : true,
					displayMsg : '{0} - {1} of {2}',
					emptyMsg : "没有记录"
				})];
		this.callParent(arguments);
	},
	initEditWindow : function(operateType) {
		if (operateType == 'add') {
			if (this.editWindow == null) {
				this.editWindow = this.createEditWindow('add');
				this.editWindow.show();
				this.editWindow.down('form').getForm().findField('createTime').setValue(new Date());
			} else {
				if(this.editWindow.down('form').formType = 'edit'){
				  this.editWindow.destroy();
				  this.editWindow = this.createEditWindow('add');
				} 
				this.editWindow.show();
			}
		}
		if (operateType == 'edit') {
			var selectModels = this.getSelectionModel().getSelection();
			if (selectModels.length == 0) {
				Ext.Msg.alert('警告','请选择一条要修改的记录！');
				return;
			} else {
				var selectRecord = selectModels[0];
				   if(this.editWindow!=null){
				       this.editWindow.destroy();
				   }
					this.editWindow = this.createEditWindow('edit');
					this.editWindow.show();
					this.editWindow.down('form').getForm().loadRecord(selectRecord);
					this.editWindow.down('form').getForm().findField('parent.menuId').setValue(selectRecord.get('parentId'))
					this.editWindow.down('form').getForm().findField('parent.menuId').setRawValue(selectRecord.get('parentName'));
					
				
			}

		}

	},
	createEditWindow : function(operateType) {
		var editForm = Ext.widget({
			xtype : 'form',
			layout : 'form',
			formType : operateType,
			collapsible : false,
			id : 'menuManager.form',
			frame : true,
			bodyPadding : '5 5 0',
			width : 350,
			fieldDefaults : {
				msgTarget : 'side',
				labelWidth : 75
			},
			plugins : {
				ptype : 'datatip'
			},
			defaultType : 'textfield',
			items : [{name:'menuId',hidden:true},{
						fieldLabel : '菜单名称',
						name : 'menuName',
						allowBlank : false,
						tooltip : '请输入菜单名称'
					}, {
						fieldLabel : '菜单类型',
						xtype : 'combo',
						name : 'menuType',
						allowBlank : true,
						emptyText : '请选择菜单类型',
						editable : false,
						tooltip : '请选择菜单类型',
						queryMode : 'local',
						displayField : 'menuType',
						valueField : 'menuType',
						store : {
							xtype : 'store',
							fields : ['menuType'],
							data : [{
										"menuType" : "COMPONENT"

									}, {
										"menuType" : "URI"

									}]
						}
					}, {
						fieldLabel : '菜单URI',
						name : 'menuUri',
						allowBlank : false,
						tooltip : '请输入菜单uri'
					}, {
						fieldLabel : '创建日期',
						name : 'createTime',
						xtype : 'datefield',
						anchor : '100%',
						format : 'Y-m-d H:i:s',
						readOnly : true,
						editabled : false
					}, {
						fieldLabel : '菜单等级',
						name : 'menuLevel',
						xtype : 'numberfield',
						editable : false,
						minValue : 1,
						maxValue : 5,
						tooltip : '菜单等级'
					}, {
						xtype : 'radiogroup',
						fieldLabel : '是否叶子',
						//name : 'isLeaf',不能配置name属性，否则loadRecord()方法无法给组件赋值
						columns : 2,
						vertical : false,
						items : [{
									boxLabel : '否',
									name : 'isLeaf',
									inputValue : '0'
									
								}, {
									boxLabel : '是',
									name : 'isLeaf',
									inputValue : '1',
									checked : true
									
								}]
					}, {
						xtype : 'treepicker',
						fieldLabel : '父菜单名',
						name : 'parent.menuId',//这样命名是为了SpringMVC在后台将表单数据封装为一MenuEntity个对象
						width : 400,
						labelWidth : 60,
						margin : '100 0 0 150',
						displayField : 'text',
						valueField : 'id',
						minPickerHeight : 200,
						emptyText : '请选择父菜单',
						labelAlign : 'right',
						animate : true,
						rootVisible : true,
						store : Ext.create('Ext.data.TreeStore', {
									model : 'MyExt.model.TreeModel',
									clearOnLoad : false,
									root : {
										text : 'root',
										expanded : true
									}
								}),
						listeners : {
							select : function(that, record, dd) {
								this.up('form').getForm().findField('menuLevel').setValue(record.raw.level + 1);
							}
						}

					}],

			buttons : [{
				text : 'Save',
				handler : function() {
					var realForm = this.up('form');
					var bform = this.up('form').getForm();
					var formType = this.up('form').formType;
					if (bform.isValid()) {
						var submitUrl = basePath ;
						if(formType=='add'){
						  submitUrl =  submitUrl + '/menu/'+formType ;
						}
						if(formType=='edit'){
						  submitUrl =  submitUrl + '/menu/'+formType ;
						}
						
						bform.submit({
							clientValidation : true,
							url : submitUrl,
							params : {},
							scope:this,
							success : function(form, action) {
								if (action.result.success) {
									Ext.data.StoreManager.lookup('menuStore').load();
									Ext.getCmp('menuManager.editWindow').close();
									Ext.Msg.alert('Success', action.result.msg);
								} else {
									Ext.Msg.alert('Failure', action.result.msg);
								}
							},
							failure : function(form, action) {
								switch (action.failureType) {
									case Ext.form.action.Action.CLIENT_INVALID :
										Ext.Msg.alert('Failure','Form fields may not be submitted with invalid values');
										break;
									case Ext.form.action.Action.CONNECT_FAILURE :
										Ext.Msg.alert('Failure','Ajax communication failed');
										break;
									case Ext.form.action.Action.SERVER_INVALID :
										Ext.Msg.alert('Failure',action.result.msg);
								}
							}
						});
					}
				}
			}, {
				text : 'Cancel',
				handler : function() {
					var formType = this.up('form').formType;
					if(formType=='add'){
					  this.up('window').hide();
					}
					if(formType=='edit'){
					 this.up('window').close();
					}
				}
			}],
			buttonAlign : 'center'
		});
		return Ext.create('Ext.window.Window', {
					title : operateType == 'add' ? '菜单添加' : '菜单修改',
					height : 280,
					width : 350,
					id:'menuManager.editWindow',
					layout : 'fit',
					modal : true,
					closeAction : 'hide',
					items : [editForm]

				});
	},export2Excel:function(){
	   downloadExcel(basePath+'/menu/export',this);
	}
});