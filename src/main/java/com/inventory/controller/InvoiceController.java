package com.inventory.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
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
    @RequestParam(value = "date",required = false) String date) throws ParseException
    
    {


      //For analytics page and when search bar of order page is null and date is given
      if(date.isBlank()==false && search.isBlank()){

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date2 = dateFormat.parse(date);
        
        // Step 2: Set the time zone to UTC
        
        System.out.println("Date captured--> "+date2);
        List<InvoiceDto> list= invoiceService.getInvoiceUserIdAndDate(adminId,date2);

        return new ResponseEntity<>(list,HttpStatus.OK);
      }

      if(search.length()==0&&  date.isBlank()){
           List<InvoiceDto> list= invoiceService.findByUserIdAndSearchValue(adminId, search, page, size);
           return new ResponseEntity<>(list,HttpStatus.OK);
      }
      
      if(search.length()!=0 && date.isBlank()){
        List<InvoiceDto> list= invoiceService.findByUserIdAndSearchValue(adminId, search, page, size);
        return new ResponseEntity<>(list,HttpStatus.OK);
   }



   if(search.isBlank()==false && date.isBlank()==false){
     
     
     
     
     List<InvoiceDto> list1= invoiceService.findByUserIdAndSearchValue(adminId, search, page, size); 
   

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    Date date2 = dateFormat.parse(date);
    

    
    

    
    System.out.println("Date captured--> "+date2);
    List<InvoiceDto> list2= invoiceService.getInvoiceUserIdAndDate(adminId,date2);

    List<InvoiceDto> list=new ArrayList<>(); 

    for(int i=0;i<list1.size();i++){

       for(int j=0;j<list2.size();j++){
           
         if(list1.get(i).getCustomerName().equals(list2.get(j).getCustomerName())){
            list.add(list1.get(i));
         }

        else  if(list1.get(i).getOrderId().equals(list2.get(j).getOrderId())){
            list.add(list1.get(i));
         }



         else  if(list1.get(i).getPhoneNo().equals(list2.get(j).getPhoneNo())){
          list.add(list1.get(i));
         }


       }

    }
     
  
   

         return new ResponseEntity<>(list,HttpStatus.OK);
   }
      

        return null;
     }

   
    

    //get the invoice by admin id and Date
  //    @GetMapping("/invoice/by-date")
  //    public ResponseEntity<List<InvoiceDto>> getInvoiceByDate(@RequestParam("adminId") int adminId,   
  //    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date){
       
       
  //      log.info("InvoiceController::getInvoice caputred adminId and Date--> {} {} ",adminId,date);
   
  //   //  List<InvoiceDto> list=   productService.getSalesByUserIdAndDate(adminId,date);
  //     List<InvoiceDto> list=   invoiceService.getInvoiceUserIdAndDate(adminId,date);


  //    log.info("InvoiceController::getInvoice list got from Service Layer-> {}  ",list);

  //    return new ResponseEntity<>(list,HttpStatus.OK);
  // }


    }