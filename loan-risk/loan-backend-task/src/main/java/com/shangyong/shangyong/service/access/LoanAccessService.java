package com.shangyong.shangyong.service.access;

import com.shangyong.backend.entity.Application;

/**
 * 准入 业务层
 * @author caisheng
 * @date 2018-08-12
 */
public interface LoanAccessService {

    /**
     * 准入
     * @param application
     * @return
     */
    public boolean processApplication(Application application);
}
