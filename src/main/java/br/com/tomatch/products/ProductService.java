package br.com.tomatch.products;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.tomatch.products.dto.ProductDto;
import br.com.tomatch.products.form.ProductForm;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository _repository;
	
	
	public ResponseEntity<ProductDto> getById(String id) {

		Optional<Product> product = _repository.findById(id);

		return ResponseEntity.ok().body(new ProductDto(product));
	}
	

	public ResponseEntity<Page<ProductDto>> list(Pageable paginacao) {
		
		try {

			Page<Product> produtos = _repository.findAll(paginacao);

			if (produtos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return ResponseEntity.ok().body(ProductDto.convertPage(produtos));
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<ProductDto> create(ProductForm form, UriComponentsBuilder uriBuilder) {
		try {
			Product produto = form.converter();

			_repository.save(produto);
			URI uri = uriBuilder.path("/products/{id}").buildAndExpand(produto.getId()).toUri();

			return ResponseEntity.created(uri).body(new ProductDto(produto));

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	public ResponseEntity<HttpStatus> delete(String id) {
		try {
			_repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<ProductDto> update(String id, @Valid ProductForm form) {
		Optional<Product> produtorData = _repository.findById(id);

		if (produtorData.isPresent()) {
			Product _product = produtorData.get();

			_product.setName(form.getName());
			_product.setPrice(form.getPrice());
			_product.setQuantity(form.getQuantity());
			_product.setStatus(form.getStatus());
			_product.setType(form.getType());

			_repository.save(_product);

			return ResponseEntity.ok().body(new ProductDto(_product));
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	

}
