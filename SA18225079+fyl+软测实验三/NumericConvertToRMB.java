package com.su.util;
/**
 * 
 */


import java.math.BigDecimal;
import java.util.Scanner;

/**
 * @title NumericConvertToRMB.java
 * @author feiyuling
 * @date 2018-12-25����7:44:15
 */
public class NumericConvertToRMB {
	// ���ȣ�����Ӧ�÷ֶΣ��ֳ������¡����ڡ��ף����浥λ�ݲ����У��⼸����
	// ��Σ�ÿ���ηֱ��Ǹ�ʮ��ǧ�ĸ���λ����12345678��ת�ɴ�д�ǣ�ҼǪ������ʰ�� �� ��Ǫ½����ʰ��
	// ��󣬴���0�����⣬3003-->��Ǫ������303-->�����������ڶ�����������һ������0ʱ��ֻ����һ���㡣300000300-->����������
	// ��֮�����������ֶ��0��ʱ��ֻ����һ���㡣����Ҫע�⣺300-->���ۣ��ڶ�����󲻹��������ֶ���0��������
	public static final char[] NUMBER_CHAR = "��Ҽ��������½��ƾ�".toCharArray(); // ��д����
	public static final String[] IN_UNIT_CHAR = { "", "ʰ", "��", "Ǫ" }; // �����ַ�
	public static final String[] UNIT_NAME = { "", "��", "��", "����" }; // ����

	public static String numericConvertToRMB(double value) {
		String valStr = new BigDecimal(Double.toString(value)).multiply(
				new BigDecimal("100")).toPlainString();
		if (valStr.indexOf(".") > 0) {
			valStr = valStr.substring(0, valStr.indexOf("."));//Ĭ�ϱ���С�������λ
		}
		//System.out.println(valStr);
		StringBuilder prefix = new StringBuilder(); // ��������ת���Ľ��
		StringBuilder suffix = new StringBuilder(); // С������ת���Ľ��
		// ֻ��С������
		if (valStr.length() <= 2) {
			//System.out.println(valStr);
			prefix.append("��Ԫ");
			if (valStr.equals("0")) {
				suffix.append("������");
			} else if (valStr.length() == 1) {
				suffix.append(NUMBER_CHAR[valStr.charAt(0) - '0']).append("��");
			} else {
				suffix.append(NUMBER_CHAR[valStr.charAt(0) - '0']).append("��");
				suffix.append(NUMBER_CHAR[valStr.charAt(1) - '0']).append("��");
			}
		} else {
			int flag = valStr.length() - 2;
			String head = valStr.substring(0, flag); // ȡ��������
			String rail = valStr.substring(flag); // ȡС������

			// ��������λ
			char[] ch = head.toCharArray();
			int zeroNum = 0; // ������ĸ���
			for (int i = 0; i < ch.length; i++) {
				int index = (ch.length - i - 1) % 4; // ȡ����λ�ã�3��2��1��0
				int indexLoc = (ch.length - i - 1) / 4; // ȡ��λ�ã�3��2��1��0

				if (ch[i] == '0') {
					zeroNum++;
				} else {
					if (zeroNum != 0) {
						if (index != 3) {
							prefix.append("��");
						}
						zeroNum = 0;
					}
					prefix.append(NUMBER_CHAR[ch[i] - '0']); // ת����λ�õ���

					prefix.append(IN_UNIT_CHAR[index]); // ��Ӷ��ڱ�ʶ
				}

				if (index == 0 && zeroNum < 4) // ��Ӷ���
				{
					prefix.append(UNIT_NAME[indexLoc]);
					//System.out.println(UNIT_NAME[indexLoc]);
				}
			}
			prefix.append("Ԫ");

			// ����С��λ
			if (rail.equals("00")) {
				suffix.append("��������");
			} else if (rail.startsWith("0")) {
				suffix.append("��").append(NUMBER_CHAR[rail.charAt(1) - '0']).append("��");
			} else if(rail.charAt(1)-'0'==0){
				suffix.append(NUMBER_CHAR[rail.charAt(0) - '0']).append("��");
			} else {
				suffix.append(NUMBER_CHAR[rail.charAt(0) - '0']).append("��");
				suffix.append(NUMBER_CHAR[rail.charAt(1) - '0']).append("��");
			}
		}

		return "�����"+prefix.append(suffix).toString();
	}
	public static void main(String[] args){
		System.out.print("�����������֣�");
		Scanner scanner = new Scanner(System.in);
		try{
			double value = scanner.nextDouble();
			if (value>=10000000000000.00||value < 0.00) {
				System.out.println("���벻�Ϸ�");
			} else if (value == 0.00) {
				System.out.println("�������Ԫ������");
			} else {
				String valueToRMB = numericConvertToRMB(value);
				System.out.println("ת���ɴ�д��Ϊ��" + valueToRMB);
			}
		}catch(Exception e){
			System.out.print("���뺬�з�����");
		}
	}
}
