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
 * @date 2018-12-25����7:58:41
 */
@RunWith(Parameterized.class)
public class NumericConvertToRMBTest {

	private String expected; 
    private double target;
    @Parameters
    public static Collection words(){ 
        return Arrays.asList(new Object[][]{
        		{"�����Ҽ����ҼʰԪ��������",100000010.00},      						// 0.��ͨ�Ϸ��������Ƿ���ȷ����
                {"���벻�Ϸ�", -1.00},                          					// 1.��ͨ�Ƿ��������Ƿ���ȷ����
                {"�������Ԫ������", 0.00},                      					// 2.�߽�����ӽ��߽�ģ��Ϸ��������Ƿ���ȷ����
                {"�������ԪҼ��", 0.01},     										// 3.�߽�����ӽ��߽�ģ��Ϸ��������Ƿ���ȷ����
                {"�������ԪҼ��Ҽ��", 0.11},   										// 4.�߽�����ӽ��߽�ģ��Ϸ��������Ƿ���ȷ����
                {"����Ҿ�Ǫ���۾�ʰ���ھ�Ǫ���۾�ʰ�����Ǫ���۾�ʰ��Ԫ���Ǿ���", 999999999999.99}, // 5.�߽�����ӽ��߽�ģ��Ϸ��������Ƿ���ȷ����
                {"����Ҿ�Ǫ���۾�ʰ���ھ�Ǫ���۾�ʰ�����Ǫ���۾�ʰ��Ԫ���ǰƷ�", 999999999999.98}, // 6.�߽�����ӽ��߽�ģ��Ϸ��������Ƿ���ȷ����
                {"���벻�Ϸ�", 10000000000000.00},   								// 7.�߽�����ӽ��߽�ģ��Ƿ��������Ƿ���ȷ����
                {"���벻�Ϸ�", -0.99},   											// 8.�߽�����ӽ��߽�ģ��Ƿ��������Ƿ���ȷ����
                {"�����Ҽ����ҼʰԪ��Ҽ��", 100000010.01},   							// 9.N��ѭ���ĵ�0�� 1�� n���Ƿ��д�
                {"�����Ҽ��Ԫ��������", 100000000.00},   							// 10."100000010.01"N��ѭ���ĵ�0���Ƿ��д�
                {"�����ҼԪ��������", 1},   										// 11.������ж���(С������ȡ0)ȡ�����Сֵʱ�Ƿ��д�
                {"�����ҼԪ��������", 1.234},   										// 12.������ж���(С������ȡ3)ȡ�����Сֵʱ�Ƿ��д�
                {"�����ҼԪҼ��", 1.10},   											// 13.����������������length=3�պô���ȷ���ıȽ�ֵ2ʱ�Ƿ����
                {"�������ԪҼ�����", 0.10},   										// 14.����������������length=2�պõ���ȷ���ıȽ�ֵ2ʱ�Ƿ����
                {"�������ԪҼ��", 0.01}    											// 15.����������������length=1�պ�С��ȷ���ıȽ�ֵ2ʱ�Ƿ����
        }); 
    } 
    /** 
     * ���������Ա���Ĺ��캯��
     * @param expected     �����Ĳ��Խ������Ӧ�������еĵ�һ������
     * @param target     �������ݣ���Ӧ�������еĵڶ�������
     */ 
    public NumericConvertToRMBTest(String expected , double target){ 
        this.expected = expected; 
        this.target = target; 
    } 

	/**
	 * ���� numericConvertToRMB ����Ҵ�Сдת��
	 */
	@Test
	public void testNumericConvertToRMB() {
		String result="";
		if(target>=10000000000000.00||target < 0.00){
			result="���벻�Ϸ�";
		}else{
			result=NumericConvertToRMB.numericConvertToRMB(target);
		}	
		Assert.assertEquals(expected,result);
	}
}
