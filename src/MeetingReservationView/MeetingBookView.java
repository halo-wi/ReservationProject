package MeetingReservationView;

import java.util.List;

import MeetingReservationModel.AdminsVO;

public class MeetingBookView {
	public void showme(int result) {
		if (result >= 1)
			System.out.println("성공하였습니다");
		else
			System.out.println("실패하였습니다");

	}

	public static void showme(List<AdminsVO> bo) {
		System.out.println("게시판 목록");
		for (AdminsVO list : bo) {
			System.out.println(list);
			System.out.println(list);

		}
	}

	public void showLoginResult(int i) {
		if(i == 1) System.out.println("로그인 성공");
		if(i == 0) System.out.println("로그인 실패");

	}
}
