package controlblock;
/*
 * 进程控制块
 */
public class PCB {
	private int pid;          //编号
	private double priority;  //优先级
	private double arriveTime;   //提交时间
	private double needTime;     //运行时间
	private int havaCome = 0;     //RR法中判断进程是否为多次执行

	public PCB(int pid, double arriveTime, double needTime) {
		this.pid = pid;
		this.arriveTime = arriveTime;
		this.needTime = needTime;
	}

	public PCB(int pid, double priority, double arriveTime, double needTime) {
		super();
		this.pid = pid;
		this.priority = priority;
		this.arriveTime = arriveTime;
		this.needTime = needTime;
	}

	public int getPid() {
		return pid;
	}

	public double getPriority() {
		return priority;
	}

	public void setPriority(double priority) {
		this.priority = priority;
	}

	public double getArriveTime() {
		return arriveTime;
	}

	public double getNeedTime() {
		return needTime;
	}

	public void setNeedTime(double needTime) {
		this.needTime = needTime;
	}

	public int getHavaCome() {
		return havaCome;
	}

	public void incHavaCome() {
		havaCome++;
	}

	public void addNeedTime(double add){
		needTime += add;
	}

}
