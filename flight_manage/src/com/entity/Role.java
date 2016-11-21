/* 角色类
 * name:角色名称
 * remark:角色备注
 * authorityMap:角色包含的权限
 */
package com.entity;
import java.util.*;
public class Role {
	String name;
	String remark;
	Map<Authority,Boolean> authorityMap;
}
