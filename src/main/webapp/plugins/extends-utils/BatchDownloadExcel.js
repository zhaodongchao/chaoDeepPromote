/**
 * 构造函数
 * @param 1:序号
 * @param 2:列名
 * @param 3:列宽,默认100
 * @param 4:列对齐方式
 * @param 5:是否隐藏列
 */
function getColumnInfo(gird,orderType)
{
	var columnModel = gird.getColumnModel();
	
	var store = gird.getStore();
	
	if(columnModel == null||store == null)
	{
		return false;
	}
	
	var columnInfo = [];
	if(!orderType){
		for(var i=0 ;i<=columnModel.getColumnCount();i++)
		{
			var columnLine = columnModel.getColumnById(i);
			if(columnLine == null||columnLine==undefined)
			{
				continue;
			}
			var column = [];
			column[0] = i-1;
			column[1] = columnLine.header!=null?columnLine.header:"";
			column[2] = columnLine.width!=null?columnLine.width:150;
			if(columnLine.align == null)
			{
				column[3] = 1;
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
	}else{
		var count = columnModel.getColumnCount() ;
	    if(count>=3){
			for(var i=0 ;i<=2;i++){
				var columnLine = columnModel.getColumnById(i);
				if(columnLine == null||columnLine==undefined)
				{
					continue;
				}
				var column = [];
				column[0] = i-1;
				column[1] = columnLine.header!=null?columnLine.header:"";
				column[2] = columnLine.width!=null?columnLine.width:150;
				if(columnLine.align == null){
					column[3] = 1;
				}else{
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
				if(column[4] == true){
					continue;
				}
				column[5] = columnLine.exportType!=null?columnLine.exportType:'';
				columnInfo.push(column);
				
			  }
	     for(var i=count ; i>=3 ;i--)	{
	        
				var columnLine = columnModel.getColumnById(i);
				if(columnLine == null||columnLine==undefined)
				{
					continue;
				}
				var column = [];
				column[0] = i-1;
				column[1] = columnLine.header!=null?columnLine.header:"";
				column[2] = columnLine.width!=null?columnLine.width:150;
				if(columnLine.align == null){
					column[3] = 1;
				}else{
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
				if(column[4] == true){
					continue;
				}
				column[5] = columnLine.exportType!=null?columnLine.exportType:'';
				columnInfo.push(column);
				
			  
	     }	  
	    }
			
	}
	if(columnInfo.length ==0)
	{
		return false;
	}
	
	return columnInfo;
}

function downloadData(url,gird,orderType){
	if(orderType ==null ||orderType==undefined){
	     return false ;
	}
	var columnInfo = getColumnInfo(gird,orderType);
	if(columnInfo == false){
		return false;
	}
	var store = gird.getStore();
	store.baseParams["listColumnInfo"] = columnInfo;
	store.baseParams["orderType"] = orderType ;
	var box = Ext.MessageBox.show({
										msg :'导出中,请稍候...',
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
		params:store.baseParams,
		success:function(response){
			var fileAddress = Ext.util.JSON.decode(response.responseText);
			window.open( document.getElementById('basePath').value+"commonInterface/excleDownload.action?inputPath="+(fileAddress.fileAddress),'_self');
			box.hide();
			//返原这个参数
			store.baseParams["listColumnInfo"] = "";
			store.baseParams["orderType"] = false ;
		},
		failure:function(response,optional){
			box.hide();
			Ext.Msg.alert("错误！", "导出内容含有非法字符!");
		}
	});
}