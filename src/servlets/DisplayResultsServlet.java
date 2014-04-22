package servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Automobile;
import exceptions.MyException;
import adapter.BuildAuto;
import util.Util;

/**
 * Servlet implementation class DisplayResultsServlet
 */
@WebServlet("/DisplayResultsServlet")
public class DisplayResultsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String model, color, transmission, breaks, airBags, moonRoof;
	private ServletConfig config;
	private ServletContext application;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayResultsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Initializes servlet
     */
    public void init() {
    	config = getServletConfig();
		application = config.getServletContext();
		model = "Model 1";
		application.log("Servlet started!");
    }
    
    /**
     * Main servlet method
     */
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	model = request.getParameter("model");
    	application.log("-------------------results! "+model);
    	color = request.getParameter("color");
    	transmission = request.getParameter("trans");
    	application.log(transmission);
    	breaks = request.getParameter("breaks");
    	airBags = request.getParameter("ab");
    	moonRoof = request.getParameter("mr");
    	
    	try {
			BuildAuto ba = new BuildAuto();
			ba.createAuto();
			ba.setValues(model, color, transmission, breaks, airBags, moonRoof);
			Automobile car = ba.getCar(model);
			
			Util util = new Util();
			request.setAttribute("model", model);
			request.setAttribute("color", color);
			request.setAttribute("colorPrice", util.getPrice(car, "Color"));
			request.setAttribute("transmission", transmission);
			request.setAttribute("transCost", util.getPrice(car, "Transmission"));
			request.setAttribute("breaks", breaks);
			request.setAttribute("breaksCost", util.getPrice(car, "Brakes/Traction Control"));
			request.setAttribute("airBags", airBags);
			request.setAttribute("abCost", util.getPrice(car, "Side Impact Air Bags"));
			request.setAttribute("moonRoof", moonRoof);
			request.setAttribute("mrCost", util.getPrice(car, "Power Moonroof"));
			request.setAttribute("total", ba.getTotalPrice(car));
			
			request.getRequestDispatcher("/WEB-INF/displayResults.jsp").forward(request, response);
			
			application.log("-----Price for color = "+util.getPrice(car, "Power Moonroof"));
			application.log("---------Total cost: "+ba.getTotalPrice(car));
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
