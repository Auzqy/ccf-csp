package top.au.study._2018_12.no2;

import java.util.Scanner;

/**
 * description:
 * <p>
 * <p>
 * 试题编号：	201812-2
 * 试题名称：	小明放学
 * 时间限制：	1.0s
 * 内存限制：	512.0MB
 * 问题描述：
 * 题目背景
 * 　　汉东省政法大学附属中学所在的光明区最近实施了名为“智慧光明”的智慧城市项目。具体到交通领域，通过“智慧光明”终端，可以看到光明区所有红绿灯此时此刻的状态。小明的学校也安装了“智慧光明”终端，小明想利用这个终端给出的信息，估算自己放学回到家的时间。
 * 问题描述
 * 　　一次放学的时候，小明已经规划好了自己回家的路线，并且能够预测经过各个路段的时间。同时，小明通过学校里安装的“智慧光明”终端，看到了出发时刻路上经过的所有红绿灯的指示状态。请帮忙计算小明此次回家所需要的时间。
 * 输入格式
 * 　　输入的第一行包含空格分隔的三个正整数 r、y、g，表示红绿灯的设置。这三个数均不超过 106。
 * 　　输入的第二行包含一个正整数 n，表示小明总共经过的道路段数和路过的红绿灯数目。
 * 　　接下来的 n 行，每行包含空格分隔的两个整数 k、t。k=0 表示经过了一段道路，将会耗时 t 秒，此处 t 不超过 106；k=1、2、3 时，分别表示出发时刻，此处的红绿灯状态是红灯、黄灯、绿灯，且倒计时显示牌上显示的数字是 t，此处 t 分别不会超过 r、y、g。
 * 输出格式
 * 　　输出一个数字，表示此次小明放学回家所用的时间。
 * 样例输入
 * 30 3 30
 * 8
 * 0 10
 * 1 5
 * 0 11
 * 2 2
 * 0 6
 * 0 3
 * 3 10
 * 0 3
 * 样例输出
 * 46
 * 样例说明
 * 　　小明先经过第一段路，用时 10 秒。第一盏红绿灯出发时是红灯，还剩 5 秒；小明到达路口时，这个红绿灯已经变为绿灯，不用等待直接通过。接下来经过第二段路，用时 11 秒。第二盏红绿灯出发时是黄灯，还剩两秒；小明到达路口时，这个红绿灯已经变为红灯，还剩 11 秒。接下来经过第三、第四段路，用时 9 秒。第三盏红绿灯出发时是绿灯，还剩 10 秒；小明到达路口时，这个红绿灯已经变为红灯，还剩两秒。接下来经过最后一段路，用时 3 秒。共计 10+11+11+9+2+3 = 46 秒。
 * 评测用例规模与约定
 * 　　有些测试点具有特殊的性质：
 * 　　* 前 2 个测试点中不存在任何信号灯。
 * 　　测试点的输入数据规模：
 * 　　* 前 6 个测试点保证 n ≤ 103。
 * 　　* 所有测试点保证 n ≤ 105。
 *
 * 已注释掉的代码得分20
 *
 * createTime: 2019-12-09 23:21
 *
 * @author au
 */
public class Main {

	/**
	 * 表示红绿灯的设置。这三个数均不超过 106。
	 */
	private int r,y,g;

	/**
	 * 表示小明总共经过的道路段数和路过的红绿灯数目。
	 */
	private int n;


	private RoadStatus[] roadStatuses;

	/**
	 * 小明回家所用总时间
	 */
	private int totalTime;

	/**
	 * k=0 表示经过了一段道路，将会耗时 t 秒，此处 t 不超过 106；
	 * k=1、2、3 时，分别表示出发时刻，
	 * 	此处的红绿灯状态是红灯、黄灯、绿灯，
	 * 	且倒计时显示牌上显示的数字是 t，
	 * 	此处 t 分别不会超过 r、y、g。
	 *
	 */
	class RoadStatus {
		private int k;
		private int t;

		public RoadStatus() {
		}

		public RoadStatus(int k, int t) {
			this.k = k;
			this.t = t;
		}
	}

	private void initData() {
		Scanner scanner = new Scanner(System.in);
		r = scanner.nextInt();
		y = scanner.nextInt();
		g = scanner.nextInt();

		n = scanner.nextInt();

		roadStatuses = new RoadStatus[n];
		for (int i = 0; i < n; i++) {
			int k = scanner.nextInt();
			int t = scanner.nextInt();

			roadStatuses[i] = new RoadStatus(k, t);
		}
	}

	private void calculate() {
		for (RoadStatus roadStatus : roadStatuses) {
			int spendTime = 0;
			int curK = roadStatus.k;
			if (0 == curK) {
				spendTime = roadStatus.t;
			} else if (1 == curK) {
				spendTime = redLampCalculate(roadStatus);
			} else if (2 == curK) {
				spendTime = yellowLampCalculate(roadStatus);
			} else if (3 == curK) {
				spendTime = greenLampCalculate(roadStatus);
			}
			totalTime += spendTime;
		}
	}

	private int greenLampCalculate(RoadStatus roadStatus) {

		int spendTime = 0;

		if (roadStatus.t < totalTime) {
			int leastPositionTime = (totalTime - roadStatus.t) % (r + y + g);
			if (leastPositionTime > y && leastPositionTime < (y + r)) {
				spendTime = y+r;
			}
		}
		return spendTime;

//		int spendTime = 0;
//		if (roadStatus.t <= totalTime) {
//			int leftTime = roadStatus.t - (totalTime % (r + y + g));
//			if (-leftTime > y && -leftTime <= (y + r)) {
//				spendTime = leftTime + r + y;
//			}
//		}
//		return spendTime;
	}

	private int yellowLampCalculate(RoadStatus roadStatus) {

		int spendTime = 0;

		if (roadStatus.t < totalTime) {
//			int leastPositionTime = (totalTime - roadStatus.t) % (r + y + g);
			spendTime = r;
		}
		return spendTime;

//		int spendTime = 0;
//		int leastPositionTime = totalTime % (r + y + g);
//		int leftTime = roadStatus.t - leastPositionTime;
//		if (-leftTime > 0 && -leftTime <= r) {
//			spendTime = leftTime + r;
//		}
//		return spendTime;
	}

	private int redLampCalculate(RoadStatus roadStatus) {

		int spendTime = 0;

		if (roadStatus.t > totalTime) {
			spendTime = roadStatus.t - totalTime;
		}

		return spendTime;

//		int spendTime = 0;
//		int leastPositionTime = totalTime % (r + y + g);
//		int leftTime = roadStatus.t - leastPositionTime;
//		if (leftTime > 0) {
//			spendTime = leftTime;
//		}
//		return spendTime;
	}

	public Main() {
		initData();
		calculate();
		System.out.print(totalTime);
	}

	public static void main(String[] args) {
		new Main();
	}
}
