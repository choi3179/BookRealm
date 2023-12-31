package ReviewController;

import book.BookDAO;
import book.BookVO;
import review.ReviewDAO;
import review.ReviewVO;
import user.UserVO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class WriteController {
    private static Scanner sc = new Scanner(System.in);
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuffer sb = new StringBuffer();
    private static ReviewDAO reviewDAO = new ReviewDAO();
    private static BookDAO bookDAO = new BookDAO();

    public static int getMenu() {

        int select = 0;

        sb.append("1. 리뷰쓰기\n");
        sb.append("2. 리뷰검색\n");

        System.out.println(sb);
        System.out.println("번호 선택 : ");
        select = sc.nextInt();

        return select;

    }


    private static void reviewsearch() throws IOException {
        BookDAO bookDAO = new BookDAO();
        System.out.println("리뷰검색할 도서명을 적으세요");
        String bookname = br.readLine();
        int bookId = bookDAO.getBookIDByTitle(bookname);
        JoinDAO joinDAO = new JoinDAO();
        joinDAO.JoinBookAndReview(bookId);

    }

    public void reviewinsert(UserVO userVO) throws IOException, SQLException {
        System.out.println("리뷰를 작성할 책 이름을 적으세요.");
        String bookname = br.readLine();
        System.out.println("리뷰 내용 및 별점을 적으세요");

        String contents = br.readLine();
        int popular = Integer.parseInt(br.readLine());
        LocalDateTime reportDate = LocalDateTime.now();
        String userId = userVO.getUserId();
        int bookId = bookDAO.getBookIDByTitle(bookname);
        ////////////////////////////////////////////////

        ReviewVO reviewVO = new ReviewVO(popular, contents, reportDate, userId, bookId);
        int n = reviewDAO.writeReview(reviewVO);

    }

}

