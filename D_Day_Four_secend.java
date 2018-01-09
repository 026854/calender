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
	JPanel dDaySetLabel = new JPanel(); // D-Day Set ��
	JPanel dDayChoice = new JPanel(); // D-Day Choice
	JPanel dDayTitle = new JPanel(); // D-Day Title
	JPanel dDayInputPanel = new JPanel(); // D-Day Input
	JPanel dDayButtonPanel = new JPanel(); // D-Day Button(Ȯ��)
	
	Calendar today = Calendar.getInstance(); //���� ���� ��¥
	
	Choice yearChoice;
	Choice monthChoice;
	Choice dateChoice;

	JTextArea dDayInputArea = new JTextArea(2, 30);

	public D_Day_Four_secend() {
		setTitle("D-Day ����");
		setSize(400, 210);
	
		setResizable(false);//â ũ�� ����

		JLabel dDaySetLabel2 = new JLabel("D-DAY��¥                ");

		dDaySetLabel.add(dDaySetLabel2);

		yearChoice = new Choice();
		for (int i = 1990; i <= 2050; i++) {
			String S = String.valueOf(i);
			yearChoice.add(S);
		}
		yearChoice.select(today.get(Calendar.YEAR)-1990);/*���� ����*/
		dDayChoice.add(yearChoice);

		JLabel dDayChoice1 = new JLabel("��"); // dDayChoice1.setText("��");
		dDayChoice.add(dDayChoice1);

		monthChoice = new Choice();
		for (int i = 1; i <= 12; i++) {
			String S = String.valueOf(i);
			monthChoice.add(S);
		}
		monthChoice.select(today.get(Calendar.MONTH));
		dDayChoice.add(monthChoice);

		JLabel dDayChoice2 = new JLabel("��");
		dDayChoice.add(dDayChoice2);

		dateChoice = new Choice();
		for (int i = 1; i <= 31; i++) {
			String S = String.valueOf(i);
			dateChoice.add(S);
		}
		dateChoice.select(today.get(Calendar.DATE)-1);
		dDayChoice.add(dateChoice);

		JLabel dDayChoice3 = new JLabel("��");
		dDayChoice.add(dDayChoice3);

		dDayTitle.setLayout(new GridLayout(1, 1));
		JLabel dDayTitle1 = new JLabel("D-DAY����                ");
		dDayTitle.add(dDayTitle1);

		JLabel dDayTitle0 = new JLabel(" ");
		dDayTitle.add(dDayTitle0);
		JLabel dDayTitle01 = new JLabel(" ");
		dDayTitle.add(dDayTitle01);

		dDayInputPanel.add(dDayInputArea);

		JButton CB = new JButton("Ȯ��");
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
		if (e.getActionCommand().equals("Ȯ��")) {
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
