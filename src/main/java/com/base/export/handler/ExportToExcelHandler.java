package com.base.export.handler;

import java.io.File;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author zhaodongchao 2016年9月28日
 */
@RequestMapping(value = { "/sm/base/" })
@Controller
public class ExportToExcelHandler {
	private final static String DEFAULT_PATH_NAME = "D:" + File.separator + "zhao";
	private String pathName = DEFAULT_PATH_NAME;

	@RequestMapping(value = { "export/{fileId}" }, method = RequestMethod.GET)
	public ResponseEntity<byte[]> export2Excel(HttpServletResponse response,
			@PathVariable(value = "fileId") String fileId) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		String fileName = obtainFileName(fileId);
		String absolutePath = generateAbsoluteFilePath(fileName);
		String downloadFileName = generateDownloadFileName(fileName) ;
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentLength(new File(absolutePath).length());
		headers.setContentDispositionFormData("attachment",new String(downloadFileName.getBytes("gbk"),"iso-8859-1"));
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(absolutePath)), headers,HttpStatus.CREATED);
	}

	/**
	 * 获取要下载的文件的全名
	 * @param fileId 文件包含的唯一主键ID
	 * @return
	 * @throws Exception
	 */
	private String obtainFileName(String fileId) throws Exception {
		Iterator<File> tmpFiles = FileUtils.iterateFiles(new File(DEFAULT_PATH_NAME), FileFileFilter.FILE, null);
		while (tmpFiles.hasNext()) {
			File file = (File) tmpFiles.next();
			String fileName = file.getName();
			if (fileName.indexOf(fileId) != -1) {
				return fileName;
			}
		}
		return null;
	}
	/**
	 * 根据文件名按照配置生成文件的绝对路径
	 * @param fileName 文件名
	 * @return
	 */
    private String generateAbsoluteFilePath(String fileName){
    	return pathName+File.separator+fileName ;
    }
    /**
     * 生成下载的文件的名称
     * @param fileName 被下载的资源的文件名
     * @return
     * @throws Exception
     */
    private String generateDownloadFileName(String fileName)throws Exception{
    	int index = fileName.indexOf("$");
    	if(index!=-1){
	     	return fileName.substring(0,index)+".xls";
    	}
    	return null ;
    }
	public String getPathName() {
		return pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

}
