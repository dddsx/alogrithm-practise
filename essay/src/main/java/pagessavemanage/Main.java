package pagessavemanage;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("现在开始模拟页式存储的分配与回收");
		System.out.println("请先初始化内存！");
		Scanner scanner = new Scanner(System.in);
		System.out.println("输入内存页数:");
		int pageNum = scanner.nextInt();
		scanner.nextLine();
		System.out.println("输入页面大小:");
		int pageSize = scanner.nextInt();
		scanner.hasNextLine();
		Memory memory = new Memory(pageNum, pageSize);
		memory.initMemory();
		memory.initProcessList();
		System.out.println("现在根据菜单选项选择功能:");
		while (true) {
			System.out.println("1.创建新进程\n2.撤销进程\n3.输出进程的页表\n4.输出请求表\n5.退出");
			int choice;
			while (true) {
				try {
					choice = scanner.nextInt();
					scanner.nextLine();
					break;
				} catch (InputMismatchException e) {
					System.out.println("请输入一个数字:");
					scanner.nextLine();
					continue;
				}
			}
			switch(choice){
				case 1:memory.applyMemory(); break;
				case 2:memory.delProcess(); break;
				case 3:memory.printProcessPageTable(); break;
				case 4:memory.printRequestTable(); break;
				case 5:scanner.close(); System.exit(0);
				default:System.out.println("请输入正确数字");
					continue;
			}
		}
	}
}
