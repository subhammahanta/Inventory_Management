package com.inventory.util;




public class UniqueProductCodeGenerator {


     public static final String UNICOE="PR00";

    public static String generateUniqueCode(long nextProductId) {
        return UNICOE+nextProductId;
    }
}
