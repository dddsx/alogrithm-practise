package producerAndconsumer;

import java.util.Random;

public class Producer implements Runnable {
	private String producerName = null;
	private StoreHouse storeHouse = null;
	private Random random = null;

	public Producer(String producerName, StoreHouse storeHouse) {
		this.producerName = producerName;
		this.storeHouse = storeHouse;
		random = new Random(8757536);
	}

	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}

	public String getProducerName() {
		return producerName;
	}

	public void run() {
		execProcuct();
	}

	private void execProcuct() {
		int i = 0;
		while (true) {
			i++;
			Product pro = new Product(i);
			storeHouse.produce(pro);
			try {
				Thread.sleep((long)(random.nextInt(10)*200+1000));  //�������ʱ��
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}
