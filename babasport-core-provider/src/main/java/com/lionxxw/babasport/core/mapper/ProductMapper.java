package com.lionxxw.babasport.core.mapper;

import com.lionxxw.babasport.core.entity.Product;
import com.lionxxw.babasport.core.entity.ProductExample;
import com.lionxxw.babasport.core.entity.ProductWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bbs_product
     *
     * @mbggenerated Tue May 31 16:21:06 CST 2016
     */
    int countByExample(ProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bbs_product
     *
     * @mbggenerated Tue May 31 16:21:06 CST 2016
     */
    int deleteByExample(ProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bbs_product
     *
     * @mbggenerated Tue May 31 16:21:06 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bbs_product
     *
     * @mbggenerated Tue May 31 16:21:06 CST 2016
     */
    int insert(ProductWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bbs_product
     *
     * @mbggenerated Tue May 31 16:21:06 CST 2016
     */
    int insertSelective(ProductWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bbs_product
     *
     * @mbggenerated Tue May 31 16:21:06 CST 2016
     */
    List<ProductWithBLOBs> selectByExampleWithBLOBs(ProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bbs_product
     *
     * @mbggenerated Tue May 31 16:21:06 CST 2016
     */
    List<Product> selectByExample(ProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bbs_product
     *
     * @mbggenerated Tue May 31 16:21:06 CST 2016
     */
    ProductWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bbs_product
     *
     * @mbggenerated Tue May 31 16:21:06 CST 2016
     */
    int updateByExampleSelective(@Param("record") ProductWithBLOBs record, @Param("example") ProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bbs_product
     *
     * @mbggenerated Tue May 31 16:21:06 CST 2016
     */
    int updateByExampleWithBLOBs(@Param("record") ProductWithBLOBs record, @Param("example") ProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bbs_product
     *
     * @mbggenerated Tue May 31 16:21:06 CST 2016
     */
    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bbs_product
     *
     * @mbggenerated Tue May 31 16:21:06 CST 2016
     */
    int updateByPrimaryKeySelective(ProductWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bbs_product
     *
     * @mbggenerated Tue May 31 16:21:06 CST 2016
     */
    int updateByPrimaryKeyWithBLOBs(ProductWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bbs_product
     *
     * @mbggenerated Tue May 31 16:21:06 CST 2016
     */
    int updateByPrimaryKey(Product record);
}