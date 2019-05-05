package com.shangyong.backend.controller;

import com.shangyong.backend.bo.SysDictionaryBo;
import com.shangyong.backend.service.ScSystemDictService;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.exception.CalfException;
import com.shangyong.utils.ResultUtils;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SysDictController {
	
	private Logger logger = Logger.getLogger(SysDictController.class);
	
	@Autowired
	private ScSystemDictService systemDictionaryService;

	@RequestMapping(value="/backend/sysDict/list")
	public Object list(String codes, String flush, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isBlank(codes)) {
				throw new CalfException(CodeUtils.BACKEND_PRA_MISS.getCode(), CodeUtils.BACKEND_PRA_MISS.getMessage());
			}
			Map params = new HashMap();
			params.put("codes", codes);
			if(StringUtils.isNotBlank(flush)){
				params.put("flush", Integer.valueOf(flush)); //1 刷新缓存   0 或空 默认走缓存查询
			}
			Map<String, SysDictionaryBo> list = systemDictionaryService.list(params);
			return ResultUtils.resultSuccess(list);
		} catch(Exception e) {
			logger.info("-->>/backend/sysDict/list--error", e);
			if (e instanceof CalfException) {
				return ResultUtils.resultFail((CalfException)e);
			}
		}
		return ResultUtils.resultError();
	}
}
