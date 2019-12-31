package com.emx.platform.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emx.platform.common.ResultEnum;
import com.emx.platform.common.TableContext;
import com.emx.platform.domain.SysAuthority;
import com.emx.platform.params.ParamId;
import com.emx.platform.service.SysAuthorityService;
import com.emx.platform.utils.Result;
import com.emx.platform.utils.ResultUtil;
import com.emx.platform.utils.StringUtil;
import com.emx.platform.vo.AuthorityListVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@RestController
@RequestMapping("/authority")
@Api(value="权限接口相关",tags={"权限接口相关"})
public class SysAuthorityController {
	
	@Autowired private SysAuthorityService authorityService;

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "查询所有权限")
	@PostMapping(value = "/list")
	public Result<SysAuthority> list(@RequestBody ParamId pid) {
		try {
			List<AuthorityListVo> authorityPage = authorityService.selectByAuthorityPid(pid.getIdInteger());
			for (AuthorityListVo authorityListDto : authorityPage) {
				List<AuthorityListVo> authorityListDtoHasChild = authorityService.selectByAuthorityPid(authorityListDto.getAuthId());
				if (StringUtil.isNotEmpty(authorityListDtoHasChild) && authorityListDtoHasChild.size() > 0) {
					authorityListDto.setHasChild(1);
				} else {
					authorityListDto.setHasChild(0);
				}
			}
			Map<Object, Object> map = new HashMap<>();
			map.put("authorityPage", authorityPage);
			return ResultUtil.success(map);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return ResultUtil.error(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg());
	}
}
