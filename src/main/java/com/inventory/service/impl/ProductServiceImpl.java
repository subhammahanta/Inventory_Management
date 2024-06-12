package com.inventory.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;
import javax.transaction.Transactional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.inventory.Exception.BillingException;
import com.inventory.Exception.NoDataFoundException;
import com.inventory.Exception.ProductNameAlreadyExistException;
import com.inventory.Exception.ResourseNotFoundException;
import com.inventory.dto.InvoiceDto;
import com.inventory.dto.ProductDto;
import com.inventory.dto.ProductInvoiceApiResponseDto;
import com.inventory.dto.ProductResponseDto;
import com.inventory.dto.ProductSaleDetailsDto;
import com.inventory.dto.ProductSaleDto;
import com.inventory.dto.SaleResponseDto;
import com.inventory.model.ProductInventory;
import com.inventory.model.SaleDetails;
import com.inventory.model.Sales;
import com.inventory.repository.ProductRepository;
import com.inventory.repository.SaleDetailsRepository;
import com.inventory.repository.SalesRepository;
import com.inventory.service.ProductService;
import com.inventory.util.UniqueProductCodeGenerator;
import com.inventory.util.UniqueSaleCodeGenerator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private SaleDetailsRepository saleDetailsRepository;

    // Add or Update the product
    @Override
    public ProductResponseDto addProduct(ProductDto productDto) {

        log.info(" ProductServiceImpl::addProduct in Service Class productDto captured in service {} ->    ",
                productDto);

        ProductInventory pi = productDtoToProductInventory(productDto);

        long res = productRepository.countAllProductInventory();

        System.out.println(res + "TJUGJHGJHGYTGH");

        // If productId is Null -->ADD
        // else update

        if (productDto.getProductId() == null) {

            // System.out.println("TYASN<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            log.info("ProductServiceImpl::addProduct Create a new Product :");
            pi.setCreatedBy(productDto.getAdminId());
            log.info("Adding new Product");
            pi.setProductName(productDto.getProductName().toLowerCase());
            pi.setProductCode(UniqueProductCodeGenerator.generateUniqueCode(res));
            pi.setCreatedBy(productDto.getAdminId());
            pi.setIsActive(true);
            pi.setCreatedDate(new Date());
            pi.setCreatedBy(productDto.getAdminId());

        }

        else {
            // System.out.println("TYASN<<>>>>>>>");
            log.info("ProductServiceImpl::addProduct Update a new Product :");
            // update

            log.info("Updating Product");
            Optional<ProductInventory> product = Optional.ofNullable(productRepository
                    .findById(productDto.getProductId()).orElseThrow(() -> new RuntimeException("Product Not Found")));
            pi = product.get();
            pi.setUpdatedBy(productDto.getAdminId());
            pi.setProductId(productDto.getProductId());
            pi.setIsActive(productDto.getIsActive());
            pi.setMinQuantity(productDto.getMinimumQuantity());
            pi.setQuantity(productDto.getQuantity());
            pi.setProductName(productDto.getProductName());
            pi.setProductType(productDto.getProductType());
            pi.setQuantity(productDto.getQuantity());
            pi.setCostPrice(productDto.getCostPrice());
            pi.setSellingPrice(productDto.getSellingPrice());
            pi.setUpdatedDate(new Date());
            pi.setIsActive(productDto.getIsActive());
        }

        log.info(" ProductServiceImpl::addProduct in Service Class ProductInventory Object Before Saving to DB  {}  ",
                pi);


                try {
                    
                    pi = productRepository.save(pi);
                } catch (org.hibernate.exception.ConstraintViolationException e) {
                    throw new ProductNameAlreadyExistException();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
        log.info(" ProductServiceImpl::addProduct in Service Class ProductInventory Object After Saving to DB  {}  ",
                pi);

        return productToProductResponseDto(pi);

    }

    // get All Products By Id
    @Override
    public List<ProductResponseDto> getAllProductsById(int adminId) {

        List<ProductInventory> products = productRepository.findByCreatedBy(adminId);
        List<ProductResponseDto> list = null;

        if (products != null) {

            list = products.stream().map(item -> {

                return productToProductResponseDto(item);

            }).collect(Collectors.toList());
        } else {
            throw new RuntimeException("No Products Found");
        }

        if (list.isEmpty()) {
            throw new NoDataFoundException("No Products has been added");
        }
        return list;

    }

    // Sold Api
    @Transactional
    public SaleResponseDto sales(ProductSaleDto productSaleDto) {

        log.info(" ProductServiceImpl::sales in Service Class capturing Data ->{} ", productSaleDto);

        int adminId = productSaleDto.getAdminId();

        String customerName = productSaleDto.getCustomerName();

        String description = productSaleDto.getDescription();

        Double totalAmout = productSaleDto.getTotalAmount();

        String phoneNo = productSaleDto.getPhoneNo();

        Sales savedSaleObject = null;

        long res = salesRepository.countAllTransactions();

        List<ProductSaleDetailsDto> products = productSaleDto.getProducts();

        Double total_Amount = 0.0;
        List<SaleDetails> list = new ArrayList<>();

        if (products != null) {

            for (ProductSaleDetailsDto product : productSaleDto.getProducts()) {

                log.info("Rendring through each Product Details -->{} ", product);

                Optional<Integer> actualCount = productRepository
                        .findAvailableQuantityByProductId(product.getProductId());

                if (actualCount.isPresent()) {
                    int actual_count = actualCount.get();

                    log.info("Actual Count -- > {} ", actual_count);
                    log.info("Requested Quantity --> {} ", product.getQuantity());

                    if (product.getQuantity() <= actual_count) {

                        SaleDetails saleDetails = new SaleDetails(product.getProductCode(), product.getQuantity(),
                                product.getProductName());
                        saleDetails.setSellingPrice(product.getSellingPrice());
                        log.info("Setting totalPrice {} of each cart item-> ", product.getTotalPrice());
                        saleDetails.setTotalPrice(product.getTotalPrice());
                        log.info("Object Adding to SaleDetails Object--> {} ", saleDetails);
                        list.add(saleDetails);
                        log.info("Requested quantity  of Product is less than Actual Quantity ");

                        total_Amount = total_Amount + (product.getQuantity() * product.getSellingPrice());
                        int new_quantity = actual_count - product.getQuantity();
                        productRepository.updateProductCount(new_quantity, product.getProductCode());

                    } else {
                        log.info("Product Quantity requested is more than the Quantity persisted in DB ");

                        throw new RuntimeException(
                                "Product Quantity requested is more than the Quantity persisted in DB");
                        // System.out.println("Unable to Find ACtual count of the Product");
                    }
                } else {
                    log.info("Unable to find Actual Count of the Product");
                    throw new RuntimeException("Unable to find Actual Count of the Product from DB");
                }

                log.info("Total Amount calculated--> " + total_Amount);
                log.info("Total Amount from the Front End  " + productSaleDto.getTotalAmount());

            }

            System.out.println(Double.compare(total_Amount, totalAmout) == 0);

            if (Double.compare(total_Amount, totalAmout) == 0) {

                for (SaleDetails item : list) {
                    System.out.println(item);
                }

                // Sales sales = new Sales(totalAmout, customerName, phoneNo, description, new
                // Date(), adminId, list);
                Sales sales = new Sales(totalAmout, customerName, phoneNo, description, phoneNo, new Date(), adminId,
                        list);
                sales.setOrderId(UniqueSaleCodeGenerator.generateUniqueCode(res));
                savedSaleObject = salesRepository.save(sales);
            } else {

                System.out.println("Total Amount from the FrontEnd and BackENd Calculated are not Same");
                throw new RuntimeException("Total Amount came from FrontEnd and BackEnd calculated are not same");

            }

        } else {
            log.info("ProductServiceImpl::sales in Service Class No Product Details From FrontEnd ");
            throw new RuntimeException("No Product Data information from FrontEnd");
        }

        Integer saleId = savedSaleObject.getSalesId();
        SaleResponseDto saleResponseDto = null;
        if (saleId != null) {
            saleResponseDto = new SaleResponseDto();
            saleResponseDto.setSaleId(saleId);
            saleResponseDto.setSuccess(true);
        } else {
            log.info("Unable To create Bill");
            throw new BillingException("Unable to create Bill");
        }

        return saleResponseDto;

    }

    // Get Invoice

    // @Override
    // public List<InvoiceDto> getInvoice(int userId, String customerName, int page,
    // int size) {

    // log.info("ProductServiceImpl::getInvoice--> adminId{} ", userId);

    // // Total no of sales done by that particular admin
    // int totalSaleCount = salesRepository.countSalesByUserId(userId);

    // // Sorting Data according to Date
    // Pageable pageable = PageRequest.of(page,
    // size).withSort(Sort.by(Sort.Direction.DESC, "saleDate"));

    // Page<Sales> pageResult = salesRepository.findBySoldByAndCustomerName(userId,
    // customerName, pageable);
    // List<Sales> sales = pageResult.getContent();

    // log.info("Sales Object of a user after fetching from DB Object-->{} ",
    // sales);

    // List<InvoiceDto> invoiceDtos = new ArrayList<>();

    // for (Sales item : sales) {

    // log.info("Rendring hrough each Sale Object of the user {} ", item);

    // InvoiceDto invoiceDto = new InvoiceDto();
    // invoiceDto.setCustomerName(item.getCustomerName());
    // invoiceDto.setDescription(item.getDescription());
    // invoiceDto.setTotalAmount(item.getTotalAmount());
    // invoiceDto.setPhoneNo(item.getPhoneNumber());
    // invoiceDto.setDate(item.getSaleDate());
    // invoiceDto.setSoldBy(item.getSoldBy());
    // invoiceDto.setTransactionId(item.getTransactionId());
    // invoiceDto.setTotalBills(totalSaleCount);

    // List<ProductInvoiceApiResponseDto> productInvoiceApiResponseDtos = new
    // ArrayList<>();

    // for (SaleDetails items : item.getSaleDetails()) {
    // ProductInvoiceApiResponseDto product = new ProductInvoiceApiResponseDto();
    // product.setProductCode(items.getProductCode());
    // product.setTotalPrice(items.getTotalPrice());
    // product.setProductName(items.getProductName());
    // product.setQuantity(items.getQuantity());
    // product.setSellingPrice(items.getSellingPrice());
    // productInvoiceApiResponseDtos.add(product);
    // }
    // invoiceDto.setProducts(productInvoiceApiResponseDtos);

    // invoiceDtos.add(invoiceDto);

    // }

    // if (invoiceDtos.isEmpty()) {
    // throw new NoDataFoundException("No such Resourse Found");
    // }

    // return invoiceDtos;
    // }

    // Get Invoice with AdminId and Date
    // @Override
    // public List<InvoiceDto> getSalesByUserIdAndDate(int userId, Date saleDate) {

    // log.info("ProductServiceImpl::getSalesByUserIdAndDate captured userId {} and
    // Date {} ", userId, saleDate);

    // log.info("ProductServiceImpl::getInvoice--> adminId{} ", userId);

    // // Total no of sales done by that particular admin
    // int totalSaleCount = salesRepository.countSalesByUserId(userId);

    // List<Sales> sales = salesRepository.findBySoldByAndSaleDate(userId,
    // saleDate);

    // log.info("Sales Object of a user after fetching from DB Object-->{} ",
    // sales);

    // List<InvoiceDto> invoiceDtos = new ArrayList<>();

    // for (Sales item : sales) {

    // log.info("Rendring hrough each Sale Object of the user {} ", item);

    // InvoiceDto invoiceDto = new InvoiceDto();
    // invoiceDto.setCustomerName(item.getCustomerName());
    // invoiceDto.setDescription(item.getDescription());
    // invoiceDto.setTotalAmount(item.getTotalAmount());
    // invoiceDto.setPhoneNo(item.getPhoneNumber());
    // invoiceDto.setDate(item.getSaleDate());
    // invoiceDto.setSoldBy(item.getSoldBy());
    // invoiceDto.setTransactionId(item.getTransactionId());
    // invoiceDto.setTotalBills(totalSaleCount);

    // List<ProductInvoiceApiResponseDto> productInvoiceApiResponseDtos = new
    // ArrayList<>();

    // for (SaleDetails items : item.getSaleDetails()) {
    // ProductInvoiceApiResponseDto product = new ProductInvoiceApiResponseDto();
    // product.setProductCode(items.getProductCode());
    // product.setTotalPrice(items.getTotalPrice());
    // product.setProductName(items.getProductName());
    // product.setQuantity(items.getQuantity());
    // product.setSellingPrice(items.getSellingPrice());
    // productInvoiceApiResponseDtos.add(product);
    // }
    // invoiceDto.setProducts(productInvoiceApiResponseDtos);

    // invoiceDtos.add(invoiceDto);

    // }

    // if (invoiceDtos.isEmpty()) {
    // throw new NoDataFoundException("No Data found");
    // }

    // return invoiceDtos;

    // }

    // Product to ProductResponseDto
    public ProductResponseDto productToProductResponseDto(ProductInventory productInventory) {

        ProductResponseDto pDto = new ProductResponseDto();

        pDto.setAdminId(productInventory.getCreatedBy());
        pDto.setProductId(productInventory.getProductId());
        pDto.setProductName(productInventory.getProductName());
        pDto.setProductType(productInventory.getProductType());
        pDto.setMinimumQuantity(productInventory.getMinQuantity());
        pDto.setQuantity(productInventory.getQuantity());
        pDto.setCostPrice(productInventory.getCostPrice());
        pDto.setSellingPrice(productInventory.getSellingPrice());
        pDto.setProductCode(productInventory.getProductCode());
        pDto.setIsActive(productInventory.getIsActive());
        return pDto;

    }

    // ProductDto to ProductResponseDto
    public ProductInventory productDtoToProductInventory(ProductDto productDto) {
        // productName;
        // productType quantity minimumQuantity costPrice sellingPrice;
        ProductInventory pi = new ProductInventory();
        if (productDto.getProductId() != null) {
            pi.setProductId(productDto.getProductId());
        }
        pi.setCreatedBy(productDto.getAdminId());
        pi.setIsActive(productDto.getIsActive());
        pi.setProductName(productDto.getProductName());
        pi.setProductType(productDto.getProductType());
        pi.setQuantity(productDto.getQuantity());
        pi.setMinQuantity(productDto.getMinimumQuantity());
        pi.setCostPrice(productDto.getCostPrice());
        pi.setSellingPrice(productDto.getSellingPrice());
        return pi;
    }

    // @Override
    // public ProductResponseDto isActive(ProductDto productDto) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'isActive'");
    // }

}
