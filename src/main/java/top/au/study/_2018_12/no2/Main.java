package top.au.study._2018_12.no2;

import java.util.Scanner;

/**
 * 
 * 2018年12月 第二题 todo
 * 
 * @Description:  
 * @Author: zqy
 * @CreateTime: 2019-09-14 20:23
 *
 */
public class Main {
	
	public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
		
		String rygTime = sc.nextLine();
		String[] rygArr = rygTime.split(" ");
		int[] rygInt = new int[rygArr.length];
		for (int i = 0; i < rygInt.length; i++) {
			rygInt[i] = Integer.parseInt(rygArr[i]);
		}
		String totalNumStr = sc.nextLine();
		int totalNumInt = Integer.parseInt(totalNumStr);
		
		long res = 0;
		for (int i = 0; i < totalNumInt; i++) {
			String inputStr = sc.nextLine();
			String[] noArrStr = inputStr.split(" ");
			if("0".equals(noArrStr[0])) {
				res += Integer.parseInt(noArrStr[1]);
			}else if("1".equals(noArrStr[0])) { // 红
				res += Integer.parseInt(noArrStr[1]);
			}else if("2".equals(noArrStr[0])) { // 黄
				res += Integer.parseInt(noArrStr[1]);
				res += rygInt[0];
			}else if("3".equals(noArrStr[0])) { // 绿
				
			}
		}
		
		System.out.println(res);
	}

}
