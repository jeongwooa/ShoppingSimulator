package Mission.ShoppingListTest;

public class MeatCorner extends FoodSection
{

    static int IDCount = 1;
    int ID;

    int quantity = 1;

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

    @Override
    protected int getQuantity() {
        return quantity;
    }

    @Override
    protected void setQuantity()
    {
        this.quantity++;
    }


    MeatCorner()
    {
        this("", 0, "", "", "");
    }


    MeatCorner(String name, int price, String origin, String expirationDate, String manufacture) {
        super(name, price, origin, expirationDate, manufacture);
        this.ID = IDCount++;
    }

    @Override
    public String toString()
    {
        return  ID + "{" +
                "제품명='" + name + '\'' +
                ", 가격=" + price +
                ", 원산지='" + origin + '\'' +
                ", 유통기한='" + expirationDate + '\'' +
                ", 제조일자='" + manufacture + '\'' +
                '}';
    }


}
