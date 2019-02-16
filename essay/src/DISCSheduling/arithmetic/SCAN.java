package DISCSheduling.arithmetic;

import DISCSheduling.process.PCB;

import java.util.ArrayList;
import java.util.Collections;


public class SCAN {
	private int startPoint;
	private ArrayList<PCB> processArrayList;

	@SuppressWarnings("unchecked")
	public SCAN(Object arrayList,int startPoint){
		processArrayList = (ArrayList<PCB>) arrayList;
		this.startPoint = startPoint;
	}
	public void shedule(){
		int allThroughWay = 0;
		int minWay;
		int minWayNum;
		//String direction = "unknow";
		String direction = "left";
		String turnTo;
		Collections.sort(processArrayList);   //按照访问顺序排列
		System.out.print("SCAN算法的走道顺序为："+startPoint);
		while(!processArrayList.isEmpty()) {   //输出n条磁盘路径，包括一次掉头，所以+1
			minWay = 0x7FFFFFFF;
			minWayNum = -1;
			turnTo = null;
			for (int j = 0; j < processArrayList.size(); j++) {
				PCB aPCB = processArrayList.get(j);
				int distance = aPCB.getVisitWay() - startPoint;
				if (Math.abs(distance) < minWay && distance <= 0 && !direction.equals("right")) { //如果当前磁头不是向右走，且当前路径为左向
					minWay = Math.abs(distance);
					minWayNum = j;
					turnTo = "left";
				}
				if (Math.abs(distance) < minWay && distance > 0 && !direction.equals("left")) {  //如果当前磁头不是向左走，且当前路径为右向
					minWay = Math.abs(distance);
					minWayNum = j;
					turnTo = "right";
				}
			}
			if(minWayNum != -1){
				direction = turnTo;
				allThroughWay = allThroughWay + minWay;
				startPoint = processArrayList.get(minWayNum).getVisitWay();
				System.out.print(" -> "+processArrayList.get(minWayNum).getVisitWay());
				processArrayList.remove(minWayNum);   //找到后移出
			} else{
				if(direction.equals("right")){
					direction = "left";
				} else{
					direction = "right";
				}
			}
		}
		System.out.println();
		System.out.println("磁头走过的总道数为:"+allThroughWay);
	}
}
