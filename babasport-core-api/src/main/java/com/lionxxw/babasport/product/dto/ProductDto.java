package com.lionxxw.babasport.product.dto;

import com.lionxxw.common.constants.DataStatus;
import com.lionxxw.common.utils.StringUtils;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

public class ProductDto implements Serializable {

    @Setter
    @Getter
    private Integer id;

    @Setter
    @Getter
    private String no;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private Double weight;

    @Setter
    @Getter
    private Boolean isNew;

    @Setter
    @Getter
    private Boolean isHot;

    @Setter
    @Getter
    private Boolean isCommend;

    @Setter
    @Getter
    private Date createTime;

    @Setter
    @Getter
    private String createUserId;

    @Setter
    @Getter
    private Date checkTime;

    @Setter
    @Getter
    private String checkUserId;

    @Setter
    @Getter
    private Boolean isShow;

    @Setter
    @Getter
    private Boolean isDel;

    @Setter
    @Getter
    private Integer typeId;

    @Setter
    @Getter
    private Integer brandId;

    @Setter
    @Getter
    private String keywords;

    @Setter
    @Getter
    private Integer sales;

    @Setter
    @Getter
    private String feature;

    @Setter
    @Getter
    private String color;

    @Setter
    @Getter
    private String size;

    // 商品描述(列表查询为空)
    @Setter
    @Getter
    private String description;

    // 包装清单(列表查询为空)
    @Setter
    @Getter
    private String packageList;

    // 联表查询商品的默认图片(全路径)
    @Getter
    @Setter
    private String imageUrl;

    // 联表查询商品的默认图片(服务器相对路径)
    @Getter
    private String url;
    public void setUrl(String url) {
        this.url = url;
        if (!StringUtils.isTrimEmpty(url)){
            setImageUrl(DataStatus.IMAGE_URL + url);
        }
    }

    /**
     * 排序sql
     */
    @Getter
    @Setter
    private String orderByClause;

    /**
     * 用于前台列表页查询
     */
    @Getter
    @Setter
    private String brandName;

    /**
     * 用于前台列表页查询
     */
    @Getter
    @Setter
    private String typeName;

    /**
     * 用于前台列表页查询
     */
    @Getter
    @Setter
    private String featureName;
}