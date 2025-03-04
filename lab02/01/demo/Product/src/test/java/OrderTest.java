
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;





public class OrderTest {

    @Test
    public void testComputeTotalPrice() {
        Product product1 = new Product();

        product1.setPrice(10.0); 
        OrderItem orderItem1 = new OrderItem(product1, 2);

        Product product2 = new Product();

        product2.setPrice(20.0); 
        OrderItem orderItem2 = new OrderItem(product2, 3);

        OrderItem[] items = {orderItem1, orderItem2};
        Order order = new Order();

        order.setItems(items); 

        double expected = 20.0 + 60.0;
        double actual = order.computeTotalPrice();
        assertEquals(expected, actual, 0.001);
    }
}
        