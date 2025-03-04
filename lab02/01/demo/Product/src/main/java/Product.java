
public class Product {
    // 唯一 id
    private String id;
    // 产品名称
    private String name;
    // 产品图片链接
    private String pictureUrl;
    // 产品描述
    private String description;
    // 产品价格
    private double price;

    // 添加 getPrice 方法
    public double getPrice() {
        return price;
    }


  

    public void setPrice(double d) {
        price = d;
    }
}
