package com.jt.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jt.common.vo.Node;
import com.jt.sys.entity.SysMenu;

public interface SysMenuDao {
	/**
	 * 查询所有菜单信息
	 * @return
	 */
	List<Map<String, Object>> findObjects();
	
	/**
	 * 根据id判断当前菜单的子菜单数量。
	 * @param id	菜单id
	 * @return	子菜单数量
	 */
	Integer getChildCount(@Param("id") Integer id);
	
	/**
	 * 根据菜单的id删除菜单
	 * @param id	菜单id
	 * @return	删除的菜单id
	 */
	Integer deleteObject(@Param("id") Integer id);

	/**
	 * 查询所有菜单的id，parentId，菜单名。
	 * @return
	 */
	List<Node> findZTreeMenuNodes();
	
	/**
	 * 添加菜单信息
	 * 参数不加@param注解，因为mybaits会自动调用传过来对象的get和set方法。
	 */
	int insertObject(SysMenu entity);
	
	/**
	 * 跟新菜单
	 * @param entity
	 * @return
	 */
	int updateObject(SysMenu entity);
}
