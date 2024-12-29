// example 5.1: NETADDRESS1.JAVA PROGRAM
import java.net.InetAddress;

class NetAdress1 {
  public static void main(String args[]) trhows Exception {
    InetAddress inetAddress = InetAdress.getLocalHost();
    System.out.println("IP Address: " + inetAddress.getHostAddress());
    System.out.println("Host Name: " + inetAddress.getHostName());
  }
}

// example 5.2: NETINFO.JAVA PROGRAM
import java.net.*;

class NetInfo {
  public static void main(String args[]) throws Exception {
    // get IP address
    InetAddress ip;
    ip = InetAddress.getLocalHost();
    System.out.println("IP address: " + ip.getHostAddress());
    NetworkInterface network = NetworkInterface.getByInetAddress(ip);
    // get subnet mask
    InetAddress localHost = Inet4Address.getLocalHost();
    NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localHost);
    System.out.println("Subnet Mask: " + networkInterface.getInterfaceAddresses().get(0).getNetworkPrefixLength());
    // get MAC address
    byte[] mac = network.getHardwareAddress();
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < mac.length; i++) {
      sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
    }
    System.out.println("MAC address: " + sb.toString());
  }
}

// example 5.3: NETADDRESS.JAVA PROGRAM
import java.net.*;
import java.util.*;

