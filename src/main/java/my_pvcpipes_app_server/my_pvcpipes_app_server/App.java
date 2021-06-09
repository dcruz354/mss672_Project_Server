package my_pvcpipes_app_server.my_pvcpipes_app_server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Logger logger = LogManager.getLogger();
    	logger.info("Inside App server");
        ServerSocket server = null;
        Socket socket = null;
        ObjectInputStream in = null;
        ObjectOutputStream out = null;
        boolean exit = false;
        try {
     	   server = new ServerSocket(8000, 100);
     	   while(!exit) {
     		   socket = server.accept();
      		   logger.info("Connection is created. Port number: 8000");
     		   out = new ObjectOutputStream(socket.getOutputStream());
     		   in = new ObjectInputStream(socket.getInputStream());
     		   
     		   String inputStr = (String)in.readObject();
     		   
     		   if(! inputStr.equals("exit")) {
     			   out.writeObject("Hello " + inputStr);
     		   } else
     		   {
     			   exit = true;
     			   out.writeObject("Exiting");
     		   }
     		   
     		   in.close();
     		   out.close();
     		   socket.close();
     	   }
        } catch (Exception e) {
     	   e.printStackTrace();
        } finally {
     	   try {
 				server.close();
 				logger.info("Connection is closed.");
 			} catch (IOException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
        }
    }
}
