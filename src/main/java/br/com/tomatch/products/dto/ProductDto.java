package br.com.tomatch.products.dto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.tomatch.products.Product;
import br.com.tomatch.products.ProductStatus;
import br.com.tomatch.products.ProductType;

public class ProductDto {
	
	private String id;
	private String name;
    private Integer quantity;
    private Double price;
    
    private ProductType type;
    private ProductStatus status = ProductStatus.VISIBLE;

    private String producerId;

    public ProductDto(Product product ) {
        this.name = product.getName();
        this.quantity = product.getQuantity();
        this.price = product.getPrice();
        this.type = product.getType();
        this.producerId = product.getProducerId();
    }

    public ProductDto(String id, String name, Integer quantity, Double price, ProductType type,
    		String producerId, ProductStatus status) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.type = type;
        this.producerId = producerId;
        this.status = status;
    }
    
    public ProductDto(Optional<Product> product) {
		if ( product.isPresent() ) {
			Product prod = product.get();
			this.id = prod.getId();
			this.name = prod.getName();
	        this.quantity = prod.getQuantity();
	        this.price = prod.getPrice();
	        this.type = prod.getType();
	        this.producerId = prod.getProducerId();
		}
	}

    public ProductDto() {

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
	
	public static Page<ProductDto> convertPage(Page<Product> product) {
		return product.map(ProductDto::new);
	}
	
	public static List<ProductDto> convertList(List<Product> product) {
		return product.stream().map(ProductDto::new).collect(Collectors.toList());
	}
}
