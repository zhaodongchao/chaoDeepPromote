package com.base.export.typeconversion;
/**
 * @author zhaodongchao
 */
import org.springframework.core.convert.converter.Converter;

import com.base.export.entity.TableHeadInfo;


public class TableHeadInfoTypeConversion implements Converter<String[],TableHeadInfo> {

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public TableHeadInfo convert(String[] source) {
		// TODO Auto-generated method stub
		return null;
	}

	/*@SuppressWarnings("unchecked")
	@Override
	public Object convertFromString(Map arg0, String[] arg1, Class arg2) {
		
		String tableHeadInfo[] = arg1[0].split(",");
		//对象转换,添加时注意与TableHeadInfo 类初始化函数的同步
		if(tableHeadInfo.length == 7)
		{
			return new TableHeadInfo(Integer.parseInt(tableHeadInfo[0]),Integer.parseInt(tableHeadInfo[1]),Integer.parseInt(tableHeadInfo[2]),Integer.parseInt(tableHeadInfo[3]),Integer.parseInt(tableHeadInfo[4]),Short.parseShort(tableHeadInfo[5]),String.valueOf(tableHeadInfo[6]));
		}
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String convertToString(Map arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}*/



}

