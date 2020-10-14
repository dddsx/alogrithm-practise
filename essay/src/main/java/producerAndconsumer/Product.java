package producerAndconsumer;

public class Product {
	private int productId = 0;

	public Product(int productId) {
		this.productId = productId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "" + productId;
	}
}
