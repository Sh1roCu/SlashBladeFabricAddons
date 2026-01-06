package com.exfantasycode.mclib.Utils.RandomUtil;

import java.util.Random;

public class RandomNumber {
    static Random random = new Random();

    public static int randomint(int a) {

        return random.nextInt(a);
    }
}
