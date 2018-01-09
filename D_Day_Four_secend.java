package Project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Date;
import java.util.Calendar;

public class D_Day_Four_secend extends JFrame implements ActionListener {

	JPanel dDaySet = new JPanel(); // D-DaySet
	JPanel dDaySetLabel = new JPanel(); // D-Day Set 라벨
	JPanel dDayChoice = new JPanel(); // D-Day Choice
	JPanel dDayTitle = new JPanel(); // D-Day Title
	JPanel dDayInputPanel = new JPanel(); // D-Day Input
	JPanel dDayButtonPanel = new JPanel(); // D-Day Button(확인)
	
	Calendar today = Calendar.getInstance(); //현재 오늘 날짜
	
	Choice yearChoice;
	Choice monthChoice;
	Choice dateChoice;

	JTextArea dDayInputArea = new JTextArea(2, 30);

	public D_Day_Four_secend() {
		setTitle("D-Day 설정");
		setSize(400, 210);
	
		setResizable(false);//창 크기 변경

		JLabel dDaySetLabel2 = new JLabel("D-DAY날짜                ");

		dDaySetLabel.add(dDaySetLabel2);

		yearChoice = new Choice();
		for (int i = 1990; i <= 2050; i++) {
			String S = String.valueOf(i);
			yearChoice.add(S);
		}
		yearChoice.select(today.get(Calendar.YEAR)-1990);/*빼는 이유*/
		dDayChoice.add(yearChoice);

		JLabel dDayChoice1 = new JLabel("년"); // dDayChoice1.setText("년");
		dDayChoice.add(dDayChoice1);

		monthChoice = new Choice();
		for (int i = 1; i <= 12; i++) {
			String S = String.valueOf(i);
			monthChoice.add(S);
		}
		monthChoice.select(today.get(Calendar.MONTH));
		dDayChoice.add(monthChoice);

		JLabel dDayChoice2 = new JLabel("월");
		dDayChoice.add(dDayChoice2);

		dateChoice = new Choice();
		for (int i = 1; i <= 31; i++) {
			String S = String.valueOf(i);
			dateChoice.add(S);
		}
		dateChoice.select(today.get(Calendar.DATE)-1);
		dDayChoice.add(dateChoice);

		JLabel dDayChoice3 = new JLabel("일");
		dDayChoice.add(dDayChoice3);

		dDayTitle.setLayout(new GridLayout(1, 1));
		JLabel dDayTitle1 = new JLabel("D-DAY제목                ");
		dDayTitle.add(dDayTitle1);

		JLabel dDayTitle0 = new JLabel(" ");
		dDayTitle.add(dDayTitle0);
		JLabel dDayTitle01 = new JLabel(" ");
		dDayTitle.add(dDayTitle01);

		dDayInputPanel.add(dDayInputArea);

		JButton CB = new JButton("확인");
		dDayButtonPanel.add("South", CB);

		CB.addActionListener(this);/**/

		dDaySet.add(dDaySetLabel);
		dDaySet.add(dDayChoice);
		dDaySet.add(dDayTitle);
		dDaySet.add(dDayInputPanel);
		dDaySet.add("South", dDayButtonPanel);

		add(dDaySet);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("확인")) {
			int year=Integer.parseInt(yearChoice.getSelectedItem());
			int month=Integer.parseInt(monthChoice.getSelectedItem());;
			int day=Integer.parseInt(dateChoice.getSelectedItem());;
			String comment=dDayInputArea.getText();
			
			FileReadAndWrite fileManage=new FileReadAndWrite();
			fileManage.addDday(year,month,day, comment);
			
			setVisible(false);
			new D_Day_Four();
		}

	}
}
