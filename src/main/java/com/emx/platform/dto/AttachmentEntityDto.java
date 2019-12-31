package com.emx.platform.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class AttachmentEntityDto {
	private String oldFileName;
	private String fileSize;
	private String uploadName;
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private Date uploadTime;
	private String attachmentId;//文件的id
	
	private String fileName;//该字段暂时用不到
	
}
