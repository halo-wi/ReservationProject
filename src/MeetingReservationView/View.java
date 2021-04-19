 package MeetingReservationView;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class View {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 487, 574);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btn1 = new JButton("1");
		btn1.setFont(new Font("±¼¸²", Font.PLAIN, 40));
		btn1.setBounds(12, 254, 90, 78);
		frame.getContentPane().add(btn1);
		
		JButton btn2 = new JButton("2");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn2.setFont(new Font("±¼¸²", Font.PLAIN, 40));
		btn2.setBounds(124, 254, 90, 78);
		frame.getContentPane().add(btn2);
		
		JButton btn3 = new JButton("3");
		btn3.setFont(new Font("±¼¸²", Font.PLAIN, 40));
		btn3.setBounds(243, 254, 90, 78);
		frame.getContentPane().add(btn3);
		
		JButton btn4 = new JButton("4");
		btn4.setFont(new Font("±¼¸²", Font.PLAIN, 40));
		btn4.setBounds(356, 254, 90, 78);
		frame.getContentPane().add(btn4);
		
		JButton btn9 = new JButton("9");
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn9.setFont(new Font("±¼¸²", Font.PLAIN, 40));
		btn9.setBounds(12, 351, 434, 83);
		frame.getContentPane().add(btn9);
		
		JButton btn0 = new JButton("0");
		btn0.setFont(new Font("±¼¸²", Font.PLAIN, 40));
		btn0.setBounds(185, 444, 90, 78);
		frame.getContentPane().add(btn0);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		lblNewLabel.setBounds(0, 0, 473, 248);
		frame.getContentPane().add(lblNewLabel);
	}
}



