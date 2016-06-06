package com.lionxxw.babasport.core.dto;

import com.lionxxw.common.constants.DataStatus;
import com.lionxxw.common.utils.StringUtils;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class ProductImageDto implements Serializable {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private Integer productId;

    @Getter
    private String url;

    public void setUrl(String url) {
        this.url = url;
        if (!StringUtils.isTrimEmpty(url)){
            setAllUrl(DataStatus.IMAGE_URL+url);
        }
    }

    @Getter
    @Setter
    private String allUrl;

    @Getter
    @Setter
    private Boolean isDef;
}