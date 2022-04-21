import java.io.*;
import java.net.*;

public class DateClient {

  public static void main(String[] args) {
    try {
      /* Obtain pport variable from the file args */
      int port = Integer.parseInt(args[0]);

      /* make connection to server socket */
      Socket sock = new Socket("127.0.0.1", port);
      InputStream in = sock.getInputStream();
      BufferedReader bin = new BufferedReader(new InputStreamReader(in));

      /* read the date from the socket */
      String line;
      while ((line = bin.readLine()) != null) System.out.println(line);
      /* close the socket connection*/
      sock.close();
    } catch (IOException ioe) {
      System.err.println(ioe);
    }
  }
}
