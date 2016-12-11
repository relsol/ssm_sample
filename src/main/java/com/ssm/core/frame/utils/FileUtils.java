package com.ssm.core.frame.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ssm.core.frame.common.GlobalConfigure;

public class FileUtils {
	/**
	 * 获取文件的后缀名
	 * @param fileName 文件名
	 * @return 文件后缀名,例如: .txt
	 */
	public static String getFileExtension(String fileName){
		if(StringUtils.isEmpty(fileName)){
			throw new RuntimeException("文件名称不能为空!");
		}
		int index = fileName.lastIndexOf(".");
		if(index == -1){
			throw new RuntimeException("文件名没有后缀名!");
		}
		return fileName.substring(index);
	}
	
	/**
	 * 获取文件名(不含后缀)
	* @Description: 
	* @param fileName
	* @return 
	* String 
	* @throws
	 */
	public static String getFileName(String fileName){
		if(StringUtils.isEmpty(fileName)){
			throw new RuntimeException("文件名称不能为空!");
		}
		int index = fileName.lastIndexOf(".");
		if(index == -1){
			throw new RuntimeException("文件名没有后缀名!");
		}
		return fileName.substring(0,index);
	}
	
	/**
	 * 上传文件到指定的目录中
	 * @param dirPath			上级目录
	 * @param originalFileName	原文件名
	 * @param targetFileName	目标文件名
	 * @param data				文件字节数据
	 * @return 图片可访问URL: /XXXXXX/XXXXXX.jpg
	 * @throws IOException
	 */
	public static String uploadFile(String dirPath, String originalFileName, String targetFileName, byte[] data) throws IOException {
		File localFile = new File(dirPath);
		if(!localFile.exists()){
			localFile.mkdirs();
		}
		StringBuilder builder = new StringBuilder();
		builder.append(dirPath);
		if(targetFileName == null || targetFileName.length() == 0){
			builder.append(System.currentTimeMillis()).append(getFileExtension(originalFileName));
		} else {
			builder.append(targetFileName);
		}
		targetFileName = builder.toString();
		org.apache.commons.io.FileUtils.writeByteArrayToFile(new File(targetFileName), data);
		
		String picUrl = targetFileName.replace(GlobalConfigure.FILE_SERVER_LOCAL_PATH, "");
		picUrl = picUrl.replaceAll("\\\\", "/");
		return picUrl;
	}
	
	public static String convertUrl2LocalPath(String url) {
		if (StringUtils.isBlank(url)) {
			throw new IllegalArgumentException("缺少必要的参数!");
		}
		String os = System.getProperties().getProperty("os.name").toLowerCase();
		if(StringUtils.startsWithIgnoreCase(os, "win")){
			url = GlobalConfigure.FILE_SERVER_LOCAL_PATH + url.replaceAll("/", "\\\\");
		} else {
			url = GlobalConfigure.FILE_SERVER_LOCAL_PATH + url;
		}
		return url;
	}
	
	/**
	 * 上传文件到指定目录
	 * @param file  文件流
	 * @param folder 文件需要保存的文件上级目录
	 * @return
	 * @throws IOException
	 */
	public static String uploadFile(MultipartFile file, String folder) throws IOException {
		//路径
		StringBuilder dirBuilder = new StringBuilder();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date theDate = new Date();
//		folder = StringUtils.defaultString(folder, "---");
		dirBuilder.append(GlobalConfigure.FILE_SERVER_LOCAL_PATH).append(File.separator)
			.append(folder).append(File.separator).append(df.format(theDate)).append(File.separator);
		//文件名
		String uri = FileUtils.uploadFile(dirBuilder.toString(), 
					file.getOriginalFilename(), null, file.getBytes());
		return uri;
	}
}
