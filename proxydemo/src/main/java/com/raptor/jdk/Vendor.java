package com.raptor.jdk;

import lombok.Data;

/**
 * @author raptor
 * @description Vendor
 * @date 2022/4/27 16:39
 */
@Data
public class Vendor implements Sell {


    @Override
    public void sell() {
        System.out.println("In sell method");
    }

    @Override
    public void ad() {
        System.out.println("ad method");
    }

}
