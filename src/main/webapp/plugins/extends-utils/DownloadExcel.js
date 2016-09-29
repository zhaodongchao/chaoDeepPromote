/**
 * 构造函数
 * @param 1:序号
 * @param 2:列名
 * @param 3:列宽,默认100
 * @param 4:列对齐方式
 * @param 5:是否隐藏列
 */
function getColumnInfo(gird)
{
	var columns = gird.columns;
	
	var store = gird.getStore();
	
	if(columns == null||store == null)
	{
		return false;
	}
	
	var columnInfo = [];
	
	for(var i=0 ;i<=columns.length;i++)
	{
		var columnLine = columns[i];
		if(!columnLine){
			continue;
		}
		if(columnLine.xtype == 'rownumberer'){//排除序列号
		    continue;
		}
		var column = [];
		column[0] = i-1;
		column[1] = columnLine.text!=null?columnLine.text:"";
		column[2] = columnLine.width!=null?columnLine.width:150;
		if(columnLine.align == null)
		{
			column[3] = 1;//默认为靠左
		}
		else
		{
			if(columnLine.align.toUpperCase() == "LEFT")
			{
				column[3] = 1;
			}
			else if(columnLine.align.toUpperCase() == "CENTER")
			{
				column[3] = 2;
			}
			else if(columnLine.align.toUpperCase() == "RIGHT")
			{
				column[3] = 3;
			}
		}
		column[4] = columnLine.hidden!=null?columnLine.hidden:false;
		if(column[4] == true)
		{
			continue;
		}
		
		column[5] = columnLine.exportType!=null?columnLine.exportType:'';
		columnInfo.push(column);
		
	}
	if(columnInfo.length ==0)
	{
		return false;
	}
	
	return columnInfo;
}

function downloadExcel(url,gird)
{
	var columnInfo = getColumnInfo(gird);
	if(columnInfo == false)
	{
		return false;
	}
	var store = gird.getStore();
	store.proxy.extraParams["listColumnInfo"] = columnInfo;
	
	var box = Ext.MessageBox.show({ msg :'导出中,请稍候...',
									progressText: '',
									width : 200,
									height:100,
									wait : true,
									waitConfig : {
										interval : 250
									}
					     		     });
					     		     
	Ext.Ajax.request(
	{
		url:url,
		params:store.proxy.extraParams,
		success:function(response){
			var resultData = Ext.decode(response.responseText,true);
			window.open(basePath+"/sm/base/export/"+(resultData.fileAddress),'_self');
			box.hide();
			//返原这个参数
			store.proxy.extraParams["listColumnInfo"] = "";
		},
		failure:function(response,optional){
			box.hide();
			Ext.Msg.alert("错误！", "导出内容含有非法字符!");
		}
	});
}