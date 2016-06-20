package com.lionxxw.test;

import com.lionxxw.babasport.user.dto.BuyerDto;
import com.lionxxw.common.utils.JsonUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.junit.Test;

import java.io.StringWriter;

/**
 * <p>Description: 测试购物车对象 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/6/20 下午5:35
 */
public class TestCookie {

    @Test
    public void testCookie() throws Exception{

        BuyerDto buyerDto = new BuyerDto();
        buyerDto.setRealName("王翔");

        // springmvc
        ObjectMapper om = new ObjectMapper();
        om.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);// 设置序列化,不显示null

        // 流
        StringWriter sw = new StringWriter();

        // 对象转json 写的过程
        om.writeValue(sw, buyerDto);
        System.out.println(sw.toString());

        // json转对象 读的过程
        BuyerDto o = (BuyerDto) JsonUtils.toObject("{\"realName\":\"王翔\"}", BuyerDto.class);
        System.out.println(o.toString());
    }
}
