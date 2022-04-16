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
	
}
