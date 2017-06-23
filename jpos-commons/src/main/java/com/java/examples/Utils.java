package com.java.examples;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

/**
 * Created by miztli on 3/03/17.
 */
public class Utils {

    public static void logISOMsg(ISOMsg msg) {
        System.out.println("----ISO MESSAGE-----");
        try {
            System.out.println("  MTI : " + msg.getMTI());
            for (int i=1;i<=msg.getMaxField();i++) {
                if (msg.hasField(i)) {
                    System.out.println("    Field-"+i+" : "+msg.getString(i));
                }
            }
        } catch (ISOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("--------------------");
        }

    }
}
