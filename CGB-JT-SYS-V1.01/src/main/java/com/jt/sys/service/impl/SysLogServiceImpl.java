package com.jt.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.vo.PageObject;
import com.jt.sys.common.exception.ServiceException;
import com.jt.sys.dao.SysLogDao;
import com.jt.sys.entity.SysLog;
import com.jt.sys.service.SysLogService;

@Service
public class SysLogServiceImpl implements SysLogService {

	@Autowired
	private SysLogDao sysLogDao;

	@Override
	public PageObject<SysLog> findPageObjects(String name, Integer pageCurrent) {
		// 1.验证参数合法性
		// 1.1验证pageCurrent的合法性，
		if (pageCurrent == null || pageCurrent < 1) {
			// 不合法抛出IllegalArgumentException异常
			throw new IllegalArgumentException("页码不正确");
		}
		// 2.基于条件查询总记录数
		// 2.1) 执行查询
		int rowCount = sysLogDao.getRowCount(name);
		// 2.2) 验证查询结果，假如结果为0不再执行如下操作
		if (rowCount == 0) {
			throw new ServiceException("没有记录");
		}

		// 3.基于条件查询当前页记录(pageSize定义为5)
		// 3.1)定义pageSize
		int pageSize = 5;
		// 3.2)计算startIndex
		int startIndex = (pageCurrent - 1) * pageSize;

		// 3.3)执行当前数据的查询操作
		List<SysLog> records = sysLogDao.findPageObjects(name, startIndex, pageSize);

		// 4.对分页信息以及当前页记录进行封装
		// 4.1)构建PageObject对象
		PageObject<SysLog> pageObject = new PageObject<SysLog>();
		// 4.2)封装数据
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
		pageObject.setRecords(records);
		pageObject.setRowCount(rowCount);
		// 5.返回封装结果。
		return pageObject;
	}

	@Override
	public int deleteObjects(Integer... ids) {
		if(ids == null || ids.length == 0) throw new IllegalArgumentException("请选择删除记录");
		int rows=0;
		try {
			rows = sysLogDao.deleteObjects(ids);
		} catch (Throwable e) {
			e.printStackTrace();
			throw new ServiceException("系统出现了故障，请稍后重试。");
		}
		if(rows==0) throw new ServiceException("记录可以已经不存在了。");
		return rows;
	}

}
