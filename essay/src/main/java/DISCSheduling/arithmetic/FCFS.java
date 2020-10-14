package DISCSheduling.arithmetic;

import DISCSheduling.process.PCB;

import java.util.ArrayList;
import java.util.Collections;


public class FCFS {
	private int startPoint;
	private ArrayList<PCB> processArrayList;

	@SuppressWarnings("unchecked")
	public FCFS(Object arrayList,int startPoint){
		processArrayList = (ArrayList<PCB>) arrayList;
		this.startPoint = startPoint;
	}
	public void shedule(){
		int allThroughWay = 0;
		Collections.sort(processArrayList);   //按照访问顺序排列
		System.out.print("FCFS算法的走道顺序为："+startPoint);
		for(int i=0;i<processArrayList.size();i++){
			PCB aPCB = processArrayList.get(i);
			allThroughWay = allThroughWay + Math.abs(aPCB.getVisitWay() - startPoint);
			startPoint = aPCB.getVisitWay();
			System.out.print(" -> "+aPCB.getVisitWay());
		}
		System.out.println();
		System.out.println("磁头走过的总道数为:"+allThroughWay);
	}
}
