package DISCSheduling.test;

import java.util.ArrayList;
import arithmetic.FCFS;
import arithmetic.SCAN;
import arithmetic.SSTF;
import process.PCB;

public class Test {
	private static int PROCESS_NUM = 8;
	
	public static void main(String[] args) {
		ArrayList<PCB> arrayListPCB = new ArrayList<>();
		arrayListPCB.add(new PCB(1,98));
		arrayListPCB.add(new PCB(2,183));
		arrayListPCB.add(new PCB(3,37));
		arrayListPCB.add(new PCB(4,122));
		arrayListPCB.add(new PCB(5,14));
		arrayListPCB.add(new PCB(6,124));
		arrayListPCB.add(new PCB(7,65));
		arrayListPCB.add(new PCB(8,67));
		FCFS aFCFS = new FCFS(arrayListPCB.clone(),53);
		aFCFS.shedule();
		SSTF aSSTF = new SSTF(arrayListPCB.clone(),53);
		aSSTF.shedule();
		SCAN aSCAN = new SCAN(arrayListPCB.clone(),53);
		aSCAN.shedule();
	}

	public static int getPROCESS_NUM() {
		return PROCESS_NUM;
	}
}
