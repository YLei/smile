package com.emx.platform.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.emx.platform.dao.SysAttachmentMapper;
import com.emx.platform.domain.SysAttachment;

@Service
public class SysUploadService {
	
	@Autowired private SysAttachmentMapper attachMapper;
	@Value("${img.location}")
	private String location;

	public void upload(MultipartFile multipartFile, String path, String fileName) {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		FileInputStream fileInputStream = null;
		BufferedOutputStream bos = null;

		try {
			fileInputStream = (FileInputStream) multipartFile.getInputStream();
			bos = new BufferedOutputStream(new FileOutputStream(path + File.separator + fileName));
			byte[] bs = new byte[1024];
			int len;
			while ((len = fileInputStream.read(bs)) != -1) {
				bos.write(bs, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.flush();
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}

	public boolean downloadAtt(HttpServletResponse response,String id) {
		SysAttachment att = attachMapper.selectByPrimaryKey(id);
		if(att == null){
			return false;
		}
		String path = att.getFileDir()+"/"+att.getFileName()+"."+att.getFileType();
		File file = new File(location + path);
        //File file = new File(realPath , fileName);
        if (file.exists()) {
        	Updownload(response,file,att.getOldFileName());
        	return true;
        }
		return false;
	}
	
	public void Updownload(HttpServletResponse response,File file,String fileName) {
		FileInputStream fis = null;
        BufferedInputStream bis = null;	
        byte[] buffer = new byte[1024];
		try {
			response.setCharacterEncoding("UTF-8");
			response.setHeader("content-Type","application/force-download");// 设置强制下载不打开
			//response.setContentType("multipart/form-data");
			response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
			// 设置文件
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}

	public void delByUrls(String[] urls) {
		try {
			for(String url:urls){
				File file = new File(location+url);
			if (file.exists()) {
					file.delete();
				}
			}
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public void delByUrl(String url) {
		try {
			File file = new File(location+url);
			if (file.exists()) {
				file.delete();
			}
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
