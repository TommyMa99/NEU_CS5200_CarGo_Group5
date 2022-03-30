package car.model;

import java.util.Date;
/**
 * This class is used for representing reviews.
 * @author Jianqing Ma, Sen Yan, Bo Chen, Bingfan Tian, Qihui Liu, Kailun He
 *
 */
public class Reviews{
	protected int reviewId;
	protected Date date;
	protected String reviewContent;
	protected double rating;
	protected int buyerId;
	protected int sellerId;
	
	//this constructor is used for reading records
	public Reviews(int reviewId, Date date, String reviewContent, double rating, int buyerId, int sellerId) {
		super();
		this.reviewId = reviewId;
		this.date = date;
		this.reviewContent = reviewContent;
		this.rating = rating;
		this.buyerId = buyerId;
		this.sellerId = sellerId;
	}
	
	
	//this is used for reference
	public Reviews(int reviewId) {
		super();
		this.reviewId = reviewId;
	}



	//this constructor is used for creating records
	public Reviews(Date date, String reviewContent, double rating, int buyerId, int sellerId) {
		super();
		this.date = date;
		this.reviewContent = reviewContent;
		this.rating = rating;
		this.buyerId = buyerId;
		this.sellerId = sellerId;
	}


	public int getReviewId() {
		return reviewId;
	}


	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getReviewContent() {
		return reviewContent;
	}


	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}


	public double getRating() {
		return rating;
	}


	public void setRating(double rating) {
		this.rating = rating;
	}


	public int getBuyerId() {
		return buyerId;
	}


	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}


	public int getSellerId() {
		return sellerId;
	}


	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	
	
	
}