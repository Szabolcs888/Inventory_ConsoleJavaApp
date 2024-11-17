package com.myinventoryapp.util;

import com.myinventoryapp.util.IdUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdUtilsTest {

    @Test
    void testGenerateId() {
        int id = IdUtils.generateId();
        System.out.println(id);

        boolean isTheNumberCorrect = id >= 1000000 && id <= 9999999;
        assertTrue(isTheNumberCorrect, "ID should be between 1000000 and 9999999, but it is not. The number is " + id);
    }
}