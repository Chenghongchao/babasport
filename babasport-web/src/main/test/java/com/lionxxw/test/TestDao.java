package com.lionxxw.test;

import com.lionxxw.babasport.core.dto.TestTbDto;
import com.lionxxw.babasport.core.service.TestTbService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>Description: 测试 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/21 下午3:51
 */
public class TestDao extends SpringJunitTest{

    @Autowired
    private TestTbService testTbService;

    @Test
    public void testAdd() throws Exception {
        TestTbDto testTb = new TestTbDto();
        testTb.setName("金乐乐");

        testTbService.addTestTb(testTb);
    }
}
