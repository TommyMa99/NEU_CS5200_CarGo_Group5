package car.servlet;

import car.dal.*;
import car.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * FindUsers is the primary entry point into the application.
 * 
 * Note the logic for doGet() and doPost() are almost identical. However, there is a difference:
 * doGet() handles the http GET request. This method is called when you put in the /findusers
 * URL in the browser.
 * doPost() handles the http POST request. This method is called after you click the submit button.
 * 
 * To run:
 * 1. Run the SQL script to recreate your database schema: http://goo.gl/86a11H.
 * 2. Insert test data. You can do this by running blog.tools.Inserter (right click,
 *    Run As > JavaApplication.
 *    Notice that this is similar to Runner.java in our JDBC example.
 * 3. Run the Tomcat server at localhost.
 * 4. Point your browser to http://localhost:8080/Cargo/new.
 */
@WebServlet("/new")
public class NewCar extends HttpServlet {
	
	protected CarDao carDao;
	
	@Override
	public void init() throws ServletException {
		carDao = CarDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/new/new.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
//        String userName = req.getParameter("username");
        String resultVin = req.getParameter("vin");
		
        if (resultVin == null || resultVin.trim().isEmpty()) {
            messages.put("success", "Invalid Vin number");
        } else {
        	int resultYear = Integer.valueOf(req.getParameter("year"));
    		String resultMake = req.getParameter("make");
    		String resultModel = req.getParameter("model");
    		String resultTrim = req.getParameter("trim");
    		String resultBody = req.getParameter("body");
    		String resultTransmission = req.getParameter("transmission");
    		String resultState = req.getParameter("state");
    		int resultOdometer = Integer.valueOf(req.getParameter("odometer"));
    		double resultCarCondition = Double.valueOf(req.getParameter("carCondition"));
    		String resultColor = req.getParameter("color");
    		String resultInterior = req.getParameter("interior");
    		int resultMmr = Integer.valueOf(req.getParameter("mmr"));
    		int resultSellingPrice = Integer.valueOf(req.getParameter("sellingPrice"));
    		int resultUserId = Integer.valueOf(req.getParameter("userId"));
    		Sellers seller = null;
			try {
				seller = SellerDao.getInstance().getSellerByUserId(resultUserId);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				Cars car = new Cars(resultVin, resultYear, resultMake, resultModel, resultTrim, 
						resultBody, resultTransmission, resultState, resultOdometer, 
						resultCarCondition, resultColor, resultInterior, resultMmr, 
						resultSellingPrice, seller);
				car = carDao.create(car);
				messages.put("success", "Successfully created new car with vin: " + resultVin);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
        }
        req.getRequestDispatcher("/new/new.jsp").forward(req, resp);
    }
	
}
