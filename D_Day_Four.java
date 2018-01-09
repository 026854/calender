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
	JButton delete = new JButton("�����ϱ�");
	JLabel outlabel = new JLabel();
	FileReadAndWrite fileManage = new FileReadAndWrite();
	ArrayList<D_DayData> dDay;
	String column[] = { "��¥", "Comment", "D-Day" };
	DefaultTableModel dm;
	JTable table;
	JScrollPane js;
	int remain;
	Object temp[][];
	
	// Method : D-Day ���
    // Return Value : int
    // Parameter : myear(��), mmonth(��), mday(��¥)
    // Use : ���� ��¥���� D-day�� ������ ��¥�� ���־� ��ŭ ��¥�� ���Ҵ����� ����Ͽ� ��ȯ�Ѵ�.
	public int caldate(int myear, int mmonth, int mday) {
		try {
			Calendar today = new GregorianCalendar(Locale.KOREA);/**/
			Calendar dday = Calendar.getInstance();
			
			today.set(Calendar.MONTH, today.get(Calendar.MONTH)+1);/**/
			//System.out.println(today.get(Calendar.MONTH)+"."+today.get(Calendar.DATE));
			dday.set(myear, mmonth, mday);// D-day�� ��¥�� �Է�

			long day = dday.getTimeInMillis() / 86400000; // ( 1���� ��(86400000 =
															// 24�ð� * 60�� * 60��
															// * 1000(1�ʰ�) ) )
			long tday = today.getTimeInMillis() / 86400000;
			long count = tday - day; // ���� ��¥���� dday ��¥�� ���ش�.

			return (int) count;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	// Method : Table�ȿ� �� ������ ����
    // Return Value : void
    // Parameter : void
    // Use : Table�� ����ϱ� ���ؼ��� Object[][] ������ data�� �־���ϱ� ������ File�� �о�� ���ϴ� ���·� �����͸� �����Ͽ� �����صд�.
	private void setData(){
		temp = new Object[dDay.size()][3];
		for (int i = 0; i < dDay.size(); i++) {
			D_DayData t = dDay.get(i);
			temp[i][0] = t.getYear() + "�� " + t.getMonth() + "�� " + t.getDate() + "��";
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

	// Method : Table�ȿ� �� ������ ����
    // Return Value : void
    // Parameter : void
    // Use : D-Day ������ �о���� ������ ����ִٸ� TableModel�� ������ �� ���� ������ �� ���·� �д�. �߰� �� �ٽ� ���ƿ��� ���� ����ȴ�.
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
