package producerAndconsumer;

import java.util.Random;

public class Consumer implements Runnable {
	private String consumerName = null;
	private StoreHouse storeHouse = null;
    private Random random = null;
    
	public Consumer(String consumerName, StoreHouse storeHouse) {
		this.consumerName = consumerName;
		this.storeHouse = storeHouse;
		random = new Random(54657575);
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	public String getConsumerName() {
		return consumerName;
	}

	public void run() {
		execConsume();
	}

	public void execConsume() {
		while (true) {
			storeHouse.consume();
			try {
				Thread.sleep((long)(random.nextInt(10)*300+2000));    //���Ѽ��ʱ��
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}
