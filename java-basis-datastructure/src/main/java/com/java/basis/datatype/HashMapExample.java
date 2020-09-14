package com.java.basis.datatype;

import java.lang.reflect.Proxy;

public class HashMapExample {
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;
    public static void main(String[] args) {
        long a=6/24;
        final boolean validate=(20 % 16) == (15 & 20);//16=2^4  15=2^4-1
        final long rightOffset=4294901760L>>>16;
        System.out.println(rightOffset);
        System.out.println(validate);

        HashMapExample hashMapExample=new HashMapExample();
        hashMapExample.getClass().getClassLoader();
    }
}
