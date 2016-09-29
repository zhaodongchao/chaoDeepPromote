package com.base.export.entity;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * @author Huang Xiaohua
 */

public class ColumnInfo {
	
	public static final short TYPE_STRING = 1;
	public static final short TYPE_INT = 2;
	public static final short TYPE_DOUBLE = 3;
	public static final short TYPE_DATE = 4;
	public static final short TYPE_SHORT = 5;
	public static final short TYPE_BOOLEAN = 6;
	public static final short TYPE_BIGDECIMAL = 7 ;
	public static final short TYPE_NONE = 8 ;
	public static final short TYPE_UNKNOW = 9 ;
	public static final short TYPE_PERCENT = 10;
	
	private HSSFFont FONT ;
	/**
	 * 构造函数
	 * @param index:序号
	 * @param colName：列名
	 */
	public ColumnInfo(int index,String colName)
	{
		this.index = index;
		this.colName = colName;
	}
	
	/**
	 * 构造函数
	 * @param index:序号
	 * @param colName:列名
	 * @param colWidth:列宽,默认100
	 */
	public ColumnInfo(int index,String colName,int colWidth)
	{
		this.index = index;
		this.colName = colName;
		this.colWidth = colWidth;
	}
	
	/**
	 * 构造函数
	 * @param index:序号
	 * @param colName:列名
	 * @param colWidth:列宽,默认100
	 * @param colDataType:数据类型
	 */
	public ColumnInfo(int index,String colName,int colWidth,short colDataType)
	{
		this.index = index;
		this.colName = colName;
		this.colWidth = colWidth;
		this.colDateType = colDataType;
	}
	/**
	 * 构造函数
	 * @param index:序号
	 * @param colName:列名
	 * @param colWidth:列宽,默认100
	 * @param colDataType:数据类型
	 * @param colAlign:列对齐方式
	 */
	public ColumnInfo(int index,String colName,int colWidth,short colDataType,short colAlign )
	{
		this.index = index;
		this.colName = colName;
		this.colWidth = colWidth;
		this.colDateType = colDataType;
		this.colAlign = colAlign ;
	}
	
	
	
	/**
	 * 构造函数
	 * @param index:序号
	 * @param colName:列名
	 * @param colWidth:列宽,默认100
	 * @param colAlign:列对齐方式
	 * @param colHidden:是否隐藏列
	 */
	public ColumnInfo(int index,String colName,int colWidth,short colAlign,boolean colHidden )
	{
		this.index = index;
		this.colName = colName;
		this.colWidth = colWidth;
		this.colAlign = colAlign ;
		this.colHidden = colHidden;
	}
	
	/**
	 * 构造函数
	 * @param index:序号
	 * @param colName:列名
	 * @param colWidth:列宽,默认100
	 * @param colDataType:数据类型
	 * @param colAlign:列对齐方式
	 * @param colHidden:是否隐藏列
	 */
	public ColumnInfo(int index,String colName,int colWidth,short colAlign,boolean colHidden,String colDataType )
	{
		this.index = index;
		this.colName = colName;
		this.colWidth = colWidth;
		this.colAlign = colAlign ;
		this.colHidden = colHidden;
		
		if("Percent".equalsIgnoreCase(colDataType))
			this.colDateType = TYPE_PERCENT;
		else if("Integer".equals(colDataType))
			this.colDateType = TYPE_INT;
		else 
			this.colDateType = TYPE_UNKNOW;
		
	}
	/**
	 * 构造函数
	 * @param index:序号
	 * @param colName:列名
	 * @param colWidth:列宽,默认100
	 * @param colDataType:数据类型
	 * @param colAlign:列对齐方式
	 * @param colHidden:是否隐藏列
	 */
	public ColumnInfo(int index,String colName,int colWidth,short colAlign,boolean colHidden,String colDataType,String exportTab)
	{
		this.index = index;
		this.colName = colName;
		this.colWidth = colWidth;
		this.colAlign = colAlign ;
		this.colHidden = colHidden;
		this.exportTab = exportTab ;
		if("Percent".equalsIgnoreCase(colDataType))
			this.colDateType = TYPE_PERCENT;
		else if("Integer".equals(colDataType))
			this.colDateType = TYPE_INT;
		else 
			this.colDateType = TYPE_UNKNOW;
		
	}
	/**
	 * 多表导出时的标示符
	 */
    public String exportTab ;
	/**
	 * 列序号
	 */
	public int index = 0;	
	
	/**
	 * 列名
	 */
	public String colName = "undefined";
	
	/**
	 * 列宽
	 */
	public int colWidth = 150;	 
	
	/**
	 * 列对齐方式
	 */
	public short colAlign = org.apache.poi.hssf.usermodel.HSSFCellStyle.ALIGN_LEFT;
	
	/**
	 * 列是否隐藏
	 */
	public boolean colHidden = false;
		
	/**
	 * 合计值
	 */
	public double sumValue =0;
	
	/**
	 * 列数字型格式("#,##0.00")
	 */
	public short colNumericDataFomate = 4;

	/**
	 * 列普通型("General")
	 */
	public short colGeneralDataFomate = 0;
	
	/**
	 * 列日期型("d-mmm-yy")
	 */
	public short colDateDataFomate = 0xe;
	
	/**
	 * 百分比0.00%
	 */
	public String colPercentDataFomate = "0.00%";
	/**
	 * 列数据类型,默认String型
	 * @详细信息
	 * 1:String
	 * 2:int
	 * 3:double
	 * 4:Date
	 * 5:short
	 * 6:boolean
	 * 7:bigDecimal
	 * 8:none
	 * 9:unknow
	 */
	public short colDateType = TYPE_NONE;
	
	
	/**
	 * 每列的样式
	 */
	private HSSFCellStyle cellStyle;
	
	/**
	 * 列头对齐方式
	 */
	public short headAlign = org.apache.poi.hssf.usermodel.HSSFCellStyle.ALIGN_CENTER;
	
	/**
	 * 列头文字格式("General")
	 */
	public short headStringDataFomate = 0;
	
	/**
	 * 列头行高
	 */
	public short headRowHeight = 15;
	
	/**
	 * 列头字体
	 */
	public HSSFFont getHeadFont(HSSFWorkbook wb){
		if(FONT == null) {
			FONT = wb.createFont() ;
			FONT.setFontHeightInPoints((short)12);
			FONT.setFontName("宋体");
			FONT.setBoldweight(org.apache.poi.hssf.usermodel.HSSFFont.BOLDWEIGHT_BOLD);
			FONT.setItalic(false);
			FONT.setStrikeout(false);
		}
	    return FONT;
	}
	
	
	/**
	 * 每列的样式
	 */
	public HSSFCellStyle getCellStyle() {
		return cellStyle;
	}
	/**
	 * 每列的样式
	 */
	public void setCellStyle(HSSFCellStyle cellStyle) {
		this.cellStyle = cellStyle;
	}

	
   
}
