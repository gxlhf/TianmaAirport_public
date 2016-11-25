/* 角色类
 * name:角色名称
 * description:角色描述
 * authorityMap:角色包含的权限，键为权限名称，值为该角色是否包含此权限的布尔值。若该角色包含此权限，则值为true，否则为false
 */
package com.entity;
import java.util.*;
public class Role {
	String name;
	String description;
	Map<String,Boolean> authorityMap;
	public Role(String name) {
		this.name = name;
		/*
		 * String roleDescriptionSearch(String name);
		 * 数据库操作：查询角色名为name的角色描述
		 * 形参为角色名称，返回值为角色描述
		 */
		//this.description = roleDescriptionSearch(name);
		/*
		 * Map<String,Boolean> roleAuthorityMapSearch(String name);
		 * 数据库操作：查询角色名为name的角色包含的权限
		 * 形参为角色名称，返回值为Map<String,Boolean>
		 */
		//this.authorityMap = roleAuthorityMapSearch(name);
		
	}
}
