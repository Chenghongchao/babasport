package com.lionxxw.babasport.core.dto;

import com.lionxxw.common.constants.DataStatus;
import com.lionxxw.common.utils.StringUtil;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class BrandDto implements Serializable {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String description;

    @Getter
    private String imgUrl;
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        if (StringUtil.notTrimEmpty(imgUrl)){
            setAllImgUrl(DataStatus.IMAGE_URL+imgUrl);
        }
    }

    @Getter
    @Setter
    private Integer sort;

    @Getter
    @Setter
    private Boolean isDisplay;

    @Getter
    @Setter
    private String allImgUrl;

    @Getter
    @Setter
    private String webSite;
}