package BookSearchProgram;

import ConnUtil.ConnectionSingletonHelper;
import user.UserController2;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class BSP_MainPage {

	private static Scanner sc = new Scanner(System.in);

	public int MainMenu() {

		StringBuffer sb = new StringBuffer();
		int select = 0;

		sb.append("Welcome!!\n");
		sb.append("온라인 서점 < BookRealm > 입니다 *^^* \n");
		sb.append("\n");
		sb.append("======= menu =======\n");
		sb.append("\n");
		sb.append("1. 로그인\n");
		sb.append("2. 도서 검색\n");
		sb.append("3. 프로그램 종료\n");
		sb.append("\n");
		sb.append("====================\n");

		System.out.println(sb);
		System.out.print("번호 선택 : ");
		select = sc.nextInt();

		return select;
	}

	public void mainMenu() throws SQLException, IOException {

		int no = 0;

		do {

			no = MainMenu();
			switch (no) {
			case 1:
				System.out.println("로그인을 선택하셨습니다.");
				UserController2 user = new UserController2();
				user.mainView();
				break;
			case 2:
				System.out.println("도서 검색을 선택하셨습니다.");
				BSP_BookSearchPage.choice_1();
				System.out.println();
				break;
			case 3:
				System.out.println("프로그램을 종료합니다.");
				sc.close();
				ConnectionSingletonHelper.close();
				System.exit(0);

			}
		} while (no != 3);
	}

}
