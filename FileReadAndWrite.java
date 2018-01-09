package Project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Vector;

public class FileReadAndWrite {

	private ToDoData temp[];
	
	// Method : ��,�� �� ���� ����
    // Return Value : void
    // Parameter : year(��), month(��), endDay(������ ��)
    // Use : �ش� ��,�� File�� ToDoData��ü�� endDay(28 or 30 or 31)��ŭ �̸� ���� �����Ѵ�.
	void fileCreate(int year, int month, int endDay) {
		temp = new ToDoData[endDay];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = new ToDoData();
			temp[i].setDay(i + 1);
		}
		try {
			FileOutputStream f = new FileOutputStream(year + "_" + month);/**/
			ObjectOutput s = new ObjectOutputStream(f);

			s.writeObject(temp);

			s.flush();
		} catch (IOException e) {
		}

	}

	// Method : ��,�� �� ���� �б�
    // Return Value : ToDoData[] (�� ��,���� ��� ������ �����ϴ� �迭)
    // Parameter : year(��), month(��)
    // Use : �ش� ��,�� File�� �ִ� ��� ToDoData��ü �迭 ���·� �о� �´�. ��ü�� ����ȭ�Ͽ� �����͸� �����߱� ������ �����ϴ�.
	ToDoData[] fileRead(int year, int month) {
		ToDoData[] readFile = null;
		try {
			FileInputStream in = new FileInputStream(year + "_" + month);
			ObjectInput s = new ObjectInputStream(in);
			readFile = (ToDoData[]) s.readObject();
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFound");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFount");
		} catch (IOException e) {
			System.out.println("IOException" + e.getMessage());
		}
		return readFile;
	}

	// Method : ��� ������Ʈ
    // Return Value : void
    // Parameter : year(��), month(��), day(��¥), temper(��п� ���� ������), diary(������ �ڸ�Ʈ), endDay(���� �� ��¥)
    // Use : ������ ��, ���� ������ �о�ͼ� ������ ��¥�� ��л��� �� ������ �ڸ�Ʈ�� ������Ʈ �� �� �ٽ� ���Ͽ� �����Ѵ�.
	void fileUpdate_temper(int year, int month, int day, int temper, String diary, int endDay) {
		File file = new File(year + "_" + month);
		if (!file.exists()) {//������ ������ �����Ѵ�.
			try {
				file.createNewFile();
				fileCreate(year, month, endDay);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			ToDoData[] readFile = fileRead(year, month);
	
			readFile[day - 1].setTemper(temper);
			readFile[day - 1].setDiary(diary);
			
			try {
				FileOutputStream f = new FileOutputStream(year + "_" + month);
				ObjectOutput s = new ObjectOutputStream(f);

				s.writeObject(readFile);

				s.flush();
			} catch (IOException e) {
			}
		}

	}
	
	// Method : ���� ������Ʈ
    // Return Value : void
    // Parameter : year(��), month(��), day(��¥), diary(������ �ڸ�Ʈ), endDay(���� �� ��¥)
    // Use : ������ ��, ���� ������ �о�ͼ� ������ ��¥��  �� ���� ������Ʈ �� �� �ٽ� ���Ͽ� �����Ѵ�.
	void fileUpdate_toDo(int year,int month,int day, String comment,int endDay){
		File file = new File(year + "_" + month);
		if (!file.exists()) {//������ ������ �����Ѵ�.
			try {
				file.createNewFile();
				fileCreate(year, month, endDay);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			ToDoData[] readFile = fileRead(year, month);
			readFile[day - 1].setComment(comment);
			try {
				FileOutputStream f = new FileOutputStream(year + "_" + month);
				ObjectOutput s = new ObjectOutputStream(f);

				s.writeObject(readFile);

				s.flush();
			} catch (IOException e) {
			}
		}

	}
	
	// Method : �������� �о����
    // Return Value : ArrayList<D_DayData> (���� ��ϰ��� �޸� ũ�Ⱑ ���������� �����Ƿ� ArrayList ���)
    // Parameter : void
    // Use : ���������� �о�� D_DayData�� ������ ArrayList�� ���·� ��ȯ�Ѵ�. �̿��� Ŭ������ ����ȭ�� ����Ѵ�.
	ArrayList<D_DayData> dDayFileRead(){
		File file= new File("D_Day");
		ArrayList<D_DayData> dDay =null;
		D_DayData temp=null;
		
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			try {
				FileInputStream in = new FileInputStream("D_Day");
				ObjectInput s = new ObjectInputStream(in);
				dDay = (ArrayList<D_DayData>) s.readObject();/**/
			} catch (FileNotFoundException e) {
				System.out.println("FileNotFound");
			} catch (ClassNotFoundException e) {
				System.out.println("ClassNotFount");
			} catch (IOException e) {
				System.out.println("�������");
			}
			
		}
		
		return dDay;
	}
	
	// Method : ���� �߰��ϱ�
    // Return Value : void
    // Parameter : year(��), month(��), date(��¥), comment(� ������)
    // Use : ���������� �о�� �� ArrayList�� �߰��ϰ��� �ϴ� DDay�� Data�� �߰��Ͽ� �ٽ� ���Ͽ� ����.
	void addDday(int year,int month,int date, String comment){
		File file = new File("D_Day");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			ArrayList<D_DayData> dDay = dDayFileRead();
			if(dDay==null){
				dDay=new ArrayList<>();
			}
			dDay.add(new D_DayData(year,month,date, comment));
			try {
				FileOutputStream f = new FileOutputStream("D_Day");
				ObjectOutput s = new ObjectOutputStream(f);

				s.writeObject(dDay);

				s.flush();
			} catch (IOException e) {
			}
		}
	}
	// Method : ���� �����ϱ�
    // Return Value : void
    // Parameter : row(JTable���� ���õ� ����)
    // Use : ���������� �о�� �� ArrayList���� �ش� row�� ������ �� �ٽ� ���Ͽ� ����.
	void deleteDday(int row){
		
		ArrayList<D_DayData> dDay = dDayFileRead();
		dDay.remove(row);
		try {
			FileOutputStream f = new FileOutputStream("D_Day");
			ObjectOutput s = new ObjectOutputStream(f);

			s.writeObject(dDay);

			s.flush();
		} catch (IOException e) {
		}
	}

}
