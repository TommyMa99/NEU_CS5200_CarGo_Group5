package car.model;
/**
 * This class is used for representing saves of users.
 * @author Jianqing Ma, Sen Yan, Bo Chen, Bingfan Tian, Qihui Liu, Kailun He
 *
 */
public class Saves{
	protected int saveId;
	protected String vin;
	protected int userId;
	//this constructor is used for reading records
	public Saves(int saveId, String vin, int userId) {
		super();
		this.saveId = saveId;
		this.vin = vin;
		this.userId = userId;
	}
	//this constructor is used for reference
	public Saves(int saveId) {
		super();
		this.saveId = saveId;
	}

	//this constructor is used for creating records
	public Saves(String vin, int userId) {
		super();
		this.vin = vin;
		this.userId = userId;
	}
	public int getSaveId() {
		return saveId;
	}
	public void setSaveId(int saveId) {
		this.saveId = saveId;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}