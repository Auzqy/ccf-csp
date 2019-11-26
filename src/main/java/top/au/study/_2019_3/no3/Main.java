package top.au.study._2019_3.no3;

import java.util.Scanner;
import java.util.Stack;

import org.omg.CosNaming.NamingContextPackage.NotEmpty;

/**
 * 
 * 2019年3月 第二题
 * TODO 加入注释的定制化模版
 * TODO 编辑一个word文档记录 mac 环境下的 eclipse 的快捷键
 * 
 * @Description:  
 * @Author: zqy
 * @CreateTime: 2019-09-14 16:14
 * 
 * 
 *
 */
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String totalLineStr = sc.nextLine();
		Integer tatalLine = Integer.valueOf(totalLineStr);
		String[] inputStrArr = new String[tatalLine];
		for (int i = 0; i < tatalLine; i++) {
			inputStrArr[i] = sc.nextLine();
		}
		
		caculate(inputStrArr);
		
//		for (String string : inputStrArr) {
//			System.out.println(string);
//		}
		
	}

	private static void caculate(String[] inputStrArr) {
		Stack<String> stack = new Stack<>();
		for (String str : inputStrArr) {
			// 将乘法，除法的计算结果放入栈中
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if(c != 'x' && c != '/') {
					stack.push(String.valueOf(c));
				}else {
					String popStr = stack.pop();
					Integer popInteger = Integer.valueOf(popStr);
					Integer nextInteger = Integer.valueOf(
							String.valueOf(str.charAt(i+1)));
					
					Integer tmpResult;
					if(c == 'x') {
						tmpResult = popInteger * nextInteger;
					}else {
						tmpResult = popInteger / nextInteger;
					}
					
					stack.push(tmpResult.toString());
					i++;
				}
			}
			
			// 依次弹栈，进行 加减法的计算
			while(stack.size() != 1) {
				String numStr1 = stack.pop();
				String optStr = stack.pop();
				String numStr2 = stack.pop();
				Integer tmpResult;
				if("+".equals(optStr)) {
					tmpResult = Integer.valueOf(numStr1) + Integer.valueOf(numStr2);
				}else {
					tmpResult = Integer.valueOf(numStr2) - Integer.valueOf(numStr1);
				}
				
				stack.push(tmpResult.toString());
			}
			
			String resultStr = stack.pop();
			// 这样是不会的，改不回去，不是引用传递 
			// TODO 这里再好好看看，可能是因为是string类型的原因
//			if("24".equals(resultStr)) {
//				str = "Yes";
//			}else {
//				str = "No";
//			}
			if("24".equals(resultStr)) {
				System.out.println("Yes");
			}else {
				System.out.println("No");
			}
		}
		
	}

}


