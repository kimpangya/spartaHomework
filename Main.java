package myHomework;

import homework3.AddOperation;
import homework3.Calculator;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    static Menu coffee = new Menu("Coffee", "카페인이 들어간 커피류");
    static Product[] coffeeProduct = new Product[]{
            new Product("아메리카노", 2000, "시원한 원두커피"),
            new Product("큐브라떼", 3500, "카페라떼에 연유를 넣은 달달한 커피"),
            new Product("바닐라라떼", 3000, "바닐라향이 나는 달달한 커피")
    };


    static Menu tea = new Menu("Tea", "여러종류 티백을 우려 만든 음료");
    static Product[] teaProduct = new Product[]{
            new Product("캐모마일", 2500, "캐모마일 티백을 넣은 차"),
            new Product("사과유자차", 3500, "사과티백에 유자청을 넣은 상큼달달 음료"),
            new Product("아이스티", 3000, "달달한 복숭아맛 음료")
    };


    static Menu juice = new Menu("Juice", "과일이 들어간 쥬스 음료");
    static Product[] juiceProduct = new Product[]{
            new Product("딸기쥬스", 2000, "딸기를 갈아넣은 달달한 쥬스"),
            new Product("딸기바나나쥬스", 3500, "딸기와 바나나를 넣은 쥬스"),
            new Product("자몽오렌지쥬스", 3500, "자몽 오렌지를 넣은 새콤한 쥬스")
    };

    static Order order = new Order();
    static int orderNum = 1;

    public static void main(String[] args) {
        String answer;

        coffee.setProduct(coffeeProduct);
        tea.setProduct(teaProduct);
        juice.setProduct(juiceProduct);

        while (true) {

            answer = mainMenu();

            setmainMenu(answer);
        }

    }

    public static String mainMenu() {
        System.out.println("메가커피에 오신걸 환영합니다.\n모든 음료는 ice 입니다.");
        System.out.println("아래 메뉴판을 보고 메뉴를 골라 입력해주세요.\n");

        System.out.println("[ BEVERAGE MENU ]");
        System.out.println("1. " + coffee.name + "\t| " + coffee.exp);
        System.out.println("2. " + tea.name + "\t\t| " + tea.exp);
        System.out.println("3. " + juice.name + "\t| " + juice.exp);
        System.out.println("\n[ ORDER MENU ]");
        System.out.println("4. Order\t| 장바구니를 확인 후 주문합니다.");
        System.out.println("5. Cancel\t| 진행 중인 주문을 취소합니다.");
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    public static void setmainMenu(String answer) {
        int i;
        if(answer.equals("0")){
            System.out.println("[ 총 판매금액 현황 ]");
            System.out.println( "현재까지 총 판매된 금액은 [ "+order.getSecretTotalPrice()+"원 ] 입니다.\n");
            order.printSecretPdList();
            System.out.println("\n1. 돌아가기");
            Scanner scan=new Scanner(System.in);
            String an=scan.nextLine();
            if(an.equals("돌아가기")||an.equals("1")){
                //nothing 어차피 main문 while에 의해 메인메뉴판으로 돌아가게 됨
            }else{
                System.out.println("잘못 입력하였습니다. 다시 메뉴판을 보려면 1 또는 돌아가기를 입력해주세요.");
                setmainMenu("0");
            }
        }
        else if (answer.equals(coffee.name)) {
            productPrint(answer);
            i = 1;
            for (Product pd : coffee.product) {
                System.out.println(i++ + ". " + pd.name + "\t| " + pd.price + "원\t| " + pd.exp);
            }
            putpd(1);
        } else if (answer.equals(tea.name)) {
            productPrint(answer);
            i = 1;
            for (Product pd : tea.product) {
                System.out.println(i++ + ". " + pd.name + "\t| " + pd.price + "원\t| " + pd.exp);
            }
            putpd(2);
        } else if (answer.equals(juice.name)) {
            productPrint(answer);
            i = 1;
            for (Product pd : juice.product) {
                System.out.println(i++ + ". " + pd.name + "\t| " + pd.price + "원\t| " + pd.exp);
            }
            putpd(3);
        } else if (answer.equals("Order")) {
            orderMenu();
        } else if (answer.equals("Cancel")) {
            cancelMenu();
        } else {
            System.out.println("잘못 입력하였습니다. 다시 입력해주세요\n=> ");
            Scanner scan = new Scanner(System.in);
            setmainMenu(scan.nextLine());
        }
    }

    public static void productPrint(String name) {
        System.out.println("\n\n메가커피에 오신걸 환영합니다.");
        System.out.println("아래 상품메뉴판을 보시고 상품을 골라 입력해주세요.\n");
        System.out.println("[ " + name + " MENU ]");
    }

    public static void putpd(int state) {
        Scanner scan = new Scanner(System.in);
        String answer;
        switch (state) {
            case 1:
                answer = scan.nextLine();
                for (Product pd : coffee.product) {
                    if (answer.equals(pd.name)) {
                        System.out.println("\n" + answer + "\t| " + pd.price + "원\t| " + pd.exp);
                        System.out.println("\n위 메뉴를 장바구니에 추가하시겠습니까?");
                        System.out.println("1. 확인\t\t2. 취소");
                        //장바구니에 추가하기
                        int i = order.setOrder(pd);
                        if (i == 1) {
                            System.out.println("\n" + pd.name + " 가 장바구니에 추가되었습니다.\n\n");
                        }
                    }
                }
                break;
            case 2:
                answer = scan.nextLine();
                for (Product pd : tea.product) {
                    if (answer.equals(pd.name)) {
                        System.out.println("\n" + answer + "\t| " + pd.price + "원\t| " + pd.exp);
                        System.out.println("\n위 메뉴를 장바구니에 추가하시겠습니까?");
                        System.out.println("1. 확인\t\t2. 취소");
                        //장바구니에 추가하기
                        int i = order.setOrder(pd);
                        if (i == 1) {
                            System.out.println("\n" + pd.name + " 가 장바구니에 추가되었습니다.\n");
                        }
                    }
                    System.out.println();
                }
                break;
            case 3:
                answer = scan.nextLine();
                for (Product pd : juice.product) {
                    if (answer.equals(pd.name)) {
                        System.out.println("\n" + answer + "\t| " + pd.price + "원\t| " + pd.exp);
                        System.out.println("\n위 메뉴를 장바구니에 추가하시겠습니까?");
                        System.out.println("1. 확인\t\t2. 취소");
                        //장바구니에 추가하기
                        int i = order.setOrder(pd);
                        if (i == 1) {
                            System.out.println("\n" + pd.name + " 가 장바구니에 추가되었습니다\n\n");
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    public static void orderMenu() {
        int orNum = order.getOrder();
        if (orNum == 1) {
            //장바구니 비우기
            order.clearOrder();
            System.out.println("대기번호는[ " + orderNum + " ]번 입니다.");
            System.out.println("(3초후 메뉴판으로 돌아갑니다.)");
            System.out.println("------------------------\n\n");
            orderNum++;
            //3초후 돌아가기 스레드슬립
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void cancelMenu() {
        Scanner scan = new Scanner(System.in);
        System.out.println("진행하던 주문을 취소하시겠습니까?");
        System.out.println("1. 확인 \t\t 2. 취소");
        String answer = scan.nextLine();
        if ((answer.equals("확인"))) {
            //장바구니 비우기
            order.clearOrder();
            System.out.println("진행하던 주문이 취소되었습니다.\n");
        } else if (answer.equals("취소")) {
            //nothing
        } else {
            System.out.println("잘못 입력하였습니다. 다시 입력해주세요.\n");
            cancelMenu();
        }
    }
}
