package MeetingReservationView;

import java.util.List;

import MeetingReservationModel.AdminsVO;

public class MeetingBookView {
	public void showme(int result) {
		if (result >= 1)
			System.out.println("�����Ͽ����ϴ�");
		else
			System.out.println("�����Ͽ����ϴ�");

	}

	public static void showme(List<AdminsVO> bo) {
		System.out.println("�Խ��� ���");
		for (AdminsVO list : bo) {
			System.out.println(list);
			System.out.println(list);

		}
	}

	public void showLoginResult(int i) {
		if(i == 1) System.out.println("�α��� ����");
		if(i == 0) System.out.println("�α��� ����");

	}
}
