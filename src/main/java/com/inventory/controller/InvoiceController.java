package com.inventory.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.dto.InvoiceDto;
import com.inventory.dto.ProductInvoiceApiResponseDto;
import com.inventory.service.InvoiceService;
import com.inventory.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/admin")
@Slf4j
public class InvoiceController {


    @Autowired
    private  InvoiceService invoiceService;

  //get Invoice by adminId and searchValue
    @GetMapping("/invoice")
    public ResponseEntity<List<InvoiceDto>> allOrders(@RequestParam(value = "adminId", defaultValue = "0")Integer adminId,
    @RequestParam(value = "page", defaultValue = "0",required = false)Integer page,
    @RequestParam(value = "size",defaultValue = "5",required = false)Integer size,
    @RequestParam(value = "search",defaultValue = "",required = false)String search,
    @RequestParam(value = "date",defaultValue = "",required = false)String date) throws ParseException
    
    {
       
         if(search.isEmpty()==false && date.isEmpty()==false){

                System.out.println("SEARCH______________________________-");

          List<InvoiceDto> list=  invoiceService.findByUserIdAndSearchValue(adminId, search, page, size);
         
          log.info("Capturing InvoiceDto List --> {} ",list);
    
    
    
            return new ResponseEntity<>(list,HttpStatus.OK);
         }


       log.info("InvoiceController::getInvoice caputred userId ,page no , page size --> {} {} {}",adminId,page,size);
            if( search.isBlank() && date.length()!=0 || date!=null){

               System.out.println("I am Inside Date/...........................................");
              try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date2= dateFormat.parse(date);
                List<InvoiceDto> list=   invoiceService.getInvoiceUserIdAndDate(adminId, date2);
                return new ResponseEntity<>(list,HttpStatus.OK);
                
              } catch (Exception e) {
                 e.printStackTrace();
              }
            }

      // List<InvoiceDto> list=  productService.getInvoice(adminId,customerName ,page,size );
       List<InvoiceDto> list=  invoiceService.findByUserIdAndSearchValue(adminId, search, page, size);
         
      log.info("Capturing InvoiceDto List --> {} ",list);



        return new ResponseEntity<>(list,HttpStatus.OK);
     }

     //get the invoice by admin id and Date
  //    @GetMapping("/invoice/by-date")
  //    public ResponseEntity<List<InvoiceDto>> getInvoiceByDate(@RequestParam("adminId") int adminId,  @RequestParam("date") 
  //    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date){
       
       
  //      log.info("InvoiceController::getInvoice caputred adminId and Date--> {} {} ",adminId,date);
   
  //   //  List<InvoiceDto> list=   productService.getSalesByUserIdAndDate(adminId,date);
  //     List<InvoiceDto> list=   invoiceService.getInvoiceUserIdAndDate(adminId,date);


  //    log.info("InvoiceController::getInvoice list got from Service Layer-> {}  ",list);

  //    return new ResponseEntity<>(list,HttpStatus.OK);
  // }


}
