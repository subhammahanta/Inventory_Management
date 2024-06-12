package com.inventory.service;

import java.util.Date;
import java.util.List;

import com.inventory.dto.InvoiceDto;
import com.inventory.dto.ProductDto;
import com.inventory.dto.ProductInvoiceApiResponseDto;
import com.inventory.dto.ProductResponseDto;
import com.inventory.dto.ProductSaleDto;
import com.inventory.dto.SaleResponseDto;

public interface ProductService {


    ProductResponseDto addProduct(ProductDto productDto);

    // List<ProductResponseDto> getAllProducts();

     SaleResponseDto sales(ProductSaleDto productSaleDto);

    // ProductResponseDto isActive(ProductDto productDto);


      // List<InvoiceDto> getInvoice(int userId,String customerName,int page,int size);

    List<ProductResponseDto> getAllProductsById(int userId);

    // List<InvoiceDto> getSalesByUserIdAndDate(int adminId, Date saleDate);

}
