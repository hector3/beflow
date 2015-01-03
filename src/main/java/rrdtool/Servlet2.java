package rrdtool;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class Servlet2
 */
@WebServlet(description = "servlet para rrdtool", urlPatterns = { "/Servlet2" })
public class Servlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet2() {
    	
        super();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @return 
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("hola lidia");
		Thread thread = new Thread(new RRDThread());
		thread.run();  
		
	
		   
	}

}
