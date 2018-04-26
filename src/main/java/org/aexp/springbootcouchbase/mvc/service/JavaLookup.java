package org.aexp.springbootcouchbase.mvc.service;


import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;

/**
 * An "nslookup" clone in Java.
 *
 * @author Elliot Rusty Harold, O'Reilly & Associates
 */
public class JavaLookup {

    public static void main(String args[]) {

        long a=System.currentTimeMillis();
     lookup("139.71.208.31");
        System.out.println(System.currentTimeMillis() - a);

    } /* end main */

    public static void lookup(String s) {

        InetAddress thisComputer;
        byte[] address;

        // get the bytes of the IP address
        try {
            thisComputer = InetAddress.getByName(s);
            address = thisComputer.getAddress();
        } catch (UnknownHostException ue) {
            System.out.println("Cannot find host " + s);
            return;
        }

        if (isHostname(s)) {
            // Print the IP address
            for (int i = 0; i < address.length; i++) {
                int unsignedByte = address[i] < 0 ? address[i] + 256
                        : address[i];
                System.out.print(unsignedByte + ".");
            }
            System.out.println("hostname printed");
        } else { // this is an IP address
            try {
                System.out.println("by name : "+InetAddress.getByName(s));
                System.out.println("by canonical : "+InetAddress.getByName(s).getCanonicalHostName());
            } catch (UnknownHostException e) {
                System.out.println("Could not lookup the address " + s);
            }
        }

    } // end lookup

    private static boolean isHostname(String s) {

        char[] ca = s.toCharArray();
        // if we see a character that is neither a digit nor a period
        // then s is probably a hostname
        for (int i = 0; i < ca.length; i++) {
            if (!Character.isDigit(ca[i])) {
                if (ca[i] != '.') {
                    return true;
                }
            }
        }

        // Everything was either a digit or a period
        // so s looks like an IP address in dotted quad format
        return false;

    } // end isHostName

}
