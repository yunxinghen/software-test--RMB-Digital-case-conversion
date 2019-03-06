package com.su.util;
/**
 * 
 */


import java.math.BigDecimal;
import java.util.Scanner;

/**
 * @title NumericConvertToRMB.java
 * @author feiyuling
 * @date 2018-12-25下午7:44:15
 */
public class NumericConvertToRMB {
	// 首先，数字应该分段，分成万以下、万、亿、兆（后面单位暂不罗列）这几个段
	// 其次，每个段分别是个十百千四个单位，如12345678，转成大写是：壹仟贰佰叁拾肆 万 伍仟陆佰柒拾捌
	// 最后，处理0的问题，3003-->叁仟零叁，303-->叁佰零叁，在段内连续出现一个或多个0时，只出现一个零。300000300-->叁亿零叁佰
	// 总之，在连续出现多个0的时候，只保留一个零。而需要注意：300-->叁佰，在段内最后不管连续出现多少0，都忽略
	public static final char[] NUMBER_CHAR = "零壹贰叁肆伍陆柒捌玖".toCharArray(); // 大写数字
	public static final String[] IN_UNIT_CHAR = { "", "拾", "佰", "仟" }; // 段内字符
	public static final String[] UNIT_NAME = { "", "万", "亿", "万亿" }; // 段名

	public static String numericConvertToRMB(double value) {
		String valStr = new BigDecimal(Double.toString(value)).multiply(
				new BigDecimal("100")).toPlainString();
		if (valStr.indexOf(".") > 0) {
			valStr = valStr.substring(0, valStr.indexOf("."));//默认保留小数点后两位
		}
		//System.out.println(valStr);
		StringBuilder prefix = new StringBuilder(); // 整数部分转化的结果
		StringBuilder suffix = new StringBuilder(); // 小数部分转化的结果
		// 只有小数部分
		if (valStr.length() <= 2) {
			//System.out.println(valStr);
			prefix.append("零元");
			if (valStr.equals("0")) {
				suffix.append("零角零分");
			} else if (valStr.length() == 1) {
				suffix.append(NUMBER_CHAR[valStr.charAt(0) - '0']).append("分");
			} else {
				suffix.append(NUMBER_CHAR[valStr.charAt(0) - '0']).append("角");
				suffix.append(NUMBER_CHAR[valStr.charAt(1) - '0']).append("分");
			}
		} else {
			int flag = valStr.length() - 2;
			String head = valStr.substring(0, flag); // 取整数部分
			String rail = valStr.substring(flag); // 取小数部分

			// 处理整数位
			char[] ch = head.toCharArray();
			int zeroNum = 0; // 连续零的个数
			for (int i = 0; i < ch.length; i++) {
				int index = (ch.length - i - 1) % 4; // 取段内位置，3、2、1、0
				int indexLoc = (ch.length - i - 1) / 4; // 取段位置，3、2、1、0

				if (ch[i] == '0') {
					zeroNum++;
				} else {
					if (zeroNum != 0) {
						if (index != 3) {
							prefix.append("零");
						}
						zeroNum = 0;
					}
					prefix.append(NUMBER_CHAR[ch[i] - '0']); // 转换该位置的数

					prefix.append(IN_UNIT_CHAR[index]); // 添加段内标识
				}

				if (index == 0 && zeroNum < 4) // 添加段名
				{
					prefix.append(UNIT_NAME[indexLoc]);
					//System.out.println(UNIT_NAME[indexLoc]);
				}
			}
			prefix.append("元");

			// 处理小数位
			if (rail.equals("00")) {
				suffix.append("零零角零分");
			} else if (rail.startsWith("0")) {
				suffix.append("零").append(NUMBER_CHAR[rail.charAt(1) - '0']).append("分");
			} else if(rail.charAt(1)-'0'==0){
				suffix.append(NUMBER_CHAR[rail.charAt(0) - '0']).append("角");
			} else {
				suffix.append(NUMBER_CHAR[rail.charAt(0) - '0']).append("角");
				suffix.append(NUMBER_CHAR[rail.charAt(1) - '0']).append("分");
			}
		}

		return "人民币"+prefix.append(suffix).toString();
	}
	public static void main(String[] args){
		System.out.print("请输入人数字：");
		Scanner scanner = new Scanner(System.in);
		try{
			double value = scanner.nextDouble();
			if (value>=10000000000000.00||value < 0.00) {
				System.out.println("输入不合法");
			} else if (value == 0.00) {
				System.out.println("人民币零元零角零分");
			} else {
				String valueToRMB = numericConvertToRMB(value);
				System.out.println("转换成大写后为：" + valueToRMB);
			}
		}catch(Exception e){
			System.out.print("输入含有非数字");
		}
	}
}
