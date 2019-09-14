package top.au.study.time_2019_3.no1;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * 
 * 2019年3月 第一题
 * TODO 加入注释的定制化模版
 * TODO 编辑一个word文档记录 mac 环境下的 eclipse 的快捷键
 * 
 * @Description:  TODO 还需要校验输入的数据是不是数字
 * @Author: zqy
 * @CreateTime: 2019-07-16 18:59
 *
 */
public class Main01 {

	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		String totalNum = in.nextLine();//读一行
		Integer totalNumInteger = Integer.valueOf(totalNum.trim());
		boolean isOdd = false; // 总数据个数是否是奇数，默认是偶数个，即false
		if (totalNumInteger % 2 != 0) {
			isOdd = true;
		}

		// 取所有元素
		String allNums = in.nextLine();//读一行
		String[] allNumsStrArr = allNums.trim().split(" ");

		// 校验输入的个数是否与实际数量相同 TODO 不知道他的要求有没有这么严格
		if(totalNumInteger != allNumsStrArr.length){
//			throw new RuntimeException("输入的数据个数不对");
			throw new RuntimeException();
		}

		// todo 确定改组数据是正序还是倒序
		boolean isAsc = false; // 表示当前就是倒序
		Integer firstNumInteger = Integer.valueOf(allNumsStrArr[0]);
		Integer lastNumInteger = Integer
				.valueOf(allNumsStrArr[allNumsStrArr.length - 1]);

		if(firstNumInteger <= lastNumInteger){
			isAsc = true; // 表示此时是正序
		}

		// 计算
		if(isOdd){ // 总数据为奇数个，中位数 = 个数 / 2 索引上的数
			if(isAsc){ // 如果当前是正序，就反向输出
				System.out.println(allNumsStrArr[allNumsStrArr.length - 1]
						+ " "
						+ allNumsStrArr[totalNumInteger/2]
						+ " "
						+ allNumsStrArr[0]);
			}else {
				System.out.println(allNumsStrArr[0]
						+ " "
						+ allNumsStrArr[totalNumInteger/2]
						+ " "
						+ allNumsStrArr[allNumsStrArr.length - 1]);
			}
		}else { // 总个数为偶数时， 中位数 = （个数 / 2 索引上的数 + 后一个数）/ 2

			double midLocationNumDouble;
			
			if(totalNumInteger > 2) { // 当长度大于2的时候, 为了方式数组下标越界
				//利用BigDecimal来实现四舍五入.保留一位小数
				midLocationNumDouble = new BigDecimal(
						Integer.valueOf(allNumsStrArr[totalNumInteger/2])
							+ Integer.valueOf(allNumsStrArr[totalNumInteger/2  + 1]))
						.divide(new BigDecimal(2))
						.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
			}else {
				//利用BigDecimal来实现四舍五入.保留一位小数
				midLocationNumDouble = new BigDecimal(
						Integer.valueOf(allNumsStrArr[0])
							+ Integer.valueOf(allNumsStrArr[allNumsStrArr.length - 1]))
						.divide(new BigDecimal(2))
						.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
			}
			

//			Integer midLocationNumInteger =
//					(Integer.valueOf(allNumsStrArr[totalNumInteger/2])
//						+ Integer.valueOf(allNumsStrArr[totalNumInteger/2] + 1)) / 2;
			if(isAsc){ // 如果当前是正序，就反向输出
				System.out.println(allNumsStrArr[allNumsStrArr.length - 1]
						+ " "
						+ midLocationNumDouble
						+ " "
						+ allNumsStrArr[0]);
			}else {
				System.out.println(allNumsStrArr[0]
						+ " "
						+ midLocationNumDouble
						+ " "
						+ allNumsStrArr[allNumsStrArr.length - 1]);
			}
		}

//		String word=in.next();//读一个数据，空格为分隔符
	}

}
