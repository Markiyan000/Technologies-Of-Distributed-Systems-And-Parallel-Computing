package com.example.lw3.management;

    import com.example.lw3.dto.ProductDto;
    import com.example.lw3.dto.ProductPostDto;
    import com.example.lw3.service.ProductService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/management/products")
@RequiredArgsConstructor
public class ManagementProductsController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> save(@RequestBody ProductPostDto productPostDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(productService.save(productPostDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable Long id, @RequestBody ProductPostDto productPostDto) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(productService.update(id, productPostDto));
    }
}
