package producerAndconsumer;

import producerAndconsumer.Product;

public class StoreHouse {
	private int avail = 10;
	private int full = 0;
	private int mutex = 1; // 缓冲区大小

	private Product[] products = new Product[10]; // 数组长度为仓库大小

	// 生产产品
	public synchronized void produce(Product product) {
		while (--avail < 0) { // 仓库已满,等待消费
			avail++;
			try {
				System.out.println("仓库已满,正在等待消费..");
				wait();
			} catch (InterruptedException e) {
				System.out.println("stop push product because other reasons");
			}
		}
		/*while (--mutex < 0) { // 缓冲区满
			mutex++;
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}*/
		// 仓库未满,将生产的产品入库
		products[full] = product;
		System.out.println(Thread.currentThread().getName() + " 生产了 " + product);
		full++;
		mutex++;
		notify();
	}

	// 消费产品
	public synchronized void consume() {
		Product product = null;
		while (--full < 0) { // 仓库为空，等待生产
			full++;
			try {
				System.out.println("仓库已空，正等待生产...");
				wait();
			} catch (InterruptedException e) {
				System.out.println("stop push product because other reasons");
			}
		}
		/*while (--mutex < 0) { // 缓冲区满
			mutex++;
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}*/
		// 仓库不空,等待消费
		product = products[full];
		products[full] = null;
		System.out.println(Thread.currentThread().getName() + " 消费了 " + product);
		avail++;
		mutex++;
		notify();
	}
}
