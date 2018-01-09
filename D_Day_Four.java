package Project;


import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;


import javax.swing.*;

import javax.swing.table.DefaultTableModel;

public class D_Day_Four extends JFrame {
	JPanel plusPanel = new JPanel();
	JButton plus = new JButton("+");
	JButton delete = new JButton("삭제하기");
	JLabel outlabel = new JLabel();
	FileReadAndWrite fileManage = new FileReadAndWrite();
	ArrayList<D_DayData> dDay;
	String column[] = { "날짜", "Comment", "D-Day" };
	DefaultTableModel dm;
	JTable table;
	JScrollPane js;
	int remain;
	Object temp[][];
	
	// Method : D-Day 계산
    // Return Value : int
    // Parameter : myear(년), mmonth(월), mday(날짜)
    // Use : 현재 날짜에서 D-day로 설정된 날짜를 빼주어 얼만큼 날짜가 남았는지를 계산하여 반환한다.
	public int caldate(int myear, int mmonth, int mday) {
		try {
			Calendar today = new GregorianCalendar(Locale.KOREA);/**/
			Calendar dday = Calendar.getInstance();
			
			today.set(Calendar.MONTH, today.get(Calendar.MONTH)+1);/**/
			//System.out.println(today.get(Calendar.MONTH)+"."+today.get(Calendar.DATE));
			dday.set(myear, mmonth, mday);// D-day의 날짜를 입력

			long day = dday.getTimeInMillis() / 86400000; // ( 1일의 값(86400000 =
															// 24시간 * 60분 * 60초
															// * 1000(1초값) ) )
			long tday = today.getTimeInMillis() / 86400000;
			long count = tday - day; // 오늘 날짜에서 dday 날짜를 빼준다.

			return (int) count;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	// Method : Table안에 들어갈 데이터 설정
    // Return Value : void
    // Parameter : void
    // Use : Table을 사용하기 위해서는 Object[][] 형태의 data가 있어야하기 때문에 File을 읽어와 원하는 형태로 데이터를 조작하여 저장해둔다.
	private void setData(){
		temp = new Object[dDay.size()][3];
		for (int i = 0; i < dDay.size(); i++) {
			D_DayData t = dDay.get(i);
			temp[i][0] = t.getYear() + "년 " + t.getMonth() + "월 " + t.getDate() + "일";
			temp[i][1] = dDay.get(i).getComment();
			remain = caldate(t.getYear(), t.getMonth(), t.getDate());

			if (remain > 0) {
				temp[i][2] = "D+" + remain;
			}else if(remain==0){
				temp[i][2] = "D-Day";
			}else {
				temp[i][2] = "D" + remain;
			}

		}
	}

	// Method : Table안에 들어갈 데이터 설정
    // Return Value : void
    // Parameter : void
    // Use : D-Day 파일을 읽어오고 파일이 비어있다면 TableModel을 설정할 수 없기 때문에 빈 형태로 둔다. 추가 후 다시 돌아오면 모델이 적용된다.
	private void getDdayData() {
		dDay = fileManage.dDayFileRead();
		if(dDay!=null){
		setData();
		dm=new DefaultTableModel(temp, column);
		table = new JTable(dm);
		}else{
			table = new JTable();
		}
		
	}

	public D_Day_Four() {
		plusPanel.add(plus);
		plusPanel.add(delete);
		plus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new D_Day_Four_secend();
				setVisible(false);
			}

		});
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1)/*-1*/
					return;
				else {
					fileManage.deleteDday(table.getSelectedRow());
					dDay = fileManage.dDayFileRead();
					setData();
					dm.setDataVector(temp, column);
				}
			}

		});
		
		getDdayData();

		js = new JScrollPane(table);

		setTitle("D-DAY");
		add("North", outlabel);
		add("Center", js);
		add("South", plusPanel);
		setSize(320, 350);
		setVisible(true);
		setResizable(false);

	}

}
