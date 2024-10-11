package Mission.ShoppingListTest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ShoppingListTest
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);


        List<FoodSection> finalShoppingList = new ArrayList<>(); // 쇼핑목록에 담긴 내용

        // 육류 객체 생성 후 리스트에 담기
        List<MeatCorner> meatCorners = new ArrayList<>();
        makeObject(meatCorners, "meat");

        // 야채 객체 생성 후 리스트에 담기
        List<VegetableCorner> vegetableCorners = new ArrayList<>();
        makeObject(vegetableCorners, "vegetable");

        // 과일 객체 생성 후 리스트에 담기
        List<FruitCorner> fruitCorners = new ArrayList<>();
        makeObject(fruitCorners, "fruit");

        System.out.println("     식료품 코너에서 먹을것을 사보자");
        System.out.println("------------식료품 코너의 목록----------------");
        ListOfItems(meatCorners, vegetableCorners, fruitCorners);

        int[] totalPayment = {0}; // 총 결제 금액

        while (true)
        {
            int select;
            System.out.print("이동할 코너(1.정육  2.야채  3.과일), 내가담은제품(4)  최종결제(5)  종료(0) >>");

            try
            {
                select = sc.nextInt();
                sc.nextLine();
            }
            catch (InputMismatchException e)
            {
                System.out.println("옳지 않은 값이 들어왔으므로 안전하게 종료시키겠습니다.");
                select = 0;
            }

            if (select == 0)
            {
                writeData(finalShoppingList);
                sc.close();
                break;
            }




            switch (select)
            {
                case 1:
                {
                    MoveSelectCorner(meatCorners, finalShoppingList, totalPayment,  "정육");
                    break;
                }
                case 2:
                {
                    MoveSelectCorner(vegetableCorners, finalShoppingList, totalPayment, "야채");
                    break;
                }
                case 3:
                {
                    MoveSelectCorner(fruitCorners, finalShoppingList, totalPayment, "과일");
                    break;
                }
                case 4:
                {
                    showMyShopList(finalShoppingList);
                    break;
                }
                case 5:
                {
                    showMyShopList(finalShoppingList);
                    finalPayment(totalPayment);
                    break;
                }
                default:
                {
                    System.out.println("잘못된 입력");
                    break;
                }


            }

        }

    }

    static void finalPayment(int[] totalPayment) // 나의 장바구니와 총 결제 금액을 보여줌
    {
        System.out.println("총 결제 금액 : " + totalPayment[0] + "원 입니다.");
    }


    static void showMyShopList(List<FoodSection> shoppingList)
    {
        System.out.println();
        System.out.println("-------나의 쇼핑 목록------");
        if (shoppingList.isEmpty())
        {
            System.out.println("아무것도 담겨있지 않습니다.");
            return;
        }
        for (FoodSection foodSection : shoppingList)
            System.out.println(foodSection.shopListToString());

        System.out.println();

    }

    static <T extends FoodSection> void MoveSelectCorner(List<T> corners, List<FoodSection> shopList, int[] payment,  String name)
    {

        if (corners.getFirst() instanceof MeatCorner)
        {
            System.out.println(name + "코너에 오신것을 환영합니다!");
            corners.stream().forEach(System.out::println);
        }
        else if (corners.getFirst() instanceof VegetableCorner)
        {
            System.out.println(name + "코너에 오신것을 환영합니다!");
            corners.stream().forEach(System.out::println);
        }
        else if (corners.getFirst() instanceof FruitCorner)
        {
            System.out.println(name + "코너에 오신것을 환영합니다!");
            corners.stream().forEach(System.out::println);
        }
        purchaseItem(corners, shopList, payment);
        System.out.println();


    }

    static <T extends FoodSection> void purchaseItem(List<T> itemList, List<FoodSection> shopList, int[] payment) // 물품 구매
    {
        int select;
        Scanner sc = new Scanner(System.in);
        System.out.print("장바구니에 담으실 아이템의 ID를 입력해주세요 >>");
        try
        {
            select = sc.nextInt();
            sc.nextLine();
        }
        catch (InputMismatchException e)
        {
            System.out.println("옳지 않은 값이 들어왔습니다. 다시 입력해주세요");
            return;
        }

        getInfoSelectItem(itemList, select, shopList, payment);



    }


    //메서드의 매개변수 타입이 List<T>로 선언되어 있음
    // 즉, List 에 담긴 요소가 FoodSection 타입임을 보장해야만  getName() 메서드를 호출할 수 있다.
    static <T extends FoodSection> void getInfoSelectItem(List<T> itemList, int ID, List<FoodSection> finalShopList, int[] payment) // 구매결정한 아이템의 정보 얻기
    {

        for (T item : itemList)
        {
            if (item.getID() == ID)
            { 
                System.out.println(item.getName() + "를 장바구니에 담았습니다.");
                finalShopList.add(item);
                if (finalShopList.size() > 1) CheckDuplicateItems(finalShopList, item);
                payment[0] += item.getPrice();
                return;
            }
        }
        System.out.println("존재하지 않는 아이템 ID 입니다.");
        System.out.println();



    }

    static <T extends FoodSection>void CheckDuplicateItems(List<FoodSection>finalShopList, T item) // 만약 장바구니에 동일한 아이템이 있다면
    {


        for (int i = 0; i < finalShopList.size()-1; i++)
        {
            if (finalShopList.get(i).getName().equals(item.getName()))
            {
                item.setQuantity();
                finalShopList.removeLast();
                break;
            }

        }



    }


    static void writeData(List<FoodSection> shoppingList)
    {
        try(BufferedWriter bfw = new BufferedWriter(new FileWriter("ShopList.txt")))
        {
            for (FoodSection foodSection : shoppingList)
                bfw.write(foodSection + "\n");
        }
        catch (IOException e)
        {
            throw new CustomException("파일을 읽지 못했습니다.");
        }

    }

    static void ListOfItems(List<MeatCorner> meatCornerList, List<VegetableCorner> vegetableCornerList, List<FruitCorner> fruitCornerList)
    {
        System.out.println("정육코너");
        meatCornerList.stream().forEach(System.out::println);
        System.out.println();

        System.out.println("야채코너");
        vegetableCornerList.stream().forEach(System.out::println);

        System.out.println();

        System.out.println("과일코너");
        fruitCornerList.stream().forEach(System.out::println);

        System.out.println();
    }



    static String getNowTime()
    {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yy/MM/dd");

        return now.format(dateTimeFormatter);
    }

    static <T extends FoodSection> void makeObject(List<T> array, String foodType) // 리스트에 넣을 각종 객체들을 여기서 모두 생성한다
    {//T extends Food 를 사용하면, 해당 메서드가 Food 타입이나 그 하위 클래스의 객체만 리스트에 추가될 수 있도록 제한할 수 있다.


        String nowTime = getNowTime(); // 제조일자를 위해 현재시간 받아옴

        // 육류 먼저 넣자
        if (foodType.equals("meat"))
        {
            MeatCorner meat1 = new MeatCorner("삼겹살", 20000, "한국", "24/12/30", nowTime);
            MeatCorner meat2 = new MeatCorner("소고기", 50000, "미국", "24/11/23", nowTime);
            MeatCorner meat3 = new MeatCorner("닭고기", 15000, "브라질", "24/11/02", nowTime);
            array.add((T) meat1);
            array.add((T) meat2);
            array.add((T) meat3);
        }
        else if (foodType.equals("vegetable"))
        {
            // 야채 넣자

            VegetableCorner vegetable1 = new VegetableCorner("상추", 5000, "국내산", "24/10/20", nowTime);
            VegetableCorner vegetable2 = new VegetableCorner("깻잎", 3000, "중국산", "24/10/19", nowTime);
            VegetableCorner vegetable3 = new VegetableCorner("토마토", 5340, "미국산", "24/10/13", nowTime);
            array.add((T) vegetable1);
            array.add((T) vegetable2);
            array.add((T) vegetable3);
        }
        else if (foodType.equals("fruit"))
        {
            // 과일 넣자
            FruitCorner fruit1 = new FruitCorner("사과", 6700, "이탈리아", "24/11/23", nowTime);
            FruitCorner fruit2 = new FruitCorner("바나나", 8000, "중국", "24/11/13", nowTime);
            FruitCorner fruit3 = new FruitCorner("파인애플", 10000, "몽골", "24/10/22", nowTime);
            array.add((T) fruit1);
            array.add((T) fruit2);
            array.add((T) fruit3);
        }

    }

}
