package DISCSheduling.process;

public class PCB implements Comparable<PCB>{
	private int arriveOrder;
	private int visitWay;

	public PCB(){
		
	}
	public PCB(int arriveOrder,int visitWay){
		this.arriveOrder = arriveOrder;
		this.visitWay = visitWay;
	}
	public int getArriveOrder() {
		return arriveOrder;
	}
	public void setArriveOrder(int arriveOrder) {
		this.arriveOrder = arriveOrder;
	}
	public int getVisitWay() {
		return visitWay;
	}
	public void setVisitWay(int visitWay) {
		this.visitWay = visitWay;
	}
	
	public int compareTo(PCB p){
		return this.arriveOrder-p.arriveOrder;
	}
}
