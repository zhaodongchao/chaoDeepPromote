Ext.Loader.setConfig({// 允许动态加载
	enabled : true,
	paths : {
		MyExt : '../ui/src',
		'Ext.ux' : '../plugins/ext4.2.1/ux'
	}
});
Ext.require([ 'MyExt.model.TreeModel','MyExt.component.MenuManager', 'Ext.ux.DataTip']);
var basePath,currentUserName ;
Ext.onReady(function() {
	Ext.QuickTips.init();
	basePath = document.getElementById('basePath').value;
	currentUserName = document.getElementById('username').value;
	
	var centertabpanel = Ext.create('Ext.tab.Panel', {
				activeTab : 0,
				enableTabScroll : true,
				animScroll : true,
				border : true,
				autoScroll : true,
				region : 'center',
				split : true,
				items : [{
							title : '平台首页',
							iconCls : 'icon-activity'
						}]
			});
	var menuTree = Ext.create('Ext.panel.Panel', {
		region : 'west',
		title : '导航栏',
		width : 250,
		layout : 'accordion',
		split : true,
		collapsible : true
			// 是否可以折叠收缩
		});

	var northPanel = Ext.create("Ext.panel.Panel", {
		height : 80,
		html : '业务基础平台',
		region : 'north',
		split : true,
		bbar : [{
					iconCls : 'icon-user',
					text : '欢迎您 ！'+currentUserName
				}, '-', {
					text : Ext.Date.format(new Date(), 'Y年m月d日')
				}, '->', {
					text : '退出',
					iconCls : 'icon-logout',
					handler : function() {
						window.location.assign(basePath + "/erp_logout");
					}
				}],
		bodyStyle : 'backgroud-color:#99bbe8;line-height : 50px;padding-left:20px;font-size:22px;color:#000000;font-family:黑体;font-weight:bolder;'
				+ 'background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, rgba(153,187, 232, 0.4) ), color-stop(50%, rgba(153, 187, 232, 1) ),color-stop(0%, rgba(153, 187, 232, 0.4) ) )'
	});

	function addTree(response) {
		Ext.getBody().unmask();
		var menus = Ext.JSON.decode(response.responseText);
		for (var i = 0; i < menus.length; i++) {
			menuTree.add(Ext.create("Ext.tree.Panel", {
				title : menus[i].text,
				iconCls : menus[i].iconCls,
				autoScroll : true,
				rootVisible : false,
				viewConfig : {
					loadingText : "正在加载..."
				},
				store : createStore(menus[i].id),
				listeners : {
					itemclick : function(view, record, HtmlElement, index,
							event, eobj) {
						var menu = record.raw;// 获取当前点击节点的数据信息
						if (menu.leaf == 1) {// 如果是叶子节点的话，就给设置相应的资源
							if (menu.menuType == 'URI') {
								var panel = Ext.create('Ext.panel.Panel', {
									title : menu.text,
									closable : true,
									iconCls : 'icon-activity',
									html : '<iframe width="100%" height="100%" frameborder="0" src="http://www.baidu.com"></iframe>'
								});
								centertabpanel.add(panel);
								centertabpanel.setActiveTab(panel);
							} else if (menu.menuType == 'COMPONENT') {// 如果是用Extjs编写的页面
								// 判断当前要现实的资源是否存在
								var isPanel = centertabpanel.getComponent('component_' + menu.id);

								if (isPanel) {
									centertabpanel.remove(isPanel);
									isPanel.destroy();
								}
								var panel = Ext.create(menu.menuUri, {
											title : menu.text,
											id : 'component_' + menu.id,
											closable : true,
											iconCls : 'icon-activity'
										});
								centertabpanel.add(panel);
								centertabpanel.setActiveTab(panel);

							}
						}
					}

				}
			}));
			menuTree.doLayout();
		}

	}

	var createStore = function(id) { // 创建树面板数据源
		return Ext.create("Ext.data.TreeStore", {
					defaultRootId : id, // 默认的根节点id,向后台请求是默认带上参数id
					model : 'MyExt.model.TreeModel',
					clearOnLoad : true,
					nodeParam : "node"// 设置传递给后台的参数名,值是树节点的id属性
				});
	};

	Ext.create('Ext.container.Viewport', {
				layout : 'border',
				items : [centertabpanel, menuTree, northPanel],
				listeners : {
					afterrender : function() {
						Ext.getBody().mask('正在加载系统菜单....');
						Ext.Ajax.request({
									url : basePath + '/sm/menu',
									params : {
										node : 'root'
									},
									success : addTree
								});
					}
				}

			});
});