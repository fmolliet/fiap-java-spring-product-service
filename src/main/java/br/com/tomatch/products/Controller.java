package br.com.tomatch.products;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.tomatch.products.dto.ProductDto;
import br.com.tomatch.products.form.ProductForm;

@RestController
@RequestMapping("")
public class Controller {
	
	@Autowired
	private ProductService service;
	
	@GetMapping
	@Cacheable(value = "listaProdutos")
	public ResponseEntity<Page<ProductDto>> getAll(
			@PageableDefault(sort = "_id", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao) {
		return service.list(paginacao);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> geById(@PathVariable("id") String id) {
		return service.getById(id);

	}
	
	@PutMapping("/{id}")
	@CacheEvict(value = "listaProdutos", allEntries = true)
	public ResponseEntity<ProductDto> update(@PathVariable("id") String id,
			@Valid @RequestBody ProductForm form) {
		return service.update(id, form);

	}

	@DeleteMapping("/{id}")
	@CacheEvict(value = "listaProdutos", allEntries = true)
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") String id) {
		return service.delete(id);
	}

	@PostMapping
	@CacheEvict(value = "listaProdutos", allEntries = true)
	public ResponseEntity<ProductDto> create(@Valid @RequestBody ProductForm form, UriComponentsBuilder uriBuilder) {
		return service.create(form, uriBuilder);
	}


}
