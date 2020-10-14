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
		Collections.sort(processArrayList);   //���շ���˳������
		System.out.print("FCFS�㷨���ߵ�˳��Ϊ��"+startPoint);
		for(int i=0;i<processArrayList.size();i++){
			PCB aPCB = processArrayList.get(i);
			allThroughWay = allThroughWay + Math.abs(aPCB.getVisitWay() - startPoint);
			startPoint = aPCB.getVisitWay();
			System.out.print(" -> "+aPCB.getVisitWay());
		}
		System.out.println();
		System.out.println("��ͷ�߹����ܵ���Ϊ:"+allThroughWay);
	}
}
