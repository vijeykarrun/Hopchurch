package com.hopchurch.Pojo;

import java.util.Date;

public class Person {
    private String BabyName;
    private String Name;
    private String Password;
    private String familyName;
    private Date lmpDate;
    private String parent;
    private String partnerName;
    private String phonenumber;
    private Date registerdate;
    private String userName;
    private int week;
    private String zone;

    public void setName(String name) {
        this.Name = name;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPartnerName() {
        return this.partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getBabyName() {
        return this.BabyName;
    }

    public void setBabyName(String babyName) {
        this.BabyName = babyName;
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Date getLmpDate() {
        return this.lmpDate;
    }

    public void setLmpDate(Date lmpDate) {
        this.lmpDate = lmpDate;
    }

    public Date getRegisterdate() {
        return this.registerdate;
    }

    public void setRegisterdate(Date registerdate) {
        this.registerdate = registerdate;
    }

    public String getPassword() {
        return this.Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getParent() {
        return this.parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getWeek() {
        return this.week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getName() {
        return this.Name;
    }

    public String getPhonenumber() {
        return this.phonenumber;
    }

    public String getZone() {
        return this.zone;
    }
}
