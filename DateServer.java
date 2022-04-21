import java.io.*;
import java.net.*;
import java.util.Scanner;

public class DateServer {

  public static void main(String[] args) {
    try {
      ServerSocket sock = new ServerSocket((0));
      //   printing socket address and port
      System.out.println("Server listening on port " + sock.getLocalPort());

      Scanner input = new Scanner(System.in);

      String choice = "";

      while (!choice.equals("q")) {
        input = new Scanner(System.in);
        choice = input.next();

        if (choice.equals("q")) {
          input.close();
          System.exit(0);
        }

        if (!input.equals("q")) {
          /* Quit application if q is entered */
          Socket client = sock.accept();
          PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
          /* write the Date to the socket */
          pout.println(new java.util.Date().toString());
          choice = input.next();
          /* close the socket and resume */
          client.close();

          sock.close();
        }
      }
      /* now listen for connections */

    } catch (IOException ioe) {
      System.err.println(ioe);
    }
  }
}
