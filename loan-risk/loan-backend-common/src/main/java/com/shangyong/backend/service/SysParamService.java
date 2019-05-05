package com.shangyong.backend.service;

import com.shangyong.backend.entity.SysParam;

public interface SysParamService {

	SysParam queryByParamValue(String bqsClwebToken);

}
