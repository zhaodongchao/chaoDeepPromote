package com.base.export.typeconversion;

import java.util.ArrayList;

import org.springframework.core.convert.converter.Converter;

import com.base.export.entity.ColumnInfo;


/**
 * @author zhaodongchao
 */
public class ColumnInfoTypeConversion implements Converter<String[],ArrayList<ColumnInfo>> {

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public ArrayList<ColumnInfo> convert(String[] source) {
		ArrayList<ColumnInfo> columnList = new ArrayList<>();
		for(int i=0 ; i< source.length ;i++){
			String columnInfo[] = source[i].split(",");
			ColumnInfo column = null ;
			if(columnInfo.length == 5)
			{
				column = new ColumnInfo(Integer.parseInt(columnInfo[0]),columnInfo[1],Integer.parseInt(columnInfo[2]),Short.parseShort(columnInfo[3]),Boolean.parseBoolean(columnInfo[4]));
			}
			else if(columnInfo.length == 6)
			{
				column = new ColumnInfo(Integer.parseInt(columnInfo[0]),columnInfo[1],Integer.parseInt(columnInfo[2]),Short.parseShort(columnInfo[3]),Boolean.parseBoolean(columnInfo[4]),String.valueOf(columnInfo[5]));
			}else if(columnInfo.length == 7)
			{
				column = new ColumnInfo(Integer.parseInt(columnInfo[0]),columnInfo[1],Integer.parseInt(columnInfo[2]),Short.parseShort(columnInfo[3]),Boolean.parseBoolean(columnInfo[4]),String.valueOf(columnInfo[5]),String.valueOf(columnInfo[6]));
			}
			if(column!=null){
				columnList.add(column);
			}
			
		}
		if(!columnList.isEmpty()){
			return columnList ;
		}
	
		return null;
	}

	/*@SuppressWarnings("unchecked")
	@Override
	public Object convertFromString(Map arg0, String[] arg1, Class arg2) {
		
		String columnInfo[] = arg1[0].split(",");
		if(columnInfo.length == 5)
		{
			return new ColumnInfo(Integer.parseInt(columnInfo[0]),columnInfo[1],Integer.parseInt(columnInfo[2]),Short.parseShort(columnInfo[3]),Boolean.parseBoolean(columnInfo[4]));
		}
		else if(columnInfo.length == 6)
		{
			return new ColumnInfo(Integer.parseInt(columnInfo[0]),columnInfo[1],Integer.parseInt(columnInfo[2]),Short.parseShort(columnInfo[3]),Boolean.parseBoolean(columnInfo[4]),String.valueOf(columnInfo[5]));
		}else if(columnInfo.length == 7)
		{
			return new ColumnInfo(Integer.parseInt(columnInfo[0]),columnInfo[1],Integer.parseInt(columnInfo[2]),Short.parseShort(columnInfo[3]),Boolean.parseBoolean(columnInfo[4]),String.valueOf(columnInfo[5]),String.valueOf(columnInfo[6]));
		}
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String convertToString(Map arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}

*/

}
