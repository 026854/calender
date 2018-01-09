package Project;

import java.io.Serializable;

public class ToDoData implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int day;
	private String comment;
	private int temper;
	private String diary;
	
	
	
	public ToDoData() {
		super();
	}



	public int getDay() {
		return day;
	}



	public void setDay(int day) {
		this.day = day;
	}



	public String getComment() {
		return comment;
	}



	public void setComment(String comment) {
		this.comment = comment;
	}



	public int getTemper() {
		return temper;
	}



	public void setTemper(int temper) {
		this.temper = temper;
	}



	public String getDiary() {
		return diary;
	}



	public void setDiary(String diary) {
		this.diary = diary;
	}



	public ToDoData(int day, String comment, int temper, String diary) {
		super();
		this.day = day;
		this.comment = comment;
		this.temper = temper;
		this.diary = diary;
	}

}
