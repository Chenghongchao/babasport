package com.lionxxw.common.utils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>Description: 编写速算24 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/6/17 下午2:16
 */
public class CountUtils {
    // +,-,*,/
    static String[] zf = {"+","-","*","/"};

    public static String count24(int num1, int num2, int num3, int num4){
        StringBuffer sb = new StringBuffer();
        String sb2;
        String sb3;
        for (int i = 0; i < zf.length; i++){
            sb.append(num1).append(zf[i]).append(num2);
            sb2 = sb.toString();
            for (int j = 0; j < zf.length; j++){
                sb.append(zf[j]).append(num3);
                sb3 = sb.toString();
                for (int z = 0; z < zf.length; z++){
                    sb.append(zf[z]).append(num4);
                    if (count(sb.toString()) == new Double(24).doubleValue()){
                        System.err.println(sb.toString());
                        return sb.toString();
                    }
                    sb = new StringBuffer(sb3);
                }
                sb = new StringBuffer(sb2);
            }
            sb.setLength(0);//清空
        }
        return "未找到答案";
    }

    public static String resetCount24(int num1, int num2, int num3, int num4){
        int[] nums = {num1,num2,num3,num4};
        String result = count24(nums[0], nums[1], nums[2], nums[3]);
        if("未找到答案".equals(result)){
            result = count24(nums[0],nums[2],nums[3],nums[1]);

            result = count24(nums[0],nums[3],nums[2],nums[1]);
        }
        return result;


    }

    public static void main(String[] args) {
        System.out.println("当当当...答案出现:"+resetCount24(4, 4, 10, 10));
    }

    /**
     * 1+2+3+4
     * 1-2+3+4
     * 1*2+3+4
     * 1/2+3+4
     *
     * @return
     */
    private static double count(String str){
        System.out.println(str);
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        Object result = null;
        try {
            result = engine.eval(str);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        System.out.println("结果类型:" + str + "=" + result);
        return (Double) result;
    }
}
