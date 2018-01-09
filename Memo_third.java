package Project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Memo_third extends JFrame {
	JPanel schedulePlusPanel = new JPanel();
	JPanel inputPanel1 = new JPanel();
	JPanel inputPanel2 = new JPanel();
	JPanel inputPanel3 = new JPanel();
	JTextArea input = new JTextArea();

	JButton button = new JButton("확인/수정");
	JLabel dot = new JLabel("일정을 추가하거나 수정하여 주세요.");
	
	FileReadAndWrite fileManager=new FileReadAndWrite();
	
	

	public Memo_third(int year,int month,int day,int endDay) {
		ToDoData [] data = fileManager.fileRead(year, month);
		if(data!=null){
			input.setText(data[day-1].getComment());
			
		}
		// 3-0
		
		
		setTitle("일정설정");
		// 3-1
		ImageIcon TI = new ImageIcon("C:\\Users\\LG\\Desktop\\자바\\과제4.PNG");
		setIconImage(TI.getImage());
		input.setPreferredSize(new Dimension(300, 100));
		inputPanel1.add(dot);
		inputPanel2.add(input);
		inputPanel3.add(button);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fileManager.fileUpdate_toDo(year, month, day, input.getText(), endDay);
				dispose();
			}
		});
		
		inputPanel1.add(dot);
		inputPanel2.add(input);
		inputPanel3.add(button);
		schedulePlusPanel.add(inputPanel1);
		schedulePlusPanel.add(inputPanel2);
		schedulePlusPanel.add(inputPanel3);
		add(schedulePlusPanel);
		setSize(350, 250);
		setVisible(true);
	}
}