package book;

import user.UserController;

import java.awt.print.Book;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BookController {

    Scanner sc = new Scanner(System.in);

    private BookDAO bookDAO = new BookDAO();

    /**
     * 관리자 도서 관리 기능
     */

    // 도서 추가
    public void add() throws SQLException {
        sc.nextLine();
        BookVO vo = new BookVO();
        System.out.print("도서 제목 입력 : ");       vo.setTitle(sc.nextLine());
        System.out.print("작가 입력 : ");            vo.setWriter(sc.nextLine());
        System.out.print("카테고리 입력 : ");        vo.setCategory(sc.next());
        System.out.print("출판사 입력 : ");          vo.setPublisher(sc.next());
        System.out.print("입고된 재고 수 : ");        vo.setStock(sc.nextInt());  sc.nextLine();
        try{
            System.out.print("도서 발행일자 입력(yyyy-mm-dd) : ");    vo.setPublishDate(Date.valueOf(sc.next()));
        }
        catch(Exception e){
            System.out.println("형식에 맞게 입력해 주세요.");
            System.out.println("--------------------------------------");
            return;
        }
        System.out.print("도서 가격 입력 : ");       vo.setPrice(sc.nextInt());

        if(bookDAO.insertBook(vo) > 0){
            System.out.println("도서 추가가 완료되었습니다.");
        }
        else{
            System.out.println("도서 추가에 실패하였습니다.");
        }
        System.out.println("--------------------------------------");
    }

    // 도서 삭제
    public void delete() throws SQLException {
        System.out.print("삭제할 도서코드를 입력해 주세요 : ");
        int bookId = 0;
        try{
            bookId = sc.nextInt();
        }
        catch(Exception e){
            System.out.println("입력할 수 없는 도서코드 형식입니다.");
            return;
        }

        if(bookDAO.deleteBook(bookId) > 0) {
            System.out.println("도서 정보 삭제가 완료되었습니다.");
        }
        else{
            System.out.println("존재하지 않는 도서코드입니다.");
        }

    }

    // 도서 정보 수정
    public void edit() throws SQLException {
        System.out.print("수정할 도서코드를 입력해 주세요 : ");
        int bookId = 0;
        try{
            bookId = sc.nextInt();
        }
        catch(Exception e){
            System.out.println("입력할 수 없는 도서코드 형식입니다.");
            return;
        }

        BookVO result = bookDAO.selectBybookId(bookId);
        if(result.getBookId() == 0) {
            System.out.println("없는 도서 코드입니다.");
            return;
        }

        sc.nextLine();

        BookVO vo = new BookVO();
        vo.setBookId(bookId);
        System.out.print("도서 제목 입력[" + result.getTitle() + "] : ");       vo.setTitle(sc.nextLine());
        System.out.print("작가 입력[" + result.getWriter() + "] : ");           vo.setWriter(sc.next());
        System.out.print("카테고리 입력[" + result.getCategory() + "] : ");      vo.setCategory(sc.next());
        System.out.print("출판사 입력[" + result.getPublisher() + "] : ");       vo.setPublisher(sc.next());
        System.out.print("입고된 재고 수[" + result.getStock() + "] : ");        vo.setStock(sc.nextInt());   sc.nextLine();
        try{
            System.out.print("도서 발행일자 입력(yyyy-mm-dd) [" + result.getPublishDate() + "] : ");    vo.setPublishDate(Date.valueOf(sc.next()));
        }
        catch(Exception e){
            System.out.println("형식에 맞게 입력해 주세요.");
            System.out.println("--------------------------------------");
            return;
        }
        System.out.print("도서 가격 입력[" + result.getPrice() + "] : ");       vo.setPrice(sc.nextInt());

        if(bookDAO.updateBook(vo) > 0){
            System.out.println("정보 수정이 완료되었습니다.");
        }
        else{
            System.out.println("정보 수정에 실패하였습니다.");
        }

        System.out.println("--------------------------------------");

    }

    public void showBookAll() throws SQLException {
        List<BookVO> list = bookDAO.selectAll();
        for(int i=0;i<list.size();i++){
            BookVO vo = list.get(i);
            System.out.println("도서코드 [\t" + vo.getBookId() + "\t]");
            System.out.println("제목 : " + vo.getTitle());
            System.out.println("작가 : " + vo.getWriter());
            System.out.println("카테고리 : "  + vo.getCategory());
            System.out.println("출판사 : " + vo.getPublisher());
            System.out.println("가격 : " + vo.getPrice());
            System.out.println("초기 재고 [ " + vo.getStock() + " ] / 판매 수 [ " + vo.getSales() + " ]");
            System.out.println("발행 날짜 : " + vo.getPublishDate());
            System.out.println("---------------------------------------");
        }
        System.out.println();
    }

    public void adminMain() throws SQLException {

        int op = 0;

        while (true){
            System.out.println("============= 관리자 모드 =============");
            System.out.println("1: 회원 관리");
            System.out.println("2: 도서 관리");
            System.out.println("9: EXIT");
            System.out.print(">>>  ");    op = sc.nextInt();

            switch (op) {
                case 1:
                    new UserController().adminUserMenu();
                    break;
                case 2:
                    adminBookMenu();
                    break;
                case 9:
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
                    break;
            }
        }
    }

    public void adminBookMenu() throws SQLException {
        int op = 0;

        while (true) {
            System.out.println("============= 도서 관리 모드 =============");
            System.out.println("1: 도서 추가");
            System.out.println("2: 도서 조회");
            System.out.println("3: 도서 수정");
            System.out.println("4: 도서 삭제");
            System.out.println("9: EXIT");
            System.out.print(">>>  ");    op = sc.nextInt();

            switch (op) {
                case 1:
                    add();
                    break;
                case 2:
                    showBookAll();
                    break;
                case 3:
                    edit();
                    break;
                case 4:
                    delete();
                    break;
                case 9:
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
                    break;
            }
        }

    }

}
