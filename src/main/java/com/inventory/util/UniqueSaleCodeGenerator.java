package com.inventory.util;

public class UniqueSaleCodeGenerator {

    public static final String UNICOE="TR00";

    public static String generateUniqueCode(long nextSaleId) {
        return UNICOE+nextSaleId;
    }
}
