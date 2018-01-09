package Project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

class SelectFrame extends JFrame {

	SelectFrame(String day,int year, int month,int endDay) {

		setLayout(null);
		setSize(300, 200);
		setResizable(false);

		setTitle(year + "." + month + "." + day);

		String c[] = { "��������", "��м���" };
		JButton B[] = new JButton[c.length];
		for (int i = 0; i < c.length; i++) {
			B[i] = new JButton(c[i]);
			B[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					switch (e.getActionCommand()) {
					case "��������":
						dispose();
						new Memo_third(year,month,Integer.parseInt(day),endDay);
						break;
					case "��м���":
						dispose();
						new Feel_secend(year,month,Integer.parseInt(day),endDay);
						break;
					}
				}
			});
		}

		B[0].setBounds(35, 55, 90, 40);
		B[1].setBounds(165, 55, 90, 40);

		add(B[0]);
		add(B[1]);

		setVisible(true);
	}
}
