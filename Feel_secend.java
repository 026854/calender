package Project;

import java.awt.*;
/*import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
*/
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Feel_secend extends JFrame{
	JPanel Feel = new JPanel();
	JPanel feelButtonPaneluttonImage = new JPanel(); //기분버튼이미지
	JPanel feelButtonPanel = new JPanel(); // 기분버튼
	JPanel feelConfirmPanelommentPanel = new JPanel(); // input 한줄일기
	JPanel feelConfirmPanel = new JPanel(); // confirm '확인/수정' 버튼
	
	JTextArea feelTextArea = new JTextArea(2, 35);

	JLabel feelLable = new JLabel("▷한줄일기");
	FileReadAndWrite fileManager=new FileReadAndWrite();
	
	int tempTemper=-1;
	
	public Feel_secend(int year,int month,int day,int endDay) {
		setTitle("기분설정");
	    setSize(600,300); 
	    //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setResizable(false); //창 리사이즈 기능 중지 
	    ImageIcon TI = new ImageIcon("C:\\Users\\LG\\Desktop\\자바\\과제4.PNG");
	    //아이콘 크기 설정
	    //Image TI1 = TI.getImage();
	    //Image TI2 =TI1.getScaledInstance(900,900,java.awt.Image.SCALE_SMOOTH);		
	    setIconImage(TI.getImage());   //.getImage());

		Feel.add(feelButtonPaneluttonImage);
		Feel.add(feelButtonPanel);
		Feel.add(feelConfirmPanelommentPanel);
		Feel.add(feelConfirmPanel);
		
		add(Feel);
		
		feelButtonPanel.setLayout(new  GridLayout(1,4,75,10));
		
		feelConfirmPanelommentPanel.add("Center",feelLable);
		feelConfirmPanelommentPanel.add("Center",feelTextArea);
		
		ToDoData [] data=fileManager.fileRead(year, month);
		if(data!=null){
			feelTextArea.setText(data[day-1].getDiary());
			tempTemper=data[day-1].getTemper();
		}
		
		ImageIcon im1 = new ImageIcon("icon//happy.PNG");
		JLabel ImH = new JLabel("", im1, JLabel.CENTER);
		feelButtonPaneluttonImage.add(ImH);
		
		ImageIcon im2 = new ImageIcon("icon//sad.PNG");
		JLabel ImA = new JLabel("", im2, JLabel.CENTER);
		feelButtonPaneluttonImage.add(ImA);
		
		ImageIcon im3 = new ImageIcon("icon//tired.PNG");
		JLabel ImS = new JLabel("", im3, JLabel.CENTER);
		feelButtonPaneluttonImage.add(ImS);
		
		/*ImageIcon im4 = new ImageIcon("C:\\Users\\박세영\\Desktop\\down.png\\");
		JLabel ImD = new JLabel("", im4, JLabel.CENTER);
		feelButtonPaneluttonImage.add(ImD);
		*/
		String num[] = {"기쁨","슬픔","힘듬"};
		
		JRadioButton feelRadioButton[] = new JRadioButton[num.length];
		ButtonGroup btgroup = new ButtonGroup();
	     for ( int i = 0; i<num.length ; i++){
	    	 feelRadioButton[i] = new JRadioButton(num[i]);
	    	 btgroup.add(feelRadioButton[i]);
	    	 feelRadioButton[i].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						
						switch (e.getActionCommand()) {
						case "기쁨":
							tempTemper = 1;
							break;
						case "슬픔":
							tempTemper = 2;
							break;
						case "힘듬":
							tempTemper = 3;
							break;
						default:
							tempTemper = -1;
							break;
						}
					}
				});
	    	 feelButtonPanel.add("South",feelRadioButton[i]);
	     }
	     
	     JButton feelSubmitButton = new JButton("확인/수정");
	     feelSubmitButton.setSize(50,50);
	     feelSubmitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fileManager.fileUpdate_temper(year, month, day, tempTemper, feelTextArea.getText(),endDay);
				dispose();
			}
		});
	   //  feelSubmitButton.setPreferredSize(new Dimension(580,30));
	     feelConfirmPanel.add(feelSubmitButton);
	     
		setVisible(true); 	
	}
}
