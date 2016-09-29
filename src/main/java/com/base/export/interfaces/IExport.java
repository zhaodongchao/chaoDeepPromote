package com.base.export.interfaces;
 

/**
 * @author Huang Xiaohua
 * @version 1.0.1 
 *	
 * 异常处理有可改进之处
 */
public interface IExport {	
	
	
	/**
	 * 画表头,成功返回非负值，失败返回 ERR_DRAWTITLE
	 */
	int drawTitle();
	
	/**
	 * 画列头,成功返回非负值，失败返回 ERR_DRAWHEAD
	 */
	int drawHead();
	
	/**
	 * 画数据块,成功返回非负值，失败返回 ERR_DRAWMAINDATABLOCK
	 */
	int drawMainDataBlock();
	
	/**
	 * 画表尾,成功返回非负值，失败返回 ERR_DRAWFOOTER
	 */
	int drawFooter();
	
	/**
	 * 导出,成功返回非负值
	 */
	int Export();
}
