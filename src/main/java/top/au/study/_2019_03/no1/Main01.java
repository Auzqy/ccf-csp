package top.au.study._2019_03.no1;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * 
 * 2019年3月 第一题
 * TODO 加入注释的定制化模版
 * TODO 编辑一个word文档记录 mac 环境下的 eclipse 的快捷键
 * 
 * @Description:  还需要校验输入的数据是不是数字 这个不需要
 * 
 * 				如下代码获得的是 满分
 * 
 * @Author: zqy
 * @CreateTime: 2019-09-14 14:08
 *
 */
public class Main01 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		int totalNum = sc.nextInt();
		String totalNumStr = sc.nextLine();//读一行
		Integer totalNum = Integer.valueOf(totalNumStr.trim());
		String numString = sc.nextLine();
		String[] numStringArr = numString.split(" ");
		if(totalNum != numStringArr.length) {
			throw new IllegalArgumentException();
		}
		
		String midStr;
		if(totalNum % 2 != 0) { // 奇数
			int midIndex = totalNum /2;
			midStr = numStringArr[midIndex];
		}else { // 偶数
			int midBigger = totalNum /2;
			int midLess = midBigger - 1;
			BigDecimal mid = new BigDecimal(numStringArr[midLess])
			.add(new BigDecimal(numStringArr[midBigger]))
			.divide(new BigDecimal("2"));
//			.setScale(1,BigDecimal.ROUND_HALF_UP);
			if(mid.doubleValue() % 1 == 0) {
				midStr = mid.toString();
			}else {
				midStr = mid.setScale(1,BigDecimal.ROUND_HALF_UP).toString();
			}
			
		}
		
		Integer firstNum = Integer.valueOf(numStringArr[0]);
		Integer lastNum = Integer.valueOf(numStringArr[numStringArr.length - 1]);
		
		if(firstNum > lastNum) {
			System.out.println(firstNum + " " + midStr + " " + lastNum);
		}else {
			System.out.println(lastNum+ " " + midStr + " " + firstNum );
		}
	}

}
