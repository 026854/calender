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
	JPanel feelButtonPaneluttonImage = new JPanel(); //��й�ư�̹���
	JPanel feelButtonPanel = new JPanel(); // ��й�ư
	JPanel feelConfirmPanelommentPanel = new JPanel(); // input �����ϱ�
	JPanel feelConfirmPanel = new JPanel(); // confirm 'Ȯ��/����' ��ư
	
	JTextArea feelTextArea = new JTextArea(2, 35);

	JLabel feelLable = new JLabel("�������ϱ�");
	FileReadAndWrite fileManager=new FileReadAndWrite();
	
	int tempTemper=-1;
	
	public Feel_secend(int year,int month,int day,int endDay) {
		setTitle("��м���");
	    setSize(600,300); 
	    //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setResizable(false); //â �������� ��� ���� 
	    ImageIcon TI = new ImageIcon("C:\\Users\\LG\\Desktop\\�ڹ�\\����4.PNG");
	    //������ ũ�� ����
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
		
		/*ImageIcon im4 = new ImageIcon("C:\\Users\\�ڼ���\\Desktop\\down.png\\");
		JLabel ImD = new JLabel("", im4, JLabel.CENTER);
		feelButtonPaneluttonImage.add(ImD);
		*/
		String num[] = {"���","����","����"};
		
		JRadioButton feelRadioButton[] = new JRadioButton[num.length];
		ButtonGroup btgroup = new ButtonGroup();
	     for ( int i = 0; i<num.length ; i++){
	    	 feelRadioButton[i] = new JRadioButton(num[i]);
	    	 btgroup.add(feelRadioButton[i]);
	    	 feelRadioButton[i].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						
						switch (e.getActionCommand()) {
						case "���":
							tempTemper = 1;
							break;
						case "����":
							tempTemper = 2;
							break;
						case "����":
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
	     
	     JButton feelSubmitButton = new JButton("Ȯ��/����");
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
