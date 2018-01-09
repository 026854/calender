package Project;

import java.io.Serializable;
import java.sql.Date;

public class D_DayData implements Serializable{
	
	private int year;
	private int month;
	private int date;
	private String comment;
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public D_DayData(int year, int month, int date, String comment) {
		super();
		this.year = year;
		this.month = month;
		this.date = date;
		this.comment = comment;
	}
	
	
	

}
