package com.inventory.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.inventory.dto.InvoiceDto;
import com.inventory.model.Sales;

public interface InvoiceService {

    List<InvoiceDto> getInvoice(int adminId, String customerName, int page, int size);

    List<InvoiceDto> getInvoiceUserIdAndDate(int adminId, Date date);

     List<InvoiceDto> findByUserIdAndSearchValue( int userId,
                                    String searchValue,
                                      int page,int size);



}
