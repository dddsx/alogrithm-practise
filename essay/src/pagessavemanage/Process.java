package pagessavemanage;

public class Process {
	private int pId;
	private int needPage;
	private int startAddress;
	private int pageLength;
	private boolean haveResource;
	private int pageTable[]; // 页表
	public Process(int pId,int needPageNum){
		this.pId = pId;
		this.needPage = needPageNum;
	}
	public int getpId() {
		return pId;
	}
	public int getNeedPage() {
		return needPage;
	}
	public boolean getHaveResource() {
		return haveResource;
	}
	public void setHaveResource(boolean haveResource) {
		this.haveResource = haveResource;
	}
	public int getStartAddress() {
		return startAddress;
	}
	public void setStartAddress(int startAddress) {
		this.startAddress = startAddress;
	}
	public int getPageLength() {
		return pageLength;
	}
	public void setPageLength(int pageLength) {
		this.pageLength = pageLength;
	}
	public int[] getPageTable() {
		return pageTable;
	}
	public void setPageTable(int[] pageTable) {
		this.pageTable = pageTable;
	}
}
