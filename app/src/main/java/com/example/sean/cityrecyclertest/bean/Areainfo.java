package com.example.sean.cityrecyclertest.bean;


/**
 * @author xin
 * @date 2016-9-12
 */
public class Areainfo {

    private int AreaId;
    private int ParentId;
    private String AreaName;
    public Areainfo() {
        super();
    }
    public Areainfo(int areaId, int parentId, String areaName) {
        super();
        AreaId = areaId;
        ParentId = parentId;
        AreaName = areaName;
    }
    public int getAreaId() {
        return AreaId;
    }
    public void setAreaId(int areaId) {
        AreaId = areaId;
    }
    public int getParentId() {
        return ParentId;
    }
    public void setParentId(int parentId) {
        ParentId = parentId;
    }
    public String getAreaName() {
        return AreaName;
    }
    public void setAreaName(String areaName) {
        AreaName = areaName;
    }
    @Override
    public String toString() {
        return "CjwdAreainfo [AreaId=" + AreaId + ", AreaName=" + AreaName
                + ", ParentId=" + ParentId + "]";
    }


}
