package com.jt.sys.service;

import com.jt.common.vo.PageObject;
import com.jt.sys.entity.SysLog;

public interface SysLogService {
	/**
	 * 
	 * @param ids 要删除的id
	 * @return 删除的行数
	 */
	int deleteObjects(Integer... ids);
	
	PageObject<SysLog> findPageObjects(String name, Integer pageCurrent);
}
