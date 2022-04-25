package car.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.dal.CarDao;
import car.dal.SellerDao;
import car.model.Cars;
import car.model.Sellers;


/**   
 * @author: Bingfan Tian  
 * @date: 2022.04.01 
 */
@WebServlet("/detail")
public class CarDetailTest extends HttpServlet {
	
	protected CarDao carDao;
	
	@Override
	public void init() throws ServletException {
		carDao = CarDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
	    req.setAttribute("messages", messages);
	    String resultVin = req.getParameter("vin");
        if (resultVin == null || resultVin.trim().isEmpty()) {
            messages.put("success", "Invalid Vin number");
        } else {
        	try {
				Cars curCar = carDao.getCarByVin(resultVin);
				if(curCar == null) {
	        		messages.put("success", "Vin does not exist. No update to perform.");
	        	}
				req.setAttribute("car", curCar);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}
        req.getRequestDispatcher("/cardetail/cardetail.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        String message = req.getParameter("content");
        if(message == null || message.trim().isEmpty()) {
        	messages.put("success", "Please enter a valid message.");
        } else {
        	// Waiting for Message DAO
        }
        
//        List<Cars> cars = new ArrayList<Cars>();
// 
//        String year = req.getParameter("year");
//    	String make = req.getParameter("make");
//    	String model = req.getParameter("model");
//    	String state = req.getParameter("state");
//    	if (year == null || year.trim().isEmpty() ||
//        		make == null || make.trim().isEmpty() ||
//        		model == null || model.trim().isEmpty() ||
//        		state == null || state.trim().isEmpty()) {
//            messages.put("success", "Please enter a valid parameter.");
//        } else {
//        	try {
//        		cars = carDao.getCarByParameters(Integer.valueOf(year), make, model, state);
//            } catch (SQLException e) {
//    			e.printStackTrace();
//    			throw new IOException(e);
//            }
//        	messages.put("success", "Displaying results for " + year + " " + make + " " + model + " in " + state);
//   
//        }
    	
        req.getRequestDispatcher("/search/search.jsp").forward(req, resp);
    }
	
}
