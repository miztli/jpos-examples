package com.java.examples;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOHeader;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.header.BASE1Header;
import org.jpos.iso.header.BaseHeader;
import org.jpos.iso.packager.GenericPackager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by miztli on 3/03/17.
 */
public class Message0800Test {
    private static GenericPackager PACKAGER;

    public static void main(String[] args) throws IOException, ISOException {

        File file = new File(Configuration.CONFIG_FILE);

        // Create Packager based on XML that contain DE type
//        GenericPackager packager = new GenericPackager("basic.xml");
        PACKAGER = new GenericPackager(new FileInputStream(file));

        ISOMsg isoMsg = createIsoMessage0800();

        readIsoMessage0810(new String(isoMsg.pack()));



    }

    private static ISOMsg createIsoMessage0800() throws ISOException {

        // Create ISO Message
        ISOMsg isoMsg = new ISOMsg();
        isoMsg.setPackager(PACKAGER);
        isoMsg.setMTI("0800");
        isoMsg.set(7, "0303141800");
        isoMsg.set(11, "1");
        isoMsg.set(70, "001");

        // print the DE list
        Utils.logISOMsg(isoMsg);

        // Get and print the output result
        byte[] data = isoMsg.pack();
        System.out.println("RESULT : " + new String(data));

        return isoMsg;
    }
    private static void readIsoMessage0810(String data) throws ISOException {
        // Create ISO Message
        ISOMsg isoMsg = new ISOMsg();
        isoMsg.setPackager(PACKAGER);
        isoMsg.unpack(data.getBytes());

        // print the DE list
        Utils.logISOMsg(isoMsg);
    }

}
