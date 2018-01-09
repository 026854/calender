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
	
	// Method : 년,월 별 파일 생성
    // Return Value : void
    // Parameter : year(년), month(월), endDay(끝나는 날)
    // Use : 해당 년,월 File에 ToDoData객체를 endDay(28 or 30 or 31)만큼 미리 만들어서 저장한다.
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

	// Method : 년,월 별 파일 읽기
    // Return Value : ToDoData[] (각 년,월에 모든 일정을 저장하는 배열)
    // Parameter : year(년), month(월)
    // Use : 해당 년,월 File에 있는 모든 ToDoData객체 배열 형태로 읽어 온다. 객체를 직렬화하여 데이터를 저장했기 때문에 가능하다.
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

	// Method : 기분 업데이트
    // Return Value : void
    // Parameter : year(년), month(월), day(날짜), temper(기분에 따른 정수값), diary(간단한 코멘트), endDay(월의 끝 날짜)
    // Use : 선택한 년, 월의 파일을 읽어와서 선택한 날짜의 기분상태 및 간단한 코멘트를 업데이트 한 후 다시 파일에 저장한다.
	void fileUpdate_temper(int year, int month, int day, int temper, String diary, int endDay) {
		File file = new File(year + "_" + month);
		if (!file.exists()) {//파일이 없으면 생성한다.
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
	
	// Method : 할일 업데이트
    // Return Value : void
    // Parameter : year(년), month(월), day(날짜), diary(간단한 코멘트), endDay(월의 끝 날짜)
    // Use : 선택한 년, 월의 파일을 읽어와서 선택한 날짜의  할 일을 업데이트 한 후 다시 파일에 저장한다.
	void fileUpdate_toDo(int year,int month,int day, String comment,int endDay){
		File file = new File(year + "_" + month);
		if (!file.exists()) {//파일이 없으면 생성한다.
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
	
	// Method : 디데이파일 읽어오기
    // Return Value : ArrayList<D_DayData> (일정 등록과는 달리 크기가 정해져있지 않으므로 ArrayList 사용)
    // Parameter : void
    // Use : 디데이파일을 읽어와 D_DayData로 구성된 ArrayList의 형태로 반환한다. 이역시 클래스의 직렬화를 사용한다.
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
				System.out.println("비어있음");
			}
			
		}
		
		return dDay;
	}
	
	// Method : 디데이 추가하기
    // Return Value : void
    // Parameter : year(년), month(월), date(날짜), comment(어떤 일인지)
    // Use : 디데이파일을 읽어온 후 ArrayList에 추가하고자 하는 DDay의 Data를 추가하여 다시 파일에 쓴다.
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
	// Method : 디데이 삭제하기
    // Return Value : void
    // Parameter : row(JTable에서 선택된 라인)
    // Use : 디데이파일을 읽어온 후 ArrayList에서 해당 row를 삭제한 후 다시 파일에 쓴다.
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
