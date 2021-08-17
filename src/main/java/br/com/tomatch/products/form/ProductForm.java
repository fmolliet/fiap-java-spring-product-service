package br.com.tomatch.products.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.tomatch.products.Product;
import br.com.tomatch.products.ProductStatus;
import br.com.tomatch.products.ProductType;

public class ProductForm {
	
	@NotNull
    @NotEmpty
    @Length(min = 5)
	private String name;
	
	@NotNull
    private Integer quantity;
	
	
    @NotNull
    private Double price;
    
	@NotNull
    private ProductType type;
    private ProductStatus status = ProductStatus.VISIBLE;
    
    @NotNull
    @NotEmpty
    private String producerId;

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

	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
	}

	public String getProducerId() {
		return producerId;
	}

	public void setProducerId(String producerId) {
		this.producerId = producerId;
	}
	
	public Product converter() {
        return new Product(name, quantity, price, type, producerId);
    }
}
