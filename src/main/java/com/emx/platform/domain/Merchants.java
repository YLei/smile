package com.emx.platform.domain;

import java.io.Serializable;
import java.util.Date;

public class Merchants implements Serializable {
    private String id;

    private String merchantsPhone;

    private String password;

    private String merchantsName;

    private String contact;

    private String email;

    private String city;

    private String address;

    private String businessCategory;

    private String signStat;

    private String salesman;

    private String superiors;

    private String stat;

    private Date expirationTime;

    private String creator;

    private Date createTime;

    private String operator;

    private Date operateTime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMerchantsPhone() {
        return merchantsPhone;
    }

    public void setMerchantsPhone(String merchantsPhone) {
        this.merchantsPhone = merchantsPhone == null ? null : merchantsPhone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getMerchantsName() {
        return merchantsName;
    }

    public void setMerchantsName(String merchantsName) {
        this.merchantsName = merchantsName == null ? null : merchantsName.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getBusinessCategory() {
        return businessCategory;
    }

    public void setBusinessCategory(String businessCategory) {
        this.businessCategory = businessCategory == null ? null : businessCategory.trim();
    }

    public String getSignStat() {
        return signStat;
    }

    public void setSignStat(String signStat) {
        this.signStat = signStat == null ? null : signStat.trim();
    }

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman == null ? null : salesman.trim();
    }

    public String getSuperiors() {
        return superiors;
    }

    public void setSuperiors(String superiors) {
        this.superiors = superiors == null ? null : superiors.trim();
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat == null ? null : stat.trim();
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Merchants other = (Merchants) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMerchantsPhone() == null ? other.getMerchantsPhone() == null : this.getMerchantsPhone().equals(other.getMerchantsPhone()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getMerchantsName() == null ? other.getMerchantsName() == null : this.getMerchantsName().equals(other.getMerchantsName()))
            && (this.getContact() == null ? other.getContact() == null : this.getContact().equals(other.getContact()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getBusinessCategory() == null ? other.getBusinessCategory() == null : this.getBusinessCategory().equals(other.getBusinessCategory()))
            && (this.getSignStat() == null ? other.getSignStat() == null : this.getSignStat().equals(other.getSignStat()))
            && (this.getSalesman() == null ? other.getSalesman() == null : this.getSalesman().equals(other.getSalesman()))
            && (this.getSuperiors() == null ? other.getSuperiors() == null : this.getSuperiors().equals(other.getSuperiors()))
            && (this.getStat() == null ? other.getStat() == null : this.getStat().equals(other.getStat()))
            && (this.getExpirationTime() == null ? other.getExpirationTime() == null : this.getExpirationTime().equals(other.getExpirationTime()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getOperator() == null ? other.getOperator() == null : this.getOperator().equals(other.getOperator()))
            && (this.getOperateTime() == null ? other.getOperateTime() == null : this.getOperateTime().equals(other.getOperateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMerchantsPhone() == null) ? 0 : getMerchantsPhone().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getMerchantsName() == null) ? 0 : getMerchantsName().hashCode());
        result = prime * result + ((getContact() == null) ? 0 : getContact().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getBusinessCategory() == null) ? 0 : getBusinessCategory().hashCode());
        result = prime * result + ((getSignStat() == null) ? 0 : getSignStat().hashCode());
        result = prime * result + ((getSalesman() == null) ? 0 : getSalesman().hashCode());
        result = prime * result + ((getSuperiors() == null) ? 0 : getSuperiors().hashCode());
        result = prime * result + ((getStat() == null) ? 0 : getStat().hashCode());
        result = prime * result + ((getExpirationTime() == null) ? 0 : getExpirationTime().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getOperator() == null) ? 0 : getOperator().hashCode());
        result = prime * result + ((getOperateTime() == null) ? 0 : getOperateTime().hashCode());
        return result;
    }
}