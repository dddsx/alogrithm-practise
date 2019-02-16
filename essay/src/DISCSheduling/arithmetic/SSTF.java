package DISCSheduling.arithmetic;

import DISCSheduling.process.PCB;

import java.util.ArrayList;
import java.util.Collections;


public class SSTF {
	private int startPoint;
	private ArrayList<PCB> processArrayList;

	@SuppressWarnings("unchecked")
	public SSTF(Object arrayList, int startPoint) {
		processArrayList = (ArrayList<PCB>) arrayList;
		this.startPoint = startPoint;
	}

	public void shedule() {
		int allThroughWay = 0;
		int minWay;
		int minWayNum;
		Collections.sort(processArrayList); // 先按照访问顺序排列
		System.out.print("SSTF算法的走道顺序为：" + startPoint);
		while(!processArrayList.isEmpty()) {
			minWay = 0x7FFFFFFF;
			minWayNum = -1;
			for (int j = 0; j < processArrayList.size(); j++) {   //寻找当前磁头位置与未访问过的位置的最短路径
				PCB aPCB = processArrayList.get(j);
				int distance = Math.abs(aPCB.getVisitWay() - startPoint);
				if (distance < minWay) {
					minWay = distance;
					minWayNum = j;
				}
			}
			allThroughWay = allThroughWay + minWay;
			startPoint = processArrayList.get(minWayNum).getVisitWay();
			System.out.print(" -> "+processArrayList.get(minWayNum).getVisitWay());
			processArrayList.remove(minWayNum);    //找到后移出
		}
		System.out.println();
		System.out.println("磁头走过的总道数为:"+allThroughWay);
	}
}
