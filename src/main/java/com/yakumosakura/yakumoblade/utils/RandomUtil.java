package com.yakumosakura.yakumoblade.utils;

import java.util.Random;

public class RandomUtil {
    static Random random = new Random();

    public static int randomnum(int a) {

        return random.nextInt(a);
    }
}
