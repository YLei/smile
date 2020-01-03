package com.emx.platform.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;

public class Base64FileUtils {
	public static  String decryptByBase64(String base64, String filePath,String file) {
		if (base64 == null && file == null) {
            return "生成文件失败，请给出相应的数据。";
		}
		//如果不存在,创建文件夹
		File f = new File(filePath);
		if(!f.exists()){
		    f.mkdirs(); 
		}
		try {
			Files.write(Paths.get(file), Base64.getDecoder().decode(base64),StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "指定路径下生成文件成功！";
	}
}
