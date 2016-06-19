package com.lionxxw.babasport.product.mapper;

import com.lionxxw.babasport.product.dto.ProductDto;
import com.lionxxw.babasport.product.entity.Product;
import com.lionxxw.babasport.product.entity.ProductExample;
import com.lionxxw.babasport.product.entity.ProductWithBLOBs;
import com.lionxxw.common.model.PageQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductExMapper {
    List<ProductDto> selectByParams(@Param("params")ProductDto params, @Param("query")PageQuery query);

    int countByParams(@Param("params")ProductDto params);
}