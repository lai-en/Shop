package com.jt.sys.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.jt.sys.entity.SysLog;

/**
 * 提供数据分页查询所用到的方法。
 * @author Administrator
 *
 */
public interface SysLogDao {
	
	/**
	 * 基于id执行删除操作。
	 * @param ids
	 * @return
	 */
	int deleteObjects(@Param("ids") Integer... ids);
	
	/**
	 * 根据条件查询当前页数据
	 * @param name	查询条件
	 * @param startIndex	当前页的起始位置
	 * @param pageSize	当前页的页面大小
	 * @return
	 */
	List<SysLog> findPageObjects(
			@Param("username") String username,
			@Param("startIndex") Integer startIndex,
			@Param("pageSize") Integer pageSize);
	
	/**
	 * 查询的总记录数。
	 * @param name	查询条件
	 * @return
	 */
	int getRowCount(@Param("username") String username);
}
