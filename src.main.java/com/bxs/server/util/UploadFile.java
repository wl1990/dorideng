package com.bxs.server.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;




import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bxs.server.controller.BaseControl;


/**
 * 文件上传util
 * @author Administrator
 *
 */

public class UploadFile extends BaseControl{
	/**
	 * 上传图片 返回路径
	 * @param myfile
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static String uploadFiles(MultipartFile myfile,ServletContext context) throws FileNotFoundException, IOException{
		String fileNamePath="";
		if(myfile!=null){
			fileNamePath=getFileNamePath(myfile,context);
			File uploaded_File = new File(fileNamePath);
			FileUtils.copyInputStreamToFile(myfile.getInputStream(),uploaded_File);
			if(fileNamePath!=null && fileNamePath.contains("/upload"))
				fileNamePath=fileNamePath.split("/upload")[1];
			fileNamePath="/upload"+fileNamePath;
		}
		return fileNamePath==null?"":fileNamePath;
		
	}
	/**
	 * 根据上传文件的文件格式生成一个 '全路径文件名'
	 * 
	 * @return
	 */
	public  static String getFileNamePath(MultipartFile uploadedFile,ServletContext context) {
		String dataPath= "/upload";
		File dFile = new File(context.getRealPath(dataPath));
		if (!dFile.exists()) {
			dFile.mkdir();
		}
//		dFile = new File(context.getRealPath(dataPath + "/data"));
//		if (!dFile.exists()) {
//			dFile.mkdir();
//		}

		String fileNamePath = dFile.getAbsolutePath();
		String fileName = UUIDUtil.createUUID();
		String postfix = uploadedFile.getOriginalFilename().substring(
				uploadedFile.getOriginalFilename().lastIndexOf(".") + 1);
		fileNamePath = (fileNamePath + "/" + fileName + "." + postfix).replace(
				"\\", "/").replace("/", "/");
		return fileNamePath;
	}
	
	/**
	 * MultipartFile to File
	 */
	public static File MultipartFileToFile(MultipartFile myfile,ServletContext context)throws FileNotFoundException, IOException{
		String fileNamePath="";
		if(myfile!=null){
			fileNamePath=getFileNamePath(myfile,context);
			File uploaded_File = new File(fileNamePath);
			myfile.transferTo(uploaded_File);
			if(uploaded_File!=null)
				return uploaded_File;
		}
		return null;
	}
}
