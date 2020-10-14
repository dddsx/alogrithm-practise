package CPUScheduling.process.schedule;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.LinkedList;

import controlblock.PCB;
import CPUScheduling.test.Test;

public class RR {

	protected LinkedList<PCB> processQueue;
	protected DecimalFormat dF;

	private static final double TIME_SLICE = 1.5;

	public RR() {
		processQueue = new LinkedList<PCB>();
		dF = new DecimalFormat("0.00");
	}

	public void schedule() {
		dF.setRoundingMode(RoundingMode.HALF_EVEN);

		sortByArriveTime(processQueue);

		PCB process;
		double currentTime = 0;
		double arriveTime;
		double needTime;
		double aveRunTime = 0; //平均周转时间
		double aveWeiRunTime = 0; //平均带权周转时间

		while (!processQueue.isEmpty()) {
			process = processQueue.pollFirst();
			needTime = process.getNeedTime();
			arriveTime = process.getArriveTime();
			System.out.print("进程:" + process.getPid() + ",");
			System.out.print("到达时间:" + dF.format(process.getArriveTime()) + ",");
			System.out.print("还需要时间" + dF.format(process.getNeedTime())+",");

			if (currentTime < arriveTime)
				currentTime = arriveTime;
			System.out.print("开始时间:" + dF.format(currentTime) + ",");

			if (TIME_SLICE < needTime) {
				process.incHavaCome();
				currentTime += TIME_SLICE;
				System.out.println("进程中断时间:" + dF.format(currentTime));
				process.setNeedTime(needTime - TIME_SLICE);
				for (int i = 0; i < processQueue.size(); i++) { // 找到未就绪进程的前一个位置,将此进程加入
					if (processQueue.get(i).getArriveTime() > currentTime) {
						processQueue.add(i, process);
						break;
					} else if (i == processQueue.size() - 1) { // 当前所有进程就绪，添加到末尾
						processQueue.add(process);
						break;
					}
				}
			} else {
				currentTime += needTime;
				aveRunTime = aveRunTime+(currentTime-process.getArriveTime())/Test.getMaxProcessNum();
				for(int i=0;i<process.getHavaCome();i++){
					process.addNeedTime(TIME_SLICE);
				}
				aveWeiRunTime = aveWeiRunTime+(currentTime-process.getArriveTime())/process.getNeedTime()/Test.getMaxProcessNum();
				System.out.println("结束时间" + dF.format(currentTime));
			}
		}
		System.out.println("平均周转时间为:"+dF.format(aveRunTime)+"  平均带权周转时间为:"+dF.format(aveWeiRunTime));
	}

	public void addProcess(int pid, double arriveTime, double needTime) {
		processQueue.push(new PCB(pid, arriveTime, needTime));
	}

	private <T> void sortByArriveTime(LinkedList<PCB> processQueue) {
		processQueue.sort((p1, p2) -> {
			Double p1Time = p1.getArriveTime();
			Double p2Time = p2.getArriveTime();
			return p1Time.compareTo(p2Time);
		});
	}
}
