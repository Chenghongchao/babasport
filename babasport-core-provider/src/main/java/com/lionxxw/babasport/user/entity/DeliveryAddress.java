package com.lionxxw.babasport.user.entity;

import java.util.Date;

public class DeliveryAddress {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bbs_delivery_address.id
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bbs_delivery_address.buyer
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    private String buyer;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bbs_delivery_address.province_id
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    private Integer provinceId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bbs_delivery_address.city_id
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    private Integer cityId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bbs_delivery_address.town_id
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    private Integer townId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bbs_delivery_address.consignee
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    private String consignee;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bbs_delivery_address.address
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bbs_delivery_address.mobile
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    private String mobile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bbs_delivery_address.telephone
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    private String telephone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bbs_delivery_address.email
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bbs_delivery_address.post_code
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    private String postCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bbs_delivery_address.add_time
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    private Date addTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bbs_delivery_address.is_default
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    private Boolean isDefault;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bbs_delivery_address.id
     *
     * @return the value of bbs_delivery_address.id
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bbs_delivery_address.id
     *
     * @param id the value for bbs_delivery_address.id
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bbs_delivery_address.buyer
     *
     * @return the value of bbs_delivery_address.buyer
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    public String getBuyer() {
        return buyer;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bbs_delivery_address.buyer
     *
     * @param buyer the value for bbs_delivery_address.buyer
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    public void setBuyer(String buyer) {
        this.buyer = buyer == null ? null : buyer.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bbs_delivery_address.province_id
     *
     * @return the value of bbs_delivery_address.province_id
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    public Integer getProvinceId() {
        return provinceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bbs_delivery_address.province_id
     *
     * @param provinceId the value for bbs_delivery_address.province_id
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bbs_delivery_address.city_id
     *
     * @return the value of bbs_delivery_address.city_id
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bbs_delivery_address.city_id
     *
     * @param cityId the value for bbs_delivery_address.city_id
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bbs_delivery_address.town_id
     *
     * @return the value of bbs_delivery_address.town_id
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    public Integer getTownId() {
        return townId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bbs_delivery_address.town_id
     *
     * @param townId the value for bbs_delivery_address.town_id
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    public void setTownId(Integer townId) {
        this.townId = townId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bbs_delivery_address.consignee
     *
     * @return the value of bbs_delivery_address.consignee
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    public String getConsignee() {
        return consignee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bbs_delivery_address.consignee
     *
     * @param consignee the value for bbs_delivery_address.consignee
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    public void setConsignee(String consignee) {
        this.consignee = consignee == null ? null : consignee.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bbs_delivery_address.address
     *
     * @return the value of bbs_delivery_address.address
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bbs_delivery_address.address
     *
     * @param address the value for bbs_delivery_address.address
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bbs_delivery_address.mobile
     *
     * @return the value of bbs_delivery_address.mobile
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bbs_delivery_address.mobile
     *
     * @param mobile the value for bbs_delivery_address.mobile
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bbs_delivery_address.telephone
     *
     * @return the value of bbs_delivery_address.telephone
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bbs_delivery_address.telephone
     *
     * @param telephone the value for bbs_delivery_address.telephone
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bbs_delivery_address.email
     *
     * @return the value of bbs_delivery_address.email
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bbs_delivery_address.email
     *
     * @param email the value for bbs_delivery_address.email
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bbs_delivery_address.post_code
     *
     * @return the value of bbs_delivery_address.post_code
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bbs_delivery_address.post_code
     *
     * @param postCode the value for bbs_delivery_address.post_code
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bbs_delivery_address.add_time
     *
     * @return the value of bbs_delivery_address.add_time
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bbs_delivery_address.add_time
     *
     * @param addTime the value for bbs_delivery_address.add_time
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bbs_delivery_address.is_default
     *
     * @return the value of bbs_delivery_address.is_default
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    public Boolean getIsDefault() {
        return isDefault;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bbs_delivery_address.is_default
     *
     * @param isDefault the value for bbs_delivery_address.is_default
     *
     * @mbggenerated Tue Jun 28 10:59:08 CST 2016
     */
    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }
}