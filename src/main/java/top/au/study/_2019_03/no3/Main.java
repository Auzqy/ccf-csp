package top.au.study._2019_03.no3;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 * description:
 *
 * 试题编号：	201903-3
 * 试题名称：	损坏的RAID5
 * 时间限制：	1.0s
 * 内存限制：	512.0MB
 *
 *
 * createTime: 2019-12-06 17:36
 * @author au
 */
public class Main {
	/**
	 * 阵列中硬盘的数目
	 */
	private int n;
	/**
	 * 阵列的条带大小（单位：块）
	 */
	private int s;
	/**
	 * 现存的硬盘数目
	 */
	private int l;

	/**
	 * key: 硬盘的序号，从0开始，非负整数
	 * value: 硬盘的内容
	 */
	private Map<Integer, String> disk;

	/**
	 * 要读取的块编号
	 */
	private int[] readTarget;

	/**
	 * blk 的长度
	 */
	private int blkLength;

	/**
	 * 最大的块数量
	 */
	private int maxBlockNum;

	/**
	 * blkNo对应的所有磁盘的键值对
	 * eg:
	 * 			disk0	disk1	disk2
	 * blk0		00xxx	01xxx	02xxx
	 * blk1		10xxx	11xxx	12xxx
	 * blk2		20xxx	21xxx	22xxx
	 * blk3		30xxx	31xxx	32xxx
	 *
	 * blk[0]	0,00xxx
	 * 			1,01xxx
	 * 			2,02xxx
	 * blk[1]	0,10xxx
	 * 			1,11xxx
	 * 			2,12xxx
	 * ……	……	……	……
	 */
	private Map<Integer,String>[] blk;

	/**
	 * 用于记录数据块编号的相关内容
	 */
	private Disk[] disks;


	class Disk {
		/**
		 * 磁盘编号
		 */
		private int diskNo;
		/**
		 * 字节编号
		 */
		private int blkNo;
		/**
		 * 磁盘内容
		 */
		private String value;

		/**
		 * 用于标记是否已经设置过
		 */
		private boolean hasSet;

		public boolean isHasSet() {
			return hasSet;
		}

		public void setHasSet(boolean hasSet) {
			this.hasSet = hasSet;
		}

		public int getDiskNo() {
			return diskNo;
		}

		public void setDiskNo(int diskNo) {
			this.diskNo = diskNo;
		}

		public int getBlkNo() {
			return blkNo;
		}

		public void setBlkNo(int blkNo) {
			this.blkNo = blkNo;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}


	public Main() {
		initData();
		readData(readTarget);
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		s = scanner.nextInt();
		l = scanner.nextInt();

		disk = new HashMap<>(l);
		for (int i = 0; i < l; i++) {
			int diskNo = scanner.nextInt();
			String diskContent = scanner.next();
			disk.put(diskNo, diskContent);
		}

		int m = scanner.nextInt();
		readTarget = new int[m];
		for (int i = 0; i < m; i++) {
			readTarget[i] = scanner.nextInt();
		}

		blkLength = disk.get(0).length() / 8;

		maxBlockNum = (n-1) * blkLength / s;

		disks = new Disk[maxBlockNum];
		blk = new HashMap[blkLength];
		for (int i = 0; i < blkLength; i++) {
			blk[i] = new HashMap(l);
		}

		for (int i = 0; i < maxBlockNum; i++) {
			disks[i] = new Disk();
		}

		for (Map.Entry<Integer, String> entry : disk.entrySet()) {
			for (int i = 0; i < blkLength; i++) {
				blk[i].put(entry.getKey(),
						entry.getValue().substring(8 * i, 8 * (i + 1)));
			}
		}

		// 依照规则填充 block 对应的值
		int blockNum = 0;
		// blkLength / s 表示总共会循环的次数
		for (int k = 0; k < blkLength / s; k++) {
			if (n > 2) {
				// 对所有n个磁盘进行遍历
				for (int i = 0; i < n; i++) {
					if (n - 1 - k == 0) {
						if (k + 1 < blkLength / s) {
							blockNum = setDisksItem(blockNum, k + 1, i);
						}
					}
					// 就是那个P的位置
					if (i % (n - 1 - k) == 0) {
						blockNum = setDisksItem(blockNum, k + 1, i);
					}
					// 非 p 的位置
					else {
						blockNum = setDisksItem(blockNum, k, i);
					}
				}
			}
			// todo 还需要再检查一下
			else if (n == 2) {
				blockNum = setDisksItem(blockNum, k, 0);
			}
		}
	}

	/**
	 * 对 disks 的每一元素进行赋值
	 * @param blockNum
	 * @param k
	 * @param i
	 * @return
	 */
	private int setDisksItem(int blockNum, int k, int i) {
		String diskIValue = disk.getOrDefault(i, "-1");
		for (int j = 0; j < s; j++) {
			if (!disks[blockNum].isHasSet()) {
				disks[blockNum].setHasSet(true);
				disks[blockNum].setDiskNo(i);
				disks[blockNum].setBlkNo(k * s + j);
				if ("-1".equals(diskIValue)) {
					disks[blockNum].setValue("-1");
				} else {
					disks[blockNum].setValue(
							diskIValue.substring(8 * k, 8 * (k + 1)));
				}
				blockNum++;
			}
		}
		return blockNum;
	}


	/**
	 * 批量读取数据
	 * @param readTarget
	 */
	private void readData(int[] readTarget) {
		int length = readTarget.length;
		for (int i = 0; i < length; i++) {
			if (i < length - 1) {
				System.out.println(readData(readTarget[i]));
			} else {
				System.out.print(readData(readTarget[i]));
			}
		}
	}

	/**
	 * 读取指定块编号的内容
	 * @param blockNo
	 * @return
	 */
	private String readData(int blockNo) {
		if (blockNo >= maxBlockNum) {
			return "-";
		}
		String value = disks[blockNo].getValue();
		if ("-1".equals(value)) {
			Map<Integer, String> blkMap = blk[disks[blockNo].getBlkNo()];
			if (n - blkMap.size() != 1) {
				return "-";
			}
			String res = "";
			for (Map.Entry<Integer, String> entry : blkMap.entrySet()) {
				res = xor(res, entry.getValue());
			}
		}
		return value;
	}

	private String xor(String value1, String value2) {
		if (null == value1 || "".equals(value1)) {
			return value2;
		}
		if (null == value2 || "".equals(value2)) {
			return value1;
		}
		int result = Integer.parseInt(value1, 16)
				^ Integer.parseInt(value2, 16);
		return Integer.toHexString(result);
	}


	public static void main(String[] args) {
		new Main();
	}

}


