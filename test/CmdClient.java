package test;

import java.io.*;
import java.net.*;

public class CmdClient {

  public static void main(String[] args) {
    try {
      /* Obtain pport variable from the file args */
      int port = Integer.parseInt(args[0]);

      /* Validate port range */
      if (port >= 5000 && port <= 5500) {
        try {
          /* Set hostname from arguments or setting default */
          String host = args.length == 2 ? args[1] : "localhost";
          /* make connection to server socket */
          Socket sock = new Socket(host, port);

          PrintWriter pout = new PrintWriter(sock.getOutputStream(), true);

          String username = System.getProperty("user.name");
          pout.println(username);

          // String message = System.console().readLine();

          // pout.println(message);

          InputStream in = sock.getInputStream();
          BufferedReader bin = new BufferedReader(new InputStreamReader(in));
          String line;
          boolean isHandshake = false;
          while ((line = bin.readLine()) != null) {
            System.out.println(line);
            if (isHandshake) {
              System.out.println(line);
            } else {
              if (line.equals("handshake_complete")) {
                isHandshake = true;
              } else {
                Socket s2 = new Socket(host, Integer.parseInt(line));
                PrintWriter writer = new PrintWriter(
                  s2.getOutputStream(),
                  true
                );
                InputStream in2 = s2.getInputStream();
                BufferedReader reader = new BufferedReader(
                  new InputStreamReader(in2)
                );
                String line2;
                writer.println("sock.getPort()");
                while ((line2 = reader.readLine()) != null) {
                  System.out.println(line2);
                  
                }
                s2.close();
              }
            }
            //
          }
          sock.close();
        } catch (Exception ioe) {
          System.err.println(ioe);
        }
      }
    } catch (Exception e) {
      System.err.println(e);
    }
  }
}
