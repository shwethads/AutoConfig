package servlets;

import java.io.IOException;
import java.util.LinkedHashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Automobile;
import adapter.BuildAuto;
import util.Util;

/**
 * Servlet implementation class ProcessModelServlet
 */
@WebServlet("/ProcessModelServlet")
public class ProcessModelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected String model;
	private ServletConfig config;
	private ServletContext application;
	private Util util;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessModelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Initialized servlet
     */
    public void init() {
    	config = getServletConfig();
		application = config.getServletContext();
		util = new Util();
		model = "Model 1";
		application.log("Servlet started!");
    }
    
    /**
     * Main servlet method
     */
    public void service(HttpServletRequest request, HttpServletResponse response) {
    	application.log("--------------------in service!");
    	model = request.getParameter("models");
    	application.log("----ProcessModel: model:" +model);
    	try{
    		BuildAuto ba = new BuildAuto();
    		ba.createAuto();
    		Automobile car = ba.getCar(model);
    		request.setAttribute("model", model);
    		request.setAttribute("colors", util.getColorOptions(car));
    		request.setAttribute("transmissions", util.getTransmissionOptions(car));
    		request.setAttribute("breaks", util.getBreakOptions(car));
    		request.setAttribute("airBags", util.getAirBagOptions(car));
    		request.setAttribute("moonRoof", util.getMoonRoofOptions(car));
    		
    		request.getRequestDispatcher("/WEB-INF/displayOptions.jsp").forward(request, response);
    	}catch(Exception e) {
    		
    	}
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
