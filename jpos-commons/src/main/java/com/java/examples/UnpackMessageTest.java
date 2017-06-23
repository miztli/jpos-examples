package com.java.examples;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by miztli on 2/03/17.
 */
public class UnpackMessageTest {
    public static void main(String[] args) throws IOException, ISOException {
        File file = new File("/home/miztli/Workspace/test-example-code/jpos-impl/jpos-commons/src/main/resources/basic.xml");

        // Create Packager based on XML that contain DE type
//        GenericPackager packager = new GenericPackager("basic.xml");
        GenericPackager packager = new GenericPackager(new FileInputStream(file));

        // Print Input Data
        String data = "0200B2200000001000000000000000800000201234000000010000011072218012345606A5DFGR021ABCDEFGHIJ 1234567890";
        System.out.println("DATA : " + data);

        // Create ISO Message
        ISOMsg isoMsg = new ISOMsg();
        isoMsg.setPackager(packager);
        isoMsg.unpack(data.getBytes());

        // print the DE list
        logISOMsg(isoMsg);
    }

    private static void logISOMsg(ISOMsg msg) {
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
