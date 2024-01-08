package myHomework;

import java.util.ArrayList;
import java.util.Scanner;

public class Order {
    Scanner scan = new Scanner(System.in);

    ArrayList<Product> pdList = new ArrayList<>();
    ArrayList<Product> secretPdList = new ArrayList<>();

    int secretTotalPrice = 0;

    public int setOrder(Product product) {
        String answer = scan.nextLine();
        int num = 0;
        if (answer.equals("확인")) {
            pdList.add(product);
            num = 1;
            //name 오류남! System.out.println(this.product.name+" 가 장바구니에 추가되었습니다.");
        } else if (answer.equals("취소")) {
            //nothing
        } else {
            System.out.println("잘못 입력하였습니다. 다시 입력해주세요.\n");
            num = setOrder(product);
        }
        return num;
    }

    public int getOrder() {
        int totalPrice = 0;
        int re = 0;
        if (pdList.isEmpty()) {
            System.out.println("장바구니에 담긴 메뉴가 없습니다.\n");
            return re;
        }

        System.out.println("\n아래와 같이 주문 하시겠습니까?\n");
        System.out.println("[ Orders ]");
        for (Product pd : pdList) {
            System.out.println(pd.name + "\t| " + pd.price + "원 | " + pd.exp);
            totalPrice += pd.price;
        }
        System.out.println("\n[ Total ]");
        System.out.println(totalPrice + "원\n");
        System.out.println("1 주문\t\t2.메뉴판");

        String answer = scan.nextLine();
        if (answer.equals("주문")) {
            System.out.println("주문이 완료되었습니다!\n");
            //비밀기능0번위한친구들
            secretTotalPrice += totalPrice;
            for (Product pd : pdList) {
                secretPdList.add(pd);
            }
            re = 1;
        } else if (answer.equals("메뉴판")) {
            //nothing
        } else {
            System.out.println("잘못 입력하였습니다. 다시 입력해주세요.\n");
            re = getOrder();
        }
        return re;
    }

    public void clearOrder() {
        pdList.clear();
    }

    public int getSecretTotalPrice() {
        return secretTotalPrice;
    }

    public void printSecretPdList() {
        System.out.println("\n[ 총 판매상품 목록 현황 ]");
        if (secretPdList.isEmpty()) {
            System.out.println("현재까지 판매된 상품이 없습니다.");
        } else {
            System.out.println("현재까지 총 판매된 상품 목록은 아래와 같습니다.");
            for (Product pd : secretPdList) {
                System.out.println("- "+pd.name + "\t| " + pd.price + "원");
            }
        }
    }
}
