package test;

import java.io.*;
import java.net.*;

public class CmdServer {

  public static void main(String[] args) {
    try {
      ServerSocket sock;
      int port = 5000;

      while (port <= 5500) {
        try {
          sock = new ServerSocket(port);
          InetAddress ia = InetAddress.getByName(null);
          String hostname = (ia.toString().split("/")[0]);
          System.out.println(
            hostname + " is listening on port " + sock.getLocalPort()
          );
          while (true) {
            Socket client = sock.accept();
            PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
            InputStream in = client.getInputStream();
            BufferedReader bin = new BufferedReader(new InputStreamReader(in));
            /* read the date from the socket */
            String line;
            String username = System.getProperty("user.name");

            boolean isAuthenticated = false;

            while ((line = bin.readLine()) != null) {
              System.out.println(line);
              if (!isAuthenticated) {
                if (line.equals("username")) {
                  isAuthenticated = true;
                  pout.println("handshake_complete");
                } else {
                  ServerSocket unauthorisedSock = new ServerSocket((0));
                  pout.println(unauthorisedSock.getLocalPort());
                  System.out.println(
                    hostname +
                    " listening on port " +
                    unauthorisedSock.getLocalPort()
                  );
                  while (true) {
                    Socket unauthClient = unauthorisedSock.accept();
                    PrintWriter writer = new PrintWriter(
                      unauthClient.getOutputStream(),
                      true
                    );
                    InputStream in2 = unauthClient.getInputStream();
                    BufferedReader bin2 = new BufferedReader(
                      new InputStreamReader(in2)
                    );
                    String line2;
                    while ((line2 = bin2.readLine()) != null) {
                      System.out.println(line2);
                    }
                  }
                }
              } else {
                pout.println(reverseString(line));
              }
            }
          }
        } catch (IOException ioe) {
          System.err.println(ioe);
          port++;
        }
      }
      //   printing socket address and port

      /* now listen for connections */
      //
    } catch (Exception e) {
      System.err.println(e);
    }
  }

  public static String reverseString(String msg) {
    String newString = "";
    char ch;

    for (int i = 0; i < msg.length(); i++) {
      ch = msg.charAt(i);
      newString = ch + newString;
    }

    return newString;
  }
}
