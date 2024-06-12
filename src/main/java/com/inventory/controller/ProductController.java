package com.inventory.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.inventory.dto.ProductDto;
import com.inventory.dto.ProductResponseDto;
import com.inventory.dto.ProductSaleDto;
import com.inventory.dto.SaleResponseDto;
import com.inventory.service.ProductService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/admin")
public class ProductController {


    @Autowired
     private ProductService productService;


     //Create or Update a Product
    @PostMapping("/product/create-update")
     public  ResponseEntity<ProductResponseDto>  createProduct(@RequestBody @Valid ProductDto productRequestDto){

      log.info("ProductController:: createProduct productRequestDtoBody {}  ",productRequestDto);
      
      ProductResponseDto productResponseDto= productService.addProduct(productRequestDto);
      
      log.info("ProductController:: createProduct productResponseDtoBody {}  ",productResponseDto);

        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);

     }


     //Get All Products
     @GetMapping("/product/all")
    public  ResponseEntity<List<ProductResponseDto>> getAllProductsById(@RequestParam("adminId") int adminId){
   
       log.info(" ProductController:: getAllProducts before getting all products from Data Base  ");
       
       List<ProductResponseDto>  list_products=  productService.getAllProductsById(adminId);
       
       log.info(" ProductController:: getAllProducts after fetching List<Products> from Data Base {} ",list_products);

        
       return new ResponseEntity<>(list_products,HttpStatus.OK);

     }



     //Sale's Api
     @PostMapping("/product/sales")
public ResponseEntity<SaleResponseDto> soldProducts(@RequestBody @Valid ProductSaleDto productSaleDto){

      log.info("ProductController:: soldProducts  captured Data --> {} ",productSaleDto);

      SaleResponseDto saleResponseDto=  productService.sales(productSaleDto);

      return new ResponseEntity<>(saleResponseDto,HttpStatus.OK);


}

//Delete Api-->set active to inactive
// @PutMapping("/prdouct/isActive/{productId}")
// public ResponseEntity<ProductResponseDto> isActiveProduct(@RequestBody ProductDto productDto ){

// log.info("ProductController:: isActiveProduct  captured Data --> {} ",productDto);

// productService.isActive(productDto);

//   return null;
// }
      


}
