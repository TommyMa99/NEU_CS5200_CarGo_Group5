package car.servlet;

import car.dal.*;
import car.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
 * 4. Point your browser to http://localhost:8080/BlogApplication/findusers.
 */
@WebServlet("/search")
public class SearchTest extends HttpServlet {
	
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
        List<Cars> cars = new ArrayList<Cars>();
        
        // Retrieve and validate name.
        // firstname is retrieved from the URL query string.
        String year = req.getParameter("year");
    	String make = req.getParameter("make");
    	String model = req.getParameter("model");
    	String state = req.getParameter("state");
        if (year == null || year.trim().isEmpty() ||
        		make == null || make.trim().isEmpty() ||
        		model == null || model.trim().isEmpty() ||
        		state == null || state.trim().isEmpty()) {
            messages.put("success", "Please enter a valid parameter.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
        		cars = carDao.getCarByParameters(Integer.valueOf(year), make, model, state);
        	} catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	// hard code some test case since DAO is not ready
//        	Cars car1 = new Cars("19uua8f20ba003355", 2011, "Acura", "TL", "Base", "Sedan", "automatic", "nj", 57880, 3.7, "silver", "gray", 16000, 16000, null);
//    		Cars car2 = new Cars("19uua8f20da015332", 2013, "Acura", "TL", "Base", "Sedan", "automatic", "nv", 25681, 3.8, "white", "black", 21500, 22250, null);
//    		Cars car3 = new Cars("19xfb2f51ee233919", 2014, "Honda", "Civic", "LX", "Sedan", "automatic", "ca", 10424, 4.3, "white", "gray", 14150, 15900, null);
//    		cars.add(car1);
//    		cars.add(car2);
//    		cars.add(car3);
        	messages.put("success", "Displaying results for " + year + " " + make + " " + model + " in " + state);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previousYear", year);
        	messages.put("previousMake", make);
        	messages.put("previousModel", model);
        	messages.put("previousState", state);
        	
        }
        req.setAttribute("cars", cars);

        req.getRequestDispatcher("/search/search.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        List<Cars> cars = new ArrayList<Cars>();
        
        // Retrieve and validate name.
        // firstname is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindUsers.jsp).
        String year = req.getParameter("year");
    	String make = req.getParameter("make");
    	String model = req.getParameter("model");
    	String state = req.getParameter("state");
    	if (year == null || year.trim().isEmpty() ||
        		make == null || make.trim().isEmpty() ||
        		model == null || model.trim().isEmpty() ||
        		state == null || state.trim().isEmpty()) {
            messages.put("success", "Please enter a valid parameter.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
        		cars = carDao.getCarByParameters(Integer.valueOf(year), make, model, state);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + year + " " + make + " " + model + " in " + state);
        	
        	// hard code some test case since DAO is not ready
//        	Cars car1 = new Cars("19uua8f20ba003355", 2011, "Acura", "TL", "Base", "Sedan", "automatic", "nj", 57880, 3.7, "silver", "gray", 16000, 16000, null);
//    		Cars car2 = new Cars("19uua8f20da015332", 2013, "Acura", "TL", "Base", "Sedan", "automatic", "nv", 25681, 3.8, "white", "black", 21500, 22250, null);
//    		Cars car3 = new Cars("19xfb2f51ee233919", 2014, "Honda", "Civic", "LX", "Sedan", "automatic", "ca", 10424, 4.3, "white", "gray", 14150, 15900, null);
//    		cars.add(car1);
//    		cars.add(car2);
//    		cars.add(car3);
        }
    	req.setAttribute("cars", cars);
        
        req.getRequestDispatcher("/FindUsers.jsp").forward(req, resp);
    }
}
