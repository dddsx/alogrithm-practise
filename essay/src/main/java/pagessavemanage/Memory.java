package pagessavemanage;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Memory {
	private static final int INIT_PROCESS_NUM = 5;
	private static final int MAX_NEED_PAGE = 10;
	private static int pageStartAddress = 1024;
	private int pageSize; // 页面大小，单位为K
	private int pageNum; // 内存页面总数
	private int pageTable[]; // 内存页面使用情况
	ArrayList<Process> arrayListProcess; // 进程列表
	private Random random;
	private Scanner scanner;

	public Memory(int pageNum, int pageSize) {
		this.pageSize = pageSize;
		this.pageNum = pageNum;
		this.pageTable = new int[pageNum];
		random = new Random();
		scanner = new Scanner(System.in);
	}

	public int getPageSize() {
		return pageSize;
	}

	// 初始化内存
	public void initMemory() {
		for (int i = 0; i < pageTable.length; i++) {
			pageTable[i] = -1;
		}
		arrayListProcess = new ArrayList<>();
	}

	// 返回内存剩余块数
	public int getMemoryRest() {
		int rest = 0;
		for (int i = 0; i < pageTable.length; i++)
			if (pageTable[i] == -1)
				rest++;
		return rest;
	}

	public void initProcessList() {
		for (int i = 0; i < INIT_PROCESS_NUM; i++) {
			Process process = new Process(i, random.nextInt(MAX_NEED_PAGE) + 1);
			int needPage = process.getNeedPage();
			if (needPage <= getMemoryRest()) {
				int temp[] = new int[needPage];
				for (int j = 0; j < needPage; j++) {
					int memoryPiece = random.nextInt(pageNum);
					while (pageTable[memoryPiece] != -1) {
						memoryPiece = (memoryPiece + 1) % pageNum;
					}
					temp[j] = memoryPiece;
					pageTable[memoryPiece] = 1;
					process.setHaveResource(true);
				}
				process.setStartAddress(pageStartAddress);
				pageStartAddress += needPage;
				process.setPageTable(temp);
			} else {
				process.setHaveResource(false);
			}
			arrayListProcess.add(process);
		}
	}

	public void applyMemory() {
		System.out.println("输入新进程的进程号:");
		int pId = scanner.nextInt();
		scanner.nextLine();
		System.out.println("输入新进程的页数:");
		int pNeedPage = scanner.nextInt();
		scanner.nextLine();
		for (int i = 0; i < arrayListProcess.size(); i++) {
			if (arrayListProcess.get(i).getpId() == pId) {
				System.out.println("存在进程号冲突！");
				return;
			}
		}
		Process process = new Process(pId, pNeedPage);
		int needPage = process.getNeedPage();
		if (needPage <= getMemoryRest()) {
			int temp[] = new int[needPage];
			for (int i = 0; i < needPage; i++) {
				int memoryPiece = random.nextInt(pageNum);
				while (pageTable[memoryPiece] != -1) {
					memoryPiece = (memoryPiece + 1) % pageNum;
				}
				temp[i] = memoryPiece;
				pageTable[memoryPiece] = 1;
				process.setHaveResource(true);
			}
			process.setStartAddress(pageStartAddress);
			pageStartAddress += needPage;
			process.setPageTable(temp);
			System.out.println("进程申请资源成功。");
			printMemState();
		} else {
			System.out.println("内存不足，进程创建失败。");
			process.setHaveResource(false);
		}
		arrayListProcess.add(process);
	}

	public void printRequestTable() {
		System.out.println("|---------------------------------------------|\n|进程号	请求页面数	页表起始地址  "
				+ "页表长度	 状态                              |");
		for (int i = 0; i < arrayListProcess.size(); i++) {
			Process process = arrayListProcess.get(i);
			if (process.getHaveResource()) {
				System.out.println("|---------------------------------------------|\n|" + process.getpId() + "	"
						+ process.getNeedPage() + "	" + process.getStartAddress() + "	 " + process.getNeedPage()
						+ "	" + "已分配                           |");
			} else {
				System.out.println("|---------------------------------------------|\n|" + process.getpId() + "	"
						+ process.getNeedPage() + "	" + "无" + "  	" + "  无" + "	"
						+ "未分配                           |");
			}
		}
		System.out.println("|---------------------------------------------|\n");
	}

	public void printMemState() {
		int restPage = getMemoryRest();
		int busyPage = pageNum - restPage;
		System.out.println("内存使用情况----被进程占用页面数:" + busyPage + "  " + "空闲页面数：" + restPage);
	}

	public void printProcessPageTable() {
		Process process = null;
		System.out.println("输入要查询的进程的号数:");
		int pId = scanner.nextInt();
		for (int i = 0; i < arrayListProcess.size(); i++) {
			process = arrayListProcess.get(i);
			if (process.getpId() == pId)
				break;
		}
		if (process.getpId() != pId) {
			System.out.println("没有该进程。");
			return;
		}
		System.out.println("|---------------|\n" + "| 页号	页面号           |");
		System.out.println("|---------------|");
		for (int i = 0; i < process.getPageTable().length; i++) {
			System.out.println("| " + i + "	" + process.getPageTable()[i] + "	|");
		}
		System.out.println("|---------------|");
	}

	public void delProcess() {
		Process process = null;
		System.out.println("输入要撤销的进程号:");
		int pId = scanner.nextInt();
		for (int i = 0; i < arrayListProcess.size(); i++) {
			process = arrayListProcess.get(i);
			if (process.getpId() == pId) {
				process = arrayListProcess.remove(i);
				break;
			}
		}
		if (process == null) {
			System.out.println("没有该进程。");
			return;
		}
		int temp[] = process.getPageTable();
		if (temp != null) {
			for (int i = 0; i < temp.length; i++) {
				int memoryPiece = temp[i];
				pageTable[memoryPiece] = -1;
			}
		}
		System.out.println("撤销成功。");
		printMemState();
	}
}
