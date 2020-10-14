package producerAndconsumer;



public class Main {
	public static void main(String[] args) {
		StoreHouse storeHouse = new StoreHouse();
		Producer producer = new Producer("生产者", storeHouse);
		Consumer comsumer = new Consumer("消费者", storeHouse);
		Thread t1 = new Thread(producer, producer.getProducerName());
		Thread t2 = new Thread(comsumer, comsumer.getConsumerName());
		t1.start();
		t2.start();
	}
}
