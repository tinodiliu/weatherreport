package com.example.weather;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.List;

public class tianqi {
    @JSONField(name = "status")
    private String status = null;
    @JSONField(name = "count")
    private String count = null;
    @JSONField(name = "info")
    private String info = null;
    @JSONField(name = "infocode")
    private String infocode = null;
    @JSONField(name="lives")
    private List<Live> lives = new ArrayList<>();

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
    public void setlives(List<Live> live) {
        this.lives=new ArrayList<Live>(live);
    }
    public String getstatus() { return status; }
    public String getcount() { return count; }
    public String getinfo() { return info; }
    public String getinfocode() { return infocode; }
    public List<Live> getlives() {
        return new ArrayList<Live>(this.lives);
    }

}
class Live{
    @JSONField(name = "province")
    private String province= null;
    @JSONField(name = "city")
    private String city= null;
    @JSONField(name = "adcode")
    private String adcode= null;
    @JSONField(name = "weather")
    private String weather= null;
    @JSONField(name = "temperature")
    private String temperature= null;
    @JSONField(name = "winddirection")
    private String winddirection= null;
    @JSONField(name = "windpower")
    private String windpower= null;
    @JSONField(name = "humidity")
    private String humidity= null;
    @JSONField(name = "reporttime")
    private String reporttime= null;

    public void setcity(String city) {
        this.city = city;
    }
    public void setadcode(String adcode) {
        this.adcode = adcode;
    }
    public void setprovince(String province) {
        this.province = province;
    }
    public void setreporttime(String reporttime) {
        this.reporttime = reporttime;
    }
    public void setwinddirection (String winddirection) {
        this.winddirection = winddirection;
    }
    public void settemperature(String temperature) {  this.temperature = temperature; }
    public void setwindpower(String windpower) {
        this.windpower = windpower;
    }
    public void setweather(String weather) {
        this.weather = weather;
    }
    public void sethumidity(String humidity) {
        this.humidity = humidity;
    }
    public String getcity() {
        return city;
    }
    public String getadcode() {
        return adcode;
    }
    public String getprovince() {
        return province;
    }
    public String getreporttime() {
        return reporttime;
    }
    public String getweather() { return weather; }
    public String gettemperature() {
        return temperature;
    }
    public String getwindpower() {
        return windpower;
    }
    public String getwinddirection() {
        return winddirection;
    }
    public String gethumidity() {
        return humidity;
    }

}