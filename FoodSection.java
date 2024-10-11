package Mission.ShoppingListTest;

public abstract class FoodSection
{

    protected String name;
    protected int price;
    protected String origin; // 원산지
    protected String expirationDate; // 유통기한
    protected String manufacture; // 제조일자


    protected abstract String getName();

    protected abstract int getPrice();

    protected abstract int getID();

    protected abstract int getQuantity();

    protected abstract void setQuantity();

    public String shopListToString()
    {
        return  getID() + "{" +
                "제품명='" + name + '\'' +
                ", 가격=" + price +
                ", 담은갯수='" + getQuantity() +
                '}';
    }



    FoodSection(String name, int price, String origin, String expirationDate, String manufacture)
    {
        this.name = name;
        this.price = price;
        this.origin = origin;
        this.expirationDate = expirationDate;
        this.manufacture = manufacture;
    }

}
