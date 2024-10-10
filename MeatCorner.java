package Mission.ShoppingListTest;

public class MeatCorner extends FoodSection
{

    static int count = 1;
    int ID;

    @Override
    protected String getName() {
        return name;
    }

    @Override
    protected int getPrice() {
        return price;
    }


    @Override
    protected int getID() {
        return ID;
    }


    MeatCorner()
    {
        this("", 0, "", "", "");
    }


    MeatCorner(String name, int price, String origin, String expirationDate, String manufacture) {
        super(name, price, origin, expirationDate, manufacture);
        this.ID = count++;
    }

    @Override
    public String toString() {
        return  ID + "{" +
                "제품명='" + name + '\'' +
                ", 가격=" + price +
                ", 원산지='" + origin + '\'' +
                ", 유통기한='" + expirationDate + '\'' +
                ", 제조일자='" + manufacture + '\'' +
                '}';
    }
}
