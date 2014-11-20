package com.sunlights.common.utils;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ArithUtilTest {

    @Test
    public void testDiv() throws Exception {
        BigDecimal b1 = new BigDecimal("19000000000");
        BigDecimal b2 = new BigDecimal("100000000");
        System.out.println("[b1]" + b1);
        double div = ArithUtil.div(b1.doubleValue(), b2.doubleValue(), 2);
        System.out.println("[div]" + div);
    }
}