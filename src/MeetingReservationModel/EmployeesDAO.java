package MeetingReservationModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import MeetingReservationUtil.Util;
import MeetingReservationView.MeetingBookView;


public class EmployeesDAO {

	static Connection conn;
	static PreparedStatement ps;
	static Statement st;
	static ResultSet rs;

	public EmployeesDAO() {
		super();
	}

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	Scanner sc = new Scanner(System.in);
	private EmployeesVO emp = new EmployeesVO();

	public void admin_emp_management() throws SQLException, IOException {
		MeetingBookView vi = new MeetingBookView();

		boolean on = true;

		while (on) {
			System.out.println("*******관리자 모드 입니다*******");
			System.out.println("**********메뉴 선택**********");
			System.out.println("1. 신규 직원 등록");
			System.out.println("2. 직원 정보 삭제");
			System.out.println("3. 직원 정보 수정");
			System.out.println("4. 직원 정보 검색");
			System.out.println("9. 나가기");
			int work = sc.nextInt();

			switch (work) {

			case 1:
				vi.showme(emp_insert());

				break;

			case 2:
				vi.showme(emp_delete());
				break;

			case 3:
				vi.showme(emp_modify());
				break;

			case 4:
				System.out.println("조회할 직원의 이름을 입력하세요");
				System.out.print("이름 : ");
				System.out.println(emp_search(sc.next()));

				break;

			case 9:
				on = false;

			default:
				break;
			}
		}

	}

	private List<EmployeesVO> emp_search(String name) throws SQLException {

		List<EmployeesVO> result = new ArrayList<>();

		String sql = "select * from employees where employee_name = ?";

		conn = Util.getConnection();
		ps = conn.prepareStatement(sql);
		ps.setString(1, name);
		rs = ps.executeQuery();
		while (rs.next()) {
			emp = makeemp(rs);

			result.add(emp);
		}
		Util.dbClose(rs, ps, conn);

		return result;

	}

	EmployeesVO makeemp(ResultSet rs) throws SQLException {
		EmployeesVO e = new EmployeesVO();

		e.setEmployee_id(rs.getString("Employee_id"));
		e.setEmployee_name(rs.getString("Employee_name"));
		e.setDepartment_id(rs.getInt("Department_id"));
		e.setEmployee_position(rs.getString("Employee_position"));
		e.setEmployee_priority(rs.getInt("Employee_priority"));
		e.setEmployee_pw(rs.getString("Employee_pw"));
		return e;
	}

	public int emp_modify() throws SQLException {
		int result = 0;

		System.out.println("수정할 직원의 사번를 입력하세요");
		System.out.print("사번 : ");
		emp.setEmployee_id(sc.next());
		System.out.println("수정할 직원의 이름을 입력하세요");
		System.out.print("이름 : ");
		emp.setEmployee_name(sc.next());
		System.out.println("수정할 직원의 직급을 입력하세요");
		System.out.print("직급 : ");
		String emp_position = sc.next();
		emp.setEmployee_position(emp_position);
		emp.setEmployee_priority(employee_priority(emp_position));
		System.out.println("수정할 직원의 비밀번호를 입력하세요");
		System.out.print("사번 : ");
		emp.setEmployee_pw(sc.next());
		System.out.println("수정할 직원의 부서_id를 입력하세요");
		System.out.print("사번 : ");
		emp.setDepartment_id(sc.nextInt());

		String sql = "update employees " + " set employee_priority = ?, " + " employee_name = ?,"
				+ " employee_position = ?, " + " employee_pw = ?, " + " department_id= ?" + " where employee_id = ?";
		conn = Util.getConnection();
		ps = conn.prepareStatement(sql);
		ps.setInt(1, emp.getEmployee_priority());
		ps.setString(2, emp.getEmployee_name());
		ps.setString(3, emp.getEmployee_position());
		ps.setString(4, emp.getEmployee_pw());
		ps.setInt(5, emp.getDepartment_id());
		ps.setString(6, emp.getEmployee_id());
		result = ps.executeUpdate();
		Util.dbClose(rs, ps, conn);
		return result;
	}

	private int emp_delete() throws SQLException {
		int result = 0;
		System.out.print("삭제할 ID 입력 : ");
		String id = sc.next();

		String sql = "delete from employees where employee_id = ?";
		conn = Util.getConnection();

		ps = conn.prepareStatement(sql);
		ps.setString(1, id);

		result = ps.executeUpdate();
		Util.dbClose(rs, ps, conn);
		return result;
	}

	public int emp_insert() throws IOException, SQLException {
		System.out.println("직원 ID 생성 메뉴입니다.");
		System.out.print("이름 입력 : ");
		emp.setEmployee_name(br.readLine());
		System.out.print("새 PW 입력 : ");
		emp.setEmployee_pw(br.readLine());
		System.out.print("직급 입력 : ");
		String new_position = br.readLine();
		emp.setEmployee_position(new_position);
		emp.setEmployee_priority(employee_priority(new_position));
		System.out.print("부서 ID 입력 : ");
		emp.setDepartment_id(sc.nextInt());
		emp.setEmployee_id(emp_id_create());

		int result = 0;
		String sql = "insert into employees values(?,?,?,?,?,?)";
		conn = Util.getConnection();
		ps = conn.prepareStatement(sql);
		ps.setString(1, emp.getEmployee_id());
		ps.setInt(2, emp.getEmployee_priority());
		ps.setString(3, emp.getEmployee_name());
		ps.setString(5, emp.getEmployee_pw());
		ps.setString(4, new_position);
		ps.setInt(6, emp.getDepartment_id());

		result = ps.executeUpdate();

		Util.dbClose(null, ps, conn);

		return result;
	}

	public static int emp_login(String id, String pw) {

		int result = 0;

		String sql = "select employee_pw from employees where employee_id = ?";

		conn = Util.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getString(1).equals(pw)) {
					result = 1;
				} else {
					System.out.println("비밀번호 오류");
					result = 0;
				}
			} else {
				System.out.println("ID가 존재하지 않습니다");
				result = 0;
			}

			Util.dbClose(ps, conn, rs);
		} catch (SQLException e) {
			System.out.println("알 수 없는 오류 발생");
			result = 0;
		}

		return result;

	}

	public int employee_priority(String position) {
		int pri = 9;
		switch (position) {
		case "총괄사장":
			pri = 0;
			break;
		case "부사장":
			pri = 1;
			break;
		case "전무":
			pri = 2;
			break;
		case "이사":
			pri = 3;
			break;
		case "이사보":
			pri = 4;
			break;
		case "부장":
			pri = 5;
			break;
		case "차장":
			pri = 6;
			break;
		case "과장":
			pri = 7;
			break;
		case "대리":
			pri = 8;
			break;
		case "사원":
			pri = 9;
			break;
		}
		return pri;

	}

	public String emp_id_create() throws SQLException {
		String id = "";
		String s = "";
		Date d = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yy");
		s = transFormat.format(d);
		int a = Integer.parseInt(s);
		if (a <= 60) {
			char c = s.charAt(0);
			c += 49;
			id += "" + c + s.charAt(1);
		}

		String sql = "select employee_id from employees";
		conn = Util.getConnection();
		st = conn.createStatement();
		rs = st.executeQuery(sql);

		boolean duplication = true;
		while (duplication) {
			id += Integer.toString((int) (Math.random() * 1000));
			while (rs.next()) {
				if (!id.equals(emp.getEmployee_id()))
					duplication = false;
			}
		}

		return id;
	}

}
