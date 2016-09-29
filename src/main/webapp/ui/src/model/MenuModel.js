Ext.define('MyExt.model.MenuModel', {
			extend : 'Ext.data.Model',
			fields : [{
						name : 'menuId',
						mapping : 'MENU_NO',
						type:'string'
					}, {
						name : 'menuName',
						mapping : 'MENU_NAME',
						type:'string'
					}, {
						name : 'menuUri',
						mapping : 'MENU_URI',
						type:'string'
					}, {
						name : 'menuLevel',
						mapping : 'MENU_LEVEL',
						type:'int'
					}, {
						name : 'isLeaf',
						mapping : 'IS_LEAF',
						type:'string'//设置为string类型，方便basicForm.loadRecord()给该组件赋值
					}, {
						name : 'createTime',
						mapping : 'CREATE_TIME',
						type:'date'
					}, {
						name : 'menuType',
						mapping : 'MENU_TYPE',
						type:'string'
					}, {
						name : 'parentId',
						mapping : 'PARENT_NO',
						type:'string'
					}, {
						name : 'parentName',
						mapping : 'PARENT_NAME',
						type:'string'
					}]
		});