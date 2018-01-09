package Project;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;

public class calendar_first extends Frame implements ActionListener {//Frame 상속  액션리스너 

	private static final long serialVersionUID = 1L;//파일을 직렬화
	private Panel panel = null;
	private Panel panel1 = null;
	Color c = new Color(232, 217, 255);
	private Choice choice = null;
	Calendar nowcal = Calendar.getInstance();//매번 생성하지 않고 처음 호출시 jvm에 static하게 생성학자 하는 클래스의 인스턴스를 쓰기위함
	private Choice choice1 = null;
	private Label label = null;
	private Label label1 = null;
	private Button[] bt2 = new Button[42];
	private Button DDay = new Button("D-Day 설정");
	Label ddayout = null;
	private int year = 0;
	private int month = 0;
	ToDoData [] data;
	
	JLabel timer=null;
	
	int startDay = 0;
	int endDay = 0;
	
	File file;
	FileReadAndWrite fileManager=new FileReadAndWrite();

	//Inner Class
	//현재 클래스의 Frame의 Label을 공유하기 위해 Inner Class로 만듦.
	class Timer extends Thread{
		@Override
		// Method : 타이머 Thread의 실행 매서드
	    // Return Value : void
	    // Parameter : void
	    // Use : 1초에 한번씩 시간을 받아와 원하는 Format대로 Label에 출력한다.
		public void run() {
			while(true){
			Date date = new Date();
			SimpleDateFormat format= new SimpleDateFormat("HH:mm:ss");
			
			String updateDate=format.format(date);
			
			timer.setText(updateDate);
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}
					
		}
	}

	
	private Panel getPanel() {
		if (panel == null) {
			label1 = new Label();
			label1.setText("년");
			label = new Label();
			label.setText("월");
			Label label공간 = new Label("     ");
			panel = new Panel();
			panel.setLayout(new FlowLayout());
			panel.add(getChoice(), null);
			panel.add(label1, null);
			panel.add(getChoice1(), null);
			panel.add(label, null);
			panel.add(DDay);
			JRadioButton returnToNum = new JRadioButton("날짜 보기");
			JRadioButton checkdday = new JRadioButton("D-Day 보기");
			JRadioButton checkfeel = new JRadioButton("기분 보기");
			JRadioButton checkmemo = new JRadioButton("memo 보기");
			
			timer=new JLabel("");     /**/

			returnToNum.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					for (int i = 1; i <= endDay; i++) {
						bt2[i + startDay - 2].setLabel(i + "");
					}
				}
			});
			checkdday.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					for (int i = 1; i <= endDay; i++) {
						bt2[i + startDay - 2].setLabel(i + "");
						//bt2[i + startDay - 2].setActionCommand(i+"");;/**/
					}
					ArrayList<D_DayData> dDayList=fileManager.dDayFileRead();//collection
					if(dDayList==null)
						return;
					for(int i=0;i<dDayList.size();i++){
						D_DayData temp= dDayList.get(i);
						if(year==temp.getYear() && month==temp.getMonth()){
							for (int j = 1;j  <= endDay; j++) {
								if(j==temp.getDate())
									bt2[j + startDay - 2].setLabel(temp.getComment());
							}
						}
					}
				}
			});
			checkfeel.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					data=fileManager.fileRead(year, month);/**/
					for (int i = 1; i <= endDay; i++) {
						bt2[i + startDay - 2].setLabel(i + "");
						//bt2[i + startDay - 2].setActionCommand(i+"");;
					}
					int j=0;
				
					for (int i = 1; i <= endDay; i++) {
						switch (data[j++].getTemper()) {
						case -1:
							//System.out.println("아무감정없음.");
							break;
						case 1:
							bt2[i + startDay - 2].setLabel("기쁨");
							break;
						case 2:
							bt2[i + startDay - 2].setLabel("슬픔");
							break;
						case 3:
							bt2[i + startDay - 2].setLabel("힘듬");
							break;

						}
					}
				}
			});
			///////////////일정보기
			checkmemo.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					data=fileManager.fileRead(year, month);
					String temp;
					for (int i = 1; i <= endDay; i++) {
						bt2[i + startDay - 2].setLabel(i + "");
						//bt2[i + startDay - 2].setActionCommand(i+"");
						
					}
					
					int j=0;
					
					for (int i = 1; i <= endDay; i++) {
						temp=data[j++].getComment();
						if(temp!=null)
							bt2[i + startDay - 2].setLabel(temp);
					}
				}
			});
			ButtonGroup bgroup = new ButtonGroup();
			bgroup.add(returnToNum);
			bgroup.add(checkdday);
			bgroup.add(checkfeel);
			bgroup.add(checkmemo);
			panel.add(label공간);
			panel.add(returnToNum);
			panel.add(checkdday);
			panel.add(checkfeel);
			panel.add(checkmemo);
			panel.add(timer);
			
		}
		return panel;
	}

	private Panel getPanel1() {
		if (panel1 == null) {
			panel1 = new Panel();
			panel1.setLayout(new GridLayout(7, 7));
			Label[] bt1 = new Label[7];
			String[] day = { " SUN", " MON", " TUE", " WED", " THU", " FRI",
					" SAT" };
			for (int i = 0; i < 7; i++) {
				bt1[i] = new Label(day[i]);
				panel1.add(bt1[i]);
				if (i == 0) {
					bt1[i].setForeground(Color.red);
				}
				if (i == 6) {
					bt1[i].setForeground(Color.blue);
				}
			}

			bt2 = new Button[42];
			DDay.addActionListener(this);
			for (int i = 0; i < 42; i++) {
				bt2[i] = new Button("");
				bt2[i].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						new SelectFrame(e.getActionCommand(),year,month,endDay);/**/
					}
				});
				panel1.add(bt2[i]);

				if (i == 0 || (i != 0 && i % 7 == 0)) {
					bt2[i].setForeground(Color.red);
				}
				if (i == 6 || (i != 0 && i % 7 == 6)) {
					bt2[i].setForeground(Color.blue);
				}
			}

			year = Integer.parseInt(choice.getSelectedItem());
			month = Integer.parseInt(choice1.getSelectedItem());

			

			java.util.Calendar sDay = java.util.Calendar.getInstance();
			//java.util.Calendar eDay = java.util.Calendar.getInstance();

			sDay.set(year, month - 1, 1);/**/
			//eDay.set(year, month, 1);
			//eDay.add(java.util.Calendar.DATE, -1);

			startDay = sDay.get(java.util.Calendar.DAY_OF_WEEK);
			endDay = sDay.getActualMaximum(Calendar.DAY_OF_MONTH);

			for (int i = 1; i <= endDay; i++) {
				bt2[i + startDay - 2].setLabel(i + "");
			}
			for (int i = 0; i < 42; i++) {
				int date = nowcal.get(Calendar.DATE);/**/
				String dateS = String.valueOf(date);
				if (bt2[i].getLabel().equals(dateS)) {
					bt2[i].setBackground(c);
				} else {
					bt2[i].setBackground(Color.white);
				}
				if (bt2[i].getLabel().equals("")) {
					bt2[i].setEnabled(false);
				} else {
					bt2[i].setEnabled(true);
				}
			}
			file=new File(year+"_"+month);
			if(!file.exists()){
				try {
					file.createNewFile();
					fileManager.fileCreate(year, month, endDay);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

		}
		return panel1;
	}

	private Choice getChoice() {
		if (choice == null) {
			choice = new Choice();
			for (int i = 1990; i < 2050; i++) {
				String year = String.valueOf(i);
				choice.add(year);

			}
			choice.select(nowcal.get(Calendar.YEAR) - 1990);/**/

		}
		return choice;
	}

	private Choice getChoice1() {
		if (choice1 == null) {
			choice1 = new Choice();

			choice1.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {

					for (int i = 0; i < 42; i++) {
						bt2[i].setLabel("");
					}

					year = Integer.parseInt(choice.getSelectedItem());
					month = Integer.parseInt(choice1.getSelectedItem());

					java.util.Calendar sDay = java.util.Calendar.getInstance();
					java.util.Calendar eDay = java.util.Calendar.getInstance();

					sDay.set(year, month - 1, 1);
					eDay.set(year, month, 1);
					eDay.add(java.util.Calendar.DATE, -1);

					startDay = sDay.get(java.util.Calendar.DAY_OF_WEEK);
					endDay = eDay.get(java.util.Calendar.DATE);

					for (int i = 1; i <= endDay; i++) {
						bt2[i + startDay - 2].setLabel(i + "");
					}

					for (int i = 0; i < 42; i++) {

						int date = nowcal.get(Calendar.DATE);
						String dateS = String.valueOf(date);
						if (bt2[i].getLabel().equals(dateS)
								&& nowcal.get(Calendar.YEAR) == year
								&& nowcal.get(Calendar.MONTH) + 1 == month) {
							bt2[i].setBackground(c);
						} else {
							bt2[i].setBackground(Color.white);
						}
						if (bt2[i].getLabel().equals("")) {
							bt2[i].setEnabled(false);
						} else {
							bt2[i].setEnabled(true);
						}
					}
					file=new File(year+"_"+month);
					
					if(!file.exists()){
						try {
							file.createNewFile();
							fileManager.fileCreate(year, month, endDay);
						} catch (IOException ee) {
							// TODO Auto-generated catch block
							ee.printStackTrace();
						}
						
					}

				}
			});
			for (int i = 1; i < 13; i++) {
				String month = String.valueOf(i);
				choice1.add(month);

			}
			choice1.select(nowcal.get(Calendar.MONTH));
			
			

		}
		return choice1;
	}

	
	public calendar_first() {
		super();
		initialize();
		Timer t= new Timer();
		t.start();
	}

	private void initialize() {
		this.setSize(800, 500);
		this.setTitle("scheduler");
		this.setResizable(false);
		this.add(getPanel(), BorderLayout.NORTH);
		this.add(getPanel1(), BorderLayout.CENTER);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				// System.out.println("windowClosing()"); // TODO Auto-generated
				// Event stub windowClosing()
				System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()) {
		case "D-Day 설정":
			new D_Day_Four();
			break;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		calendar_first cal = new calendar_first();
		
		cal.setVisible(true);
	}


} 
