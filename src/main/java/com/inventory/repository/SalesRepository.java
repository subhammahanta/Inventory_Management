package com.inventory.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inventory.model.Sales;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Integer> {

    @Query("SELECT s FROM Sales s WHERE s.soldBy = :userId AND "
            + "( :customerName IS NULL OR s.customerName LIKE %:customerName% )")
    Page<Sales> findBySoldByAndCustomerName(@Param("userId") int userId,
            @Param("customerName") String customerName,
            Pageable pageable);

    // Method to get sales details by user ID and date
    @Query("SELECT s FROM Sales s WHERE s.soldBy = :userId AND DATE(s.saleDate) = :saleDate")
    List<Sales> findBySoldByAndSaleDate(@Param("userId") int userId,
            @Param("saleDate") Date saleDate);

    @Query("SELECT COUNT(s) FROM Sales s")
    long countAllTransactions();

    @Query("SELECT COUNT(s) FROM Sales s WHERE s.soldBy = :userId")
    int countSalesByUserId(@Param("userId") int userId);


    @Query("SELECT s FROM Sales s WHERE s.soldBy = :userId AND "
    + "(:searchValue IS NULL OR "
    + "s.customerName LIKE CONCAT('%', :searchValue, '%') OR "
    + "s.phoneNumber LIKE CONCAT('%', :searchValue, '%') OR "
    + "s.orderId LIKE CONCAT('%', :searchValue, '%'))")
Page<Sales> findByUserIdAndSearchValue(@Param("userId") int userId,
                                   @Param("searchValue") String searchValue,
                                   Pageable pageable);


}
// @Query("SELECT s FROM Sales s WHERE s.soldBy = :userId AND " +
// "( :customerName IS NULL OR LOWER(s.customerName) LIKE LOWER(CONCAT('%',
// :customerName, '%')) ) AND " +
// "( :transactionId IS NULL OR s.transactionId = :transactionId )")
// Page<Sales> findBySoldByAndCustomerNameAndTransactionId(@Param("userId") int
// userId,
// @Param("customerName") String customerName,
// @Param("transactionId") String transactionId,
// Pageable pageable);
