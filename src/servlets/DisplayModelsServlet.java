package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.MyException;
import models.Automobile;
import adapter.BuildAuto;

/**
 * Servlet implementation class DisplayModelsServlet
 */
@WebServlet("/DisplayModelsServlet")
public class DisplayModelsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletConfig config;
	private ServletContext application;
	protected BuildAuto ba;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayModelsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Initialized servlet
     */
    public void init() {
    	config = getServletConfig();
		application = config.getServletContext();
		application.log("Servlet is on!");
		try {
			ba = new BuildAuto();
		} catch (MyException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Main servlet function
     */
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	PrintWriter out  = response.getWriter();
    	// Iterate through HashMap and display all keys
    	try {
    		ba.createAuto();
    		LinkedHashMap<String, Automobile> cars = ba.getAutoCar();
    		String[] models = new String[cars.size()];
    		Iterator<String> it = cars.keySet().iterator();
    		int i=0;
    		while(it.hasNext()) {
    			models[i++] = it.next();
    		}
    		request.setAttribute("models", models);
    		request.getRequestDispatcher("/WEB-INF/displayModels.jsp").forward(request, response);

    		
    	} catch(Exception e) {
    		application.log("Error found!!!---"+e);
    	}
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		application.log("--------- Received request!!");
		init();
		service(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		application.log("--------- Received request!!");
		init();
		application.log("--------- Received request!!");
		service(request, response);
	}

}