public class NetAddress2
  {
    public static void main(String[] args)
    {
      try
        {
          System.out.println("getLocalHost: " + InetAddress.getLocalHost().toString());
          System.out.println("all addresses for local host: ");
          InetAddress[] addr = InetAddress.getAllByName(InetAddress.getLocalHost().getHostName());
          for(InetAddress a : addr)
            {
              System.out.println(a.toString());
            }
        }
      catch(UnknownHostException _e)
      {
          _e.printStackTrace();
      }
      try
        {
          Enumeration<NetworkInterface> nicEnum = NetworkInterface.getNetworkInterfaces();
          while(nicEnum.hasMoreElements())
            {
              NetworkInterface ni=nicEnum.nextElement();
              System.out.println("Name:" + ni.getDisplayName());
              System.out.println("Name:" + ni.getName());
              Enumeration<InetAddress>addrenum = ni.getInetAddresses();
              while(addrEnum.hasMoreElements())
                {
                  InetAddress ia = addrenum.nextElement();
                  System.out.println("Name:" + ni.getDisplayName());
                  System.out.println("Name:" + ni.getName());
                  Enumeration<InetAddress>addrenum = ni.getInetAddresses();
                  while(addrEnum.hasMoreElements())
                    {
                      InetAddress ia = addrEnum.nextElement();
                      System.out.println(ia.getHostAddress());
                    }
                }
            }
            catch(SocketException _e)
        {
          _e.printStackTrace();
        }
    }
  }

// ex.5.5: the IPCONFIG2.JAVA Program

import java.util.*;
import java.util.regex.*;
import java.io.*;

public class IPConfig2 {
  public static void main(String args[]) {
    try {
      String line;
      String[] cmd = {"cmd.exe", "/c", "ipconfig / all"};
      Process p = Runtime.getRuntime().exec(cmd);

      BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
      Pattern pattern = Pattern.compile("DHCP Server");
      Matcher matcher = pattern.matcher("");
      while((line = input.readLine()) != null) {
        matcher.reset(line);
        if(matcher.find()) {
          System.out.println(line);
        }
      }
      input.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

// ex. 5.6: the UDPSERVER.JAVA PROGRAM
import java.net.*;
class UDPServer1 {
  public static void main(String argv[]) throws Exception {
    byte[] buf = new byte[256];
    String strData;
    int PORT = 3301;
    DatagramSocket serverSocket = new DatagramSocket(PORT);
    DatagramPacket packet = new DatagramPacket(buf, buf.length);

    serverSocket.receive(packet);
    strData = new String(packet.getData());
    InetAddress IPAddress = packet.getAddress();
    PORT = packet.getPort();
    System.out.println("datagram received:" + IPAddress + ":" + PORT);
    buf = strData.getBytes();
    packet = new DatagramPacket(buf, buf.length, IPAddress, PORT);
    serverSocket.send(packet);
    serverSocket.close();
  }
}

// ex. 5.6b: the UDPCLIENT1.JAVA PROGRAM
import java.io.*;
import java.net.*;
class UDPClient1 {
  public static void main(String argv[]) throws Exception {
    int PORT = 3301;
    String HOST="localhost";
    String strData;

    System.out.println("please enter your text:");
    BufferedReader inputLine = new BufferedReader(new InputStreamReader(System.in));

    DatagramSocket clientSocket = new DatagramSocket();
    InetAddress IPAddress = InetAddress.getByName(HOST);
    byte[] buf = new byte[256];
    strData = inputLine.readLine();
    buf = strData.getBytes();

    DatagramPacket packet = new DatagramPacket(buf, buf.length, IPAddress, PORT);
    clientSocket.send(packet);

    packet = new DatagramPacket(buf, buf.length);
    clientSocket.receive(packet);
    strData = new String(packet.getData());
    System.out.println("FROM SERVER: " = strData.toUpperCase());
    clientSocket.close();
  }
}

// ex. 5.7a: the TCPSERVER.JAVA PROGRAM
import java.io.*;
import java.net.*;

class TCPServer1 {
  public static void main(String argv[]) throws Exception {
    ServerSocket serverSocket = null;
    Socket clientSocket = null;
    // can use any other port numbers if 3301 is not available
    int PORT = 3301;
    serverSocket = new ServerSocket(PORT);
    clientSocket = serverSocket.accept();
    System.out.println("Connected from: " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());
    BufferedReader inputLine = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    DataOutputStream outputLine = new DataOutputStream(clientSocket.getOutputStream());
    outputLine.writeBytes(inputLine.readLine());
    clientSocket.close();
    serverSocket.close();
  }
}

// ex. 5.7b: the TCPCLIENT1.JAVA PROGRAM
import java.io.*;
import java.net.*;
class TCPClient1 {
  public static void main(String argv[]) throws Exception {
    Socket clientSocket = null;
    int PORT = 3301;
    String HOST = "localhost";
    BufferedReader inputLine = new BufferedReader(new InputStreamReader(System.in));
    clientSocket = new Socket(HOST, PORT);
    System.out.println("Connected to: " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());
    DataOutputStream outputLine = new DataOutputStream(clientSocket.getOutputStream());
    BufferedReader replyLine = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    outputLine.writeBytes(inputLine.readLine() + '\n');
    System.out.println("FROM SERVER: " + replyLine.readLine().toUpperCase());
    clientSocket.close();
  }
}

// ex. 5.8a: a MULTITHREADED ECHO SERVER
import java.io.*;
import java.net.*;

public class EchoServer2 {
  public static void main(String[] args) {
    ServerSocket echoServer;
    int id=0;
    BufferedReader br;
    PrintWriter os;
    String line;

    ChatThread(Socket clientSocket, int id) {
      this.clientSocket=clientSocket;
      this.id=id;
    }
    public void run() {
      try{
        br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        os = new PrintWriter(clientSocket.getOutputStream(), true);
        while((line = br.readLine()) != null) {
          System.out.println(id + "-received: " + line);
          os.println(line);
        }
      }
      catch(IOException e) {
        System.out.println(e);
      }
      finally {
        try {
          br.close();
          os.close();
          clientSocket.close();
          System.out.println(id + "...stopped");
        }
        catch(Exception e) {
          e.printStackTrace();
        }
      }
      System.out.println(message);
    }
  }

// ex. 5.8B: THE ECHO CLIENT
import java.io.*;
import java.net.*;

public class EchoClient {
  public static void main(String[] args) {
    Socket echoSocket;
    PrintWriter out;
    BufferedReader in;
    try
      {
        echoSocket = new Socket("localhost", 9999);
        out = new PrintWriter(echoSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        while ((userInput = stdIn.readLine()) != null)
          {
            out.println(userInput);
            System.out.println("echo: " + in.readLine());
          }
        out.close();
        in.close();
        stdIn.close();
        echoSocket.close();
      }
    catch(Exception e)
      {
        System.out.println("Error: " + e.toString());
        System.exit(-1);
      }
  }
}

// ex. 5.9B: AN HTTPS CLIENT
import java.net.*;
import java.io.*;
import javax.net.ssl.HttpsURLConnection;

public class HTTPSClient {
  public static void main(String[] args) throws Exception {
    URL u = new URL("https://www.google.com/");
    HttpsURLConnection uc = (HttpsURLConnection) u.openConnection();
    BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
    String inputLine;
    while((inputLine = in.readLine()) != null)
      System.out.println(inputLine);
    in.close();
  }



      
