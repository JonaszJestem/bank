package com.jonaszwiacek.bank.services;

import java.math.BigInteger;
import java.security.SecureRandom;


public class ResetTokenGenerator
{
    private static SecureRandom secureRandom = new SecureRandom();


    public static String nextPassword()
    {
        return new BigInteger( 130, secureRandom ).toString( 32 );
    }
}
