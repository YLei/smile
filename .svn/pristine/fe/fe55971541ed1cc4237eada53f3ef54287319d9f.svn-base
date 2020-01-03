package com.emx.platform.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;
 
public class UploadUtils {
 
	/**
	 * 得到真实文件名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String subFileName(String fileName) {
		// 查找最后一个 \ (文件分隔符)位置
		int index = fileName.lastIndexOf("/");
		if (index == -1) {
			// 没有分隔符，说明是真实名称
			return fileName;
		} else {
			return fileName.substring(index + 1);
		}
	}
 
	/**
	 * 得到真实文件名(前缀名)
	 * 
	 * @param fileName
	 * @return
	 */
	public static String subFileNameNoExt(String fileName) {
		// 查找最后一个 \ (文件分隔符)位置
		int index = fileName.lastIndexOf("/");
		if (index == -1) {
			// 没有分隔符，说明是真实名称
			return fileName.substring(0,fileName.lastIndexOf("."));
		} else {
			fileName = fileName.substring(index + 1);
			return fileName.substring(0,fileName.lastIndexOf("."));
		}
	}
 
	
	/**
	 * 
	* @Description: 获得随机UUID文件名(加后缀)
	* @author yanglei
	* @date 2018年11月9日 下午2:27:36
	 */
	public static String generateRandonFileName(String fileName) {
		// 首先获得扩展名，然后生成一个UUID码作为名称，然后加上扩展名
		String ext = fileName.substring(fileName.lastIndexOf("."));
		return UUID.randomUUID().toString() + ext;
	}
	/**
	 * 
	* @Description: 获得后缀名
	* @author yanglei
	* @date 2018年11月9日 下午2:27:36
	 */
	public static String generateRandonFileExt(String fileName) {
		String ext = fileName.substring(fileName.lastIndexOf(".")+1);
		return ext;
	}
	/**
	 * 
	* @Description: 获得随机UUID文件名(无后缀)
	* @author yanglei
	* @date 2018年11月9日 下午2:27:36
	 */
	public static String generateRandonFileName() {
		return UUID.randomUUID().toString();
	}
 
	/**
	 * 获得hashcode 生成二级目录
	 * 
	 * @param uuidFileName
	 * @return
	 */
	public static String generateRandomDir(String uuidFileName) {
		int hashCode = uuidFileName.hashCode();// 得到它的hashcode编码
		// 一级目录
		int d1 = hashCode & 0xf;
		// 二级目录
		int d2 = (hashCode >> 4) & 0xf;
		return "/" + d1 + "/" + d2;
	}
	
	/**
     * 判断文件大小
     *
     * @param :multipartFile:上传的文件
     * @param size: 限制大小
     * @param unit:限制单位（B,K,M,G)
     * @return boolean:是否大于
     */
    public static boolean checkFileSize(MultipartFile multipartFile, int size, String unit) {
        long len = multipartFile.getSize();//上传文件的大小, 单位为字节.
        //准备接收换算后文件大小的容器
        double fileSize = 0;
        if ("B".equals(unit.toUpperCase())) {
            fileSize = (double) len;
        } else if ("K".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1024;
        } else if ("M".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1048576;
        } else if ("G".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1073741824;
        }
        //如果上传文件大于限定的容量
        if (fileSize > size) {
            return true;
        }
        return false;
    }
   
     /* 通过文件路径直接修改文件名
     * @param filePath    需要修改的文件的完整路径
     * @param newFileName 需要修改的文件的名称
     * @return
     */
    public static String FixFileName(String filePath, String newFileName) {
        File f = new File(filePath);
        if (!f.exists()) { // 判断原文件是否存在（防止文件名冲突）
            return null;
        }
        newFileName = newFileName.trim();
        if ("".equals(newFileName) || newFileName == null) // 文件名不能为空
            return null;
        String newFilePath = null;
        if (f.isDirectory()) { // 判断是否为文件夹
            newFilePath = filePath.substring(0, filePath.lastIndexOf("/")) + "/" + newFileName;
        } else {
            newFilePath = filePath.substring(0, filePath.lastIndexOf("/")) + "/" + newFileName
                    + filePath.substring(filePath.lastIndexOf("."));
        }
        File nf = new File(newFilePath);
        try {
            f.renameTo(nf); // 修改文件名
        } catch (Exception err) {
            err.printStackTrace();
            return null;
        }
        return newFilePath;
    }

    public static String [] getFileName(String path) {
        File file = new File(path);
        String[] fileName = file.list(new FilenameFilter() {
			public boolean accept(File f,String name) {
				return !name.contains("-out");
			}
		
		});
        return fileName;
    }
   
   /* public static void main(String[] args)
    {
    	Date date = new Date(); 
    	String path=new SimpleDateFormat("yyyy-MM-dd").format(date);
    	String surp = "E:\\home\\files\\declaration\\bghz_xml\\";
        String [] fileName = getFileName(surp+path);
        if(fileName !=null && fileName.length>0){
        	for(String name:fileName)
            {
                System.out.println(name);
            }
             
        }
        
    }*/
 /*
	public static void main(String[] args) {
		System.out.println(generateRandonFileName());
	}*/
	
}
