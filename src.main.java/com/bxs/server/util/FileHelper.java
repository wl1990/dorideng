package com.bxs.server.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileHelper {

	/**
	 * 移动文件到指定目录
	 * 
	 * @param oldPath
	 *            String 如：c:/eer.txt
	 * @param newPath
	 *            String 如：d:/eer.txt
	 */
	public static void moveFile(String oldPath, String newPath) {
		System.out.println("移动的旧文件名 :　" + oldPath);
		System.out.println("移动的新文件名 :　" + newPath);
		copyFile(oldPath, newPath);
		delFile(oldPath);

	}

	/**
	 * 写入文件流到指定的文件。
	 * 
	 * @param filePath
	 * @param is
	 * @throws IOException
	 */
	public static File saveFile(String filePath, InputStream is)
			throws IOException {
		File f = new File(filePath);
		if (!f.exists()){
			try{
				f.createNewFile();
			}catch(IOException ex){
				System.out.println("文件创建错误,文件路径为："+filePath);
				ex.printStackTrace();
			}
		}
		FileOutputStream fos = new FileOutputStream(f);
		int end = 0;
		byte[] buffer = new byte[1024];
		while ((end = is.read(buffer)) != -1) {
			fos.write(buffer, 0, end);
		}
		is.close();
		fos.close();
		return f;
	}

	/**
	 * 复制单个文件
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/eee.txt
	 * @param newPath
	 *            String 复制后路径 如：f:/eee.txt
	 * @return boolean
	 */
	public static void copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					// System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
				fs.close();// 一定要关了
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();

		}

	}

	/**
	 * 删除文件
	 * 
	 * @param filePathAndName
	 *            String 文件路径及名称 如c:/eee.txt
	 * @param fileContent
	 *            String
	 * @return boolean
	 */
	public static void delFile(String filePathAndName) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			java.io.File myDelFile = new java.io.File(filePath);
			if (myDelFile.exists())
				myDelFile.delete();

		} catch (Exception e) {
			System.out.println("删除文件操作出错");
			e.printStackTrace();

		}

	}

	/**
	 * 分离完整文件名的文件名和后缀,并在中间加入字符串后返回
	 * 
	 * @param fullName
	 *            原始的文件名带后缀
	 * @param add
	 *            名称部分无后缀
	 * @return 新文件名
	 */
	public static String renameFileName(String fullName, String add) {
		String name = "";// 文件名
		String ext = ""; // 后缀
		char point = '.';
		int index = fullName.lastIndexOf(point);

		if (index != -1) {// 如果有后缀
			name = fullName.substring(0, index);
			ext = fullName.substring(index + 1);
		} else {
			name = fullName;
		}
		return add + point + ext;
	}

	/**
	 * 分离完整文件名的文件名和后缀,并在中间加入字符串后返回
	 * 
	 * @param fullName
	 *            原始的文件名带后缀
	 * @param add
	 *            名称部分无后缀
	 * @return [0] = 新文件名 , [1] = 后缀名
	 */
	public static String[] renameFileNameExt(String fullName, String add) {
		String name = "";// 文件名
		String ext = ""; // 后缀
		char point = '.';
		int index = fullName.lastIndexOf(point);

		if (index != -1) {// 如果有后缀
			name = fullName.substring(0, index);
			ext = fullName.substring(index + 1);
		} else {
			name = fullName;
		}
		String[] info = new String[2];
		info[0] = add + point + ext;
		info[1] = ext;
		return info;
	}

	/**
	 * 得到新文件名的信息
	 * 
	 * @param fullName
	 *            完整文件名
	 * @return 分隔成数组 [0]:文件名 , [1]:后缀
	 */
	public static String[] getFileInfo(String fullName) {
		String[] newFile = new String[2];
		String name = "";// 文件名
		String ext = ""; // 后缀
		char point = '.';
		int index = fullName.lastIndexOf(point);

		if (index != -1) {// 如果有后缀
			name = fullName.substring(0, index);
			ext = fullName.substring(index + 1);
		} else {
			name = fullName;
		}
		// return add + point + ext;
		newFile[0] = name;
		newFile[1] = ext;
		return newFile;
	}

	/**
	 * 创建目录
	 * 
	 * @param fileDirectory
	 *            绝对目录名
	 * @return
	 */
	public static boolean isExistsAndMkdir(String fileDirectory) {
		boolean b = false;
		File uploadDirectoryAll = new File(fileDirectory);
		if (!uploadDirectoryAll.exists()) {
			if (!uploadDirectoryAll.mkdir()) {
				System.out.println("无法创建完整存储目录!");
				b = false;
			} else {
				// log.debug("创建目录成功uploadDirectoryAll==>" +
				// uploadDirectoryAll);
				System.out.println("创建目录成功uploadDirectoryAll==>"
						+ uploadDirectoryAll);
				b = true;
			}
		} else {
			b = true;
		}
		return b;
	}
}
