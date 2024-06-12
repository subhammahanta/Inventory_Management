package com.inventory.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.inventory.Exception.NoDataFoundException;
import com.inventory.dto.InvoiceDto;
import com.inventory.dto.ProductInvoiceApiResponseDto;
import com.inventory.model.SaleDetails;
import com.inventory.model.Sales;
import com.inventory.repository.SalesRepository;
import com.inventory.service.InvoiceService;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class InvoiceServiceImpl implements InvoiceService {



    @Autowired
   private SalesRepository salesRepository;


    @Override
    public List<InvoiceDto> getInvoice(int userId, String customerName, int page, int size) {
   log.info("ProductServiceImpl::getInvoice--> adminId{} ", userId);

        // Total no of sales done by that particular admin
        int totalSaleCount = salesRepository.countSalesByUserId(userId);

        // Sorting Data according to Date
        Pageable pageable = PageRequest.of(page, size).withSort(Sort.by(Sort.Direction.DESC, "saleDate"));

        Page<Sales> pageResult = salesRepository.findBySoldByAndCustomerName(userId, customerName, pageable);
        List<Sales> sales = pageResult.getContent();

        log.info("Sales Object of a user after fetching from DB  Object-->{} ", sales);

        List<InvoiceDto> invoiceDtos = new ArrayList<>();

        for (Sales item : sales) {

            log.info("Rendring hrough each Sale Object of the user {} ", item);

            InvoiceDto invoiceDto = new InvoiceDto();
            invoiceDto.setCustomerName(item.getCustomerName());
            invoiceDto.setDescription(item.getDescription());
            invoiceDto.setTotalAmount(item.getTotalAmount());
            invoiceDto.setPhoneNo(item.getPhoneNumber());
            invoiceDto.setDate(item.getSaleDate());
            invoiceDto.setSoldBy(item.getSoldBy());
            invoiceDto.setOrderId(item.getOrderId());
            invoiceDto.setTotalBills(totalSaleCount);

            List<ProductInvoiceApiResponseDto> productInvoiceApiResponseDtos = new ArrayList<>();

            for (SaleDetails items : item.getSaleDetails()) {
                ProductInvoiceApiResponseDto product = new ProductInvoiceApiResponseDto();
                product.setProductCode(items.getProductCode());
                product.setTotalPrice(items.getTotalPrice());
                product.setProductName(items.getProductName());
                product.setQuantity(items.getQuantity());
                product.setSellingPrice(items.getSellingPrice());
                productInvoiceApiResponseDtos.add(product);
            }
            invoiceDto.setProducts(productInvoiceApiResponseDtos);

            invoiceDtos.add(invoiceDto);

        }

        if (invoiceDtos.isEmpty()) {
            throw new NoDataFoundException("No such Resourse Found");
        }

        return invoiceDtos;
    }

    @Override
    public List<InvoiceDto> getInvoiceUserIdAndDate(int userId, Date saleDate) {
        log.info("ProductServiceImpl::getSalesByUserIdAndDate captured userId {} and Date {} ", userId, saleDate);

        log.info("ProductServiceImpl::getInvoice--> adminId{} saleDate { }", userId,saleDate);

        // Total no of sales done by that particular admin
        int totalSaleCount = salesRepository.countSalesByUserId(userId);

        List<Sales> sales = salesRepository.findBySoldByAndSaleDate(userId, saleDate);

        log.info("Sales Object of a user after fetching from DB  Object-->{} ", sales);

        List<InvoiceDto> invoiceDtos = new ArrayList<>();

        for (Sales item : sales) {

            log.info("Rendring hrough each Sale Object of the user {} ", item);

            InvoiceDto invoiceDto = new InvoiceDto();
            invoiceDto.setCustomerName(item.getCustomerName());
            invoiceDto.setDescription(item.getDescription());
            invoiceDto.setTotalAmount(item.getTotalAmount());
            invoiceDto.setPhoneNo(item.getPhoneNumber());
            invoiceDto.setDate(item.getSaleDate());
            invoiceDto.setSoldBy(item.getSoldBy());
            invoiceDto.setOrderId(item.getOrderId());
            invoiceDto.setTotalBills(totalSaleCount);

            List<ProductInvoiceApiResponseDto> productInvoiceApiResponseDtos = new ArrayList<>();

            for (SaleDetails items : item.getSaleDetails()) {
                ProductInvoiceApiResponseDto product = new ProductInvoiceApiResponseDto();
                product.setProductCode(items.getProductCode());
                product.setTotalPrice(items.getTotalPrice());
                product.setProductName(items.getProductName());
                product.setQuantity(items.getQuantity());
                product.setSellingPrice(items.getSellingPrice());
                productInvoiceApiResponseDtos.add(product);
            }
            invoiceDto.setProducts(productInvoiceApiResponseDtos);

            invoiceDtos.add(invoiceDto);

        }

        if (invoiceDtos.isEmpty()) {
            throw new NoDataFoundException("No Data found");
        }

        return invoiceDtos;

    }

    @Override
    public List<InvoiceDto> findByUserIdAndSearchValue(int userId, String searchValue, int page,int size) {

          // Total no of sales done by that particular admin
          int totalSaleCount = salesRepository.countSalesByUserId(userId);

           // Sorting Data according to Date
        Pageable pageable = PageRequest.of(page, size).withSort(Sort.by(Sort.Direction.DESC, "saleDate"));
      
              Page<Sales> pageData = salesRepository.findByUserIdAndSearchValue(userId, searchValue, pageable);

              List<Sales> sales= pageData.getContent();


              log.info("Sales Object of a user after fetching from DB  Object-->{} ", sales);

              List<InvoiceDto> invoiceDtos = new ArrayList<>();
      
              for (Sales item : sales) {
      
                  log.info("Rendring hrough each Sale Object of the user {} ", item);
      
                  InvoiceDto invoiceDto = new InvoiceDto();
                  invoiceDto.setCustomerName(item.getCustomerName());
                  invoiceDto.setDescription(item.getDescription());
                  invoiceDto.setTotalAmount(item.getTotalAmount());
                  invoiceDto.setPhoneNo(item.getPhoneNumber());
                  invoiceDto.setDate(item.getSaleDate());
                  invoiceDto.setSoldBy(item.getSoldBy());
                  invoiceDto.setOrderId(item.getOrderId());
                  invoiceDto.setTotalBills(totalSaleCount);
      
                  List<ProductInvoiceApiResponseDto> productInvoiceApiResponseDtos = new ArrayList<>();
      
                  for (SaleDetails items : item.getSaleDetails()) {
                      ProductInvoiceApiResponseDto product = new ProductInvoiceApiResponseDto();
                      product.setProductCode(items.getProductCode());
                      product.setTotalPrice(items.getTotalPrice());
                      product.setProductName(items.getProductName());
                      product.setQuantity(items.getQuantity());
                      product.setSellingPrice(items.getSellingPrice());
                      productInvoiceApiResponseDtos.add(product);
                  }
                  invoiceDto.setProducts(productInvoiceApiResponseDtos);
      
                  invoiceDtos.add(invoiceDto);
      
              }
      
              if (invoiceDtos.isEmpty()) {
                  throw new NoDataFoundException("No such Resourse Found");
              }
      
              return invoiceDtos;
    }

}
