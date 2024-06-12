package com.inventory.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inventory.model.ProductInventory;

@Repository
public interface ProductRepository extends JpaRepository<ProductInventory,Integer> {


    @Query("SELECT i.quantity FROM ProductInventory i WHERE i.productCode = :productCode")
    Optional<Integer> findAvailableQuantityByProductCode(@Param("productCode") String productCode);

    @Query("SELECT i.quantity FROM ProductInventory i WHERE i.productId = :productId")
    Optional<Integer> findAvailableQuantityByProductId(@Param("productId") Integer productId);

  @Modifying
    @Transactional
    @Query("UPDATE ProductInventory i SET i.quantity = :quantity WHERE i.productCode = :productCode")
    void updateProductCount(@Param("quantity") int quantity, @Param("productCode") String productCode);



   @Query("SELECT COUNT(p) FROM ProductInventory p")
    long countAllProductInventory();

   List<ProductInventory> findByCreatedBy(int userId);

    

}
