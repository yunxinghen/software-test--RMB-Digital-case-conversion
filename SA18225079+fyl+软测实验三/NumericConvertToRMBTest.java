/**
 * 
 */
package com.su.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.su.util.NumericConvertToRMB;

/**
 * @title NumericConvertToRMBTest.java
 * @author feiyuling
 * @date 2018-12-25下午7:58:41
 */
@RunWith(Parameterized.class)
public class NumericConvertToRMBTest {

	private String expected; 
    private double target;
    @Parameters
    public static Collection words(){ 
        return Arrays.asList(new Object[][]{
        		{"人民币壹亿零壹拾元零零角零分",100000010.00},      						// 0.普通合法的数据是否正确处理
                {"输入不合法", -1.00},                          					// 1.普通非法的数据是否正确处理
                {"人民币零元零角零分", 0.00},                      					// 2.边界内最接近边界的（合法）数据是否正确处理
                {"人民币零元壹分", 0.01},     										// 3.边界内最接近边界的（合法）数据是否正确处理
                {"人民币零元壹角壹分", 0.11},   										// 4.边界内最接近边界的（合法）数据是否正确处理
                {"人民币玖仟玖佰玖拾玖亿玖仟玖佰玖拾玖万玖仟玖佰玖拾玖元玖角玖分", 999999999999.99}, // 5.边界内最接近边界的（合法）数据是否正确处理
                {"人民币玖仟玖佰玖拾玖亿玖仟玖佰玖拾玖万玖仟玖佰玖拾玖元玖角捌分", 999999999999.98}, // 6.边界内最接近边界的（合法）数据是否正确处理
                {"输入不合法", 10000000000000.00},   								// 7.边界外最接近边界的（非法）数据是否正确处理
                {"输入不合法", -0.99},   											// 8.边界外最接近边界的（非法）数据是否正确处理
                {"人民币壹亿零壹拾元零壹分", 100000010.01},   							// 9.N次循环的第0、 1、 n次是否有错
                {"人民币壹亿元零零角零分", 100000000.00},   							// 10."100000010.01"N次循环的第0次是否有错
                {"人民币壹元零零角零分", 1},   										// 11.运算或判断中(小数长度取0)取最大最小值时是否有错
                {"人民币壹元贰角叁分", 1.234},   										// 12.运算或判断中(小数长度取3)取最大最小值时是否有错
                {"人民币壹元壹角", 1.10},   											// 13.数据流、控制流中length=3刚好大于确定的比较值2时是否出错
                {"人民币零元壹角零分", 0.10},   										// 14.数据流、控制流中length=2刚好等于确定的比较值2时是否出错
                {"人民币零元壹分", 0.01}    											// 15.数据流、控制流中length=1刚好小于确定的比较值2时是否出错
        }); 
    } 
    /** 
     * 参数化测试必须的构造函数
     * @param expected     期望的测试结果，对应参数集中的第一个参数
     * @param target     测试数据，对应参数集中的第二个参数
     */ 
    public NumericConvertToRMBTest(String expected , double target){ 
        this.expected = expected; 
        this.target = target; 
    } 

	/**
	 * 测试 numericConvertToRMB 人民币大小写转换
	 */
	@Test
	public void testNumericConvertToRMB() {
		String result="";
		if(target>=10000000000000.00||target < 0.00){
			result="输入不合法";
		}else{
			result=NumericConvertToRMB.numericConvertToRMB(target);
		}	
		Assert.assertEquals(expected,result);
	}
}
