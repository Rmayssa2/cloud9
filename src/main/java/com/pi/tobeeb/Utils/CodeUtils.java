package com.pi.tobeeb.Utils;

import java.util.Random;
import java.util.UUID;

public class CodeUtils {

    public static String getCode() {
        return UUID.randomUUID().toString();
    }
    public static String getSmsCode(){
        Random r = new Random();
        int numbers = 100000 + (int)(r.nextFloat() * 899900);
        return String.valueOf(numbers);
    }
}