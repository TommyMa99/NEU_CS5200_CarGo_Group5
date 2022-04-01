package car.servlet;



import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.dal.CarDao;
import car.model.Cars;

/**   
 * The servlet for delete a car info
 * 
 * @author: Bingfan Tian  
 * @date: 2022.04.01 
 */
@WebServlet("/delete")
public class DeleteCar extends HttpServlet {

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
        String resultVin = req.getParameter("vin");
        if (resultVin == null || resultVin.trim().isEmpty()) {
            messages.put("success", "Invalid Vin number");
        }else {
        	Cars cur_car = new Cars(resultVin);
	        try {
	        	cur_car = carDao.delete(cur_car);
	        	// Update the message.
		        if (cur_car == null) {
		            messages.put("success", "Successfully deleted car with Vin: " + resultVin);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("success", "Failed to delete car with Vin: " + resultVin);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }     
        req.getRequestDispatcher("/search/search.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        String resultVin = req.getParameter("vin");
        if (resultVin == null || resultVin.trim().isEmpty()) {
            messages.put("success", "Invalid Vin number");
        }else {
        	Cars cur_car = new Cars(resultVin);
	        try {
	        	cur_car = carDao.delete(cur_car);
	        	// Update the message.
		        if (cur_car == null) {
		            messages.put("success", "Successfully deleted car with Vin: " + resultVin);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("success", "Failed to delete car with Vin: " + resultVin);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/search/search.jsp").forward(req, resp);
    }
}
