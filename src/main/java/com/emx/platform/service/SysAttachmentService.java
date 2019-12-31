package com.emx.platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.emx.platform.common.AttachmentContext;
import com.emx.platform.dao.SysAttachmentMapper;
import com.emx.platform.domain.SysAttachment;
import com.emx.platform.params.ParamPage;
import com.emx.platform.utils.UploadUtils;
import com.emx.platform.vo.AttachmentEntityVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class SysAttachmentService {
	
	@Value("${img.location}")
    private String url; 
	@Autowired
	private SysUploadService uploadService; 
	@Autowired
	private SysAttachmentMapper AttachmentMapper;
	
	@Transactional(rollbackFor=Exception.class)
	public SysAttachment save(MultipartFile file,SysAttachment sysAttachment) throws Exception {
		try{
			String oldName = file.getOriginalFilename();
	        String fileName = UploadUtils.generateRandonFileName();
	        String fileExt = UploadUtils.generateRandonFileExt(oldName);
	        String fileNameExt = fileName + "." + fileExt;
	        sysAttachment.setOldFileName(oldName);
			sysAttachment.setFileName(fileName);
			sysAttachment.setFileType(fileExt);
			sysAttachment.setFileSize(file.getSize() + "");
			sysAttachment.setUrl(AttachmentContext.DIR_ICON + sysAttachment.getFileDir() + "/"+fileNameExt);
			AttachmentMapper.insertSelective(sysAttachment);
			uploadService.upload(file, url+sysAttachment.getFileDir(), fileNameExt);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return sysAttachment;
	}

	public List<SysAttachment> selectByEntityId(String id) {
		List<SysAttachment> attachment = AttachmentMapper.selectByEntityId(id);
		return attachment;
	}

	public SysAttachment selectByPrimaryId(String id) {
		SysAttachment attachment = AttachmentMapper.selectByPrimaryKey(id);
		return attachment;
	}

	@Transactional(rollbackFor=Exception.class)
	public int deleteById(String id) throws Exception{
		SysAttachment attachment = AttachmentMapper.selectByPrimaryKey(id);
		int count = AttachmentMapper.deleteByPrimaryKey(id);
		if(count >0){
			uploadService.delByUrl(attachment.getFileDir()+"/"+attachment.getFileName()+"."+attachment.getFileType());
		}
		return count;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public int deleteByIds(String[] ids,String userId) throws Exception{
		List<SysAttachment> atts =  AttachmentMapper.selectByIds(ids);
		int count = AttachmentMapper.deleteByIds(ids);
		if(count >0){
			for(SysAttachment att:atts) {
				uploadService.delByUrl(att.getFileDir() + "/" + att.getFileName() + "." + att.getFileType());
			}
		}
		return count;
	}

	public List<AttachmentEntityVo> selectByContractId(String id) {
		List<AttachmentEntityVo> attachment = AttachmentMapper.selectByContractId(id);
		return attachment;
	}

	public PageInfo<AttachmentEntityVo> selectByOrderId(ParamPage pageParam) {
		PageHelper.startPage(pageParam.getRow(),pageParam.getPagesize());
		PageInfo<AttachmentEntityVo> pageInfo = new PageInfo<>(AttachmentMapper.selectByContractId(pageParam.getOrderId()));
		return pageInfo;
	}
}
