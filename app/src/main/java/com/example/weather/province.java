package com.example.weather;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.List;


class provinces {
    @JSONField(name = "status")
    private String status = null;
    @JSONField(name = "count")
    private String count = null;
    @JSONField(name = "info")
    private String info = null;
    @JSONField(name = "infocode")
    private String infocode = null;
    @JSONField(name="districts")
    private List<citys> districts = new ArrayList<>();

    public void setstatus(String status) {
        this.status = status;
    }
    public void setcount(String count) {
        this.count = count;
    }
    public void setinfo(String info) {
        this.info = info;
    }
    public void setinfocode(String infocode) {
        this.infocode = infocode;
    }
    public void setdistricts(List<citys> districts) {
        this.districts=new ArrayList<citys>(districts);
    }
    public String getstatus() { return status; }
    public String getcount() { return count; }
    public String getinfo() { return info; }
    public String getinfocode() { return infocode; }
    public List<citys> getdistricts() {
        return new ArrayList<citys>(this.districts);
    }

}
class citys{
    @JSONField(name = "citycode")
    private String citycode= null;
    @JSONField(name = "adcode")
    private String adcode= null;
    @JSONField(name = "name")
    private String name= null;
    @JSONField(name = "center")
    private String center= null;
    @JSONField(name = "level")
    private String level= null;

    public void setname(String name) {
        this.name = name;
    }
    public void setadcode(String adcode) {
        this.adcode = adcode;
    }
    public void setcitycode(String citycode) {
        this.citycode = citycode;
    }
    public void setlevel (String level) {
        this.level = level;
    }
    public void setcenter(String center) {  this.center = center; }
    public String getadcode() {
        return adcode;
    }
    public String getcitycode() {
        return citycode;
    }
    public String getname() { return name; }
    public String getcenter() {
        return center;
    }
    public String getlevel() {
        return level;
    }

    @JSONField(name="districts")
    private List<cityss> districtss = new ArrayList<>();
    public void setdistricts(List<cityss> districtss) {
        this.districtss=new ArrayList<cityss>(districtss);
    }
    public List<cityss> getdistrictss() {
        return new ArrayList<cityss>(this.districtss);
    }
}

class cityss{
    @JSONField(name = "citycode")
    private String citycode= null;
    @JSONField(name = "adcode")
    private String adcode= null;
    @JSONField(name = "name")
    private String name= null;
    @JSONField(name = "center")
    private String center= null;
    @JSONField(name = "level")
    private String level= null;

    public void setname(String name) {
        this.name = name;
    }
    public void setadcode(String adcode) {
        this.adcode = adcode;
    }
    public void setcitycode(String citycode) {
        this.citycode = citycode;
    }
    public void setlevel (String level) {
        this.level = level;
    }
    public void setcenter(String center) {  this.center = center; }
    public String getadcode() {
        return adcode;
    }
    public String getcitycode() {
        return citycode;
    }
    public String getname() { return name; }
    public String getcenter() {
        return center;
    }
    public String getlevel() {
        return level;
    }

}