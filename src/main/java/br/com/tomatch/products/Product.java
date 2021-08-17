package br.com.tomatch.products;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "produtos")
public class Product {
	
	 	@Id
	    private String id;
	    private String name;
	    private Integer quantity;
	    private Double price;
	    
	    private ProductType type;
	    private ProductStatus status = ProductStatus.VISIBLE;

	    private String producerId;

	    public Product(String name, Integer quantity, Double price, ProductType type, String producerId) {
	        this.name = name;
	        this.quantity = quantity;
	        this.price = price;
	        this.type = type;
	        this.producerId = producerId;

	    }

	    public Product(String id, String name, Integer quantity, Double price, ProductType type,
	    		String producerId, ProductStatus status) {
	        this.id = id;
	        this.name = name;
	        this.quantity = quantity;
	        this.price = price;
	        this.type = type;
	        this.producerId = producerId;
	        this.status = status;
	    }

	    public Product() {

	    }
	    
		public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

	    public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

		public Double getPrice() {
			return price;
		}

		public void setPrice(Double price) {
			this.price = price;
		}

		public ProductType getType() {
			return type;
		}

		public void setType(ProductType type) {
			this.type = type;
		}

		public String getProducerId() {
			return producerId;
		}

		public void setProducerId(String producerId) {
			this.producerId = producerId;
		}




	    public ProductStatus getStatus() {
	        return status;
	    }

	    public void setStatus(ProductStatus status) {
	        this.status = status;
	    }


}
