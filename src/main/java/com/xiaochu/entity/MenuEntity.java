package com.xiaochu.entity;

/**
 * @author zhaodongchao
 * 2016年9月26日
 */
public class MenuEntity {
    private String menuId ;
    private String menuName ;
    private String menuUri ;
    private String menuType;
    private String menuLevel ;
    private Integer isLeaf ;
    private String createTime ;
    private MenuEntity parent ;
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuUri() {
		return menuUri;
	}
	public void setMenuUri(String menuUri) {
		this.menuUri = menuUri;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public String getMenuLevel() {
		return menuLevel;
	}
	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public MenuEntity getParent() {
		return parent;
	}
	public void setParent(MenuEntity parent) {
		this.parent = parent;
	}
	public Integer getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}
	@Override
	public String toString() {
		return "MenuEntity [menuId=" + menuId + ", menuName=" + menuName + ", menuUri=" + menuUri + ", menuType="
				+ menuType + ", menuLevel=" + menuLevel + ", isLeaf=" + isLeaf + ", createTime=" + createTime
				+ ", parent=" + parent.getMenuId() + "]";
	}
	
    
}
