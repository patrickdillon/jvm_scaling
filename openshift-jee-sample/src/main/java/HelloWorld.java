// Import required java libraries
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.servlet.*;
import javax.servlet.http.*;

// Extend HttpServlet class
public class HelloWorld extends HttpServlet {
 
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
  {
      // Set response content type
      response.setContentType("text/html");

      // Actual logic goes here.
      PrintWriter out = response.getWriter();
      String queryString = request.getQueryString();
      int id = Integer.parseInt(((queryString.split("&")[0]).split("=")[1]));
      int memory = Integer.parseInt(((queryString.split("&")[1]).split("=")[1]));
      int sleep = Integer.parseInt(((queryString.split("&")[2]).split("=")[1]));
      int gc = Integer.parseInt(((queryString.split("&")[3]).split("=")[1]));
      int cpu = Integer.parseInt(((queryString.split("&")[4]).split("=")[1]));

      
      StringBuilder sb = new StringBuilder();
      int totalSize = 0;
      List myobjects = new ArrayList();
      for (int i = 0; i < memory; i++) {
    	  double randomNumber = Math.random() * 100;
    	  double logRandom = Math.log10(randomNumber);
    	  double scaleFactor = (2 - logRandom);
    	  int byteSize = (int) (500000 * scaleFactor);
    	  byte[] someOtherMemory = new byte[byteSize];
    	  Arrays.fill( someOtherMemory, new Byte((byte) Math.ceil(Math.random()*8)));
    	  myobjects.add(someOtherMemory);
    	  sb.append(byteSize).append(" ");
    	  totalSize += byteSize;
      }


      if (sleep > 0) {
	      	try {
	  			TimeUnit.SECONDS.sleep(sleep);
	  		} catch (InterruptedException e) {
	  			// TODO Auto-generated catch block
	  			e.printStackTrace();
	  		}
      }
     
      int result = 100;
      if (cpu > 0) {
	      int iters = 6500000;
		  Random rand = new Random();
		  
		  for (int i = 1; i < iters; i++) {
			  if (i % 2 == 0) result += rand.nextInt();
			  else result -= rand.nextInt();
		  }
      }
	  
      if (gc > 0) {
    	  System.out.println("Thread " + id + "is trying to call GC.");
    	  System.gc();
      }

      //This print statement prevents the compiler from optimizing out the actual memory usage
      System.out.println("Exiting thread " + id + " MEM: " + memory + "array contents: " + myobjects.get(0) + " kinda random number: " + result);
  }

  public void destroy()
  {
      // do nothing.
  }
}
