

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class OrderItemTest {

    @Test
    public void testComputePrice() {
        Product product = new Product();
        product.setPrice(10.0); 
        OrderItem orderItem = new OrderItem(product, 2);
        double expected = 20.0;
        double actual = orderItem.computePrice();
        assertEquals(expected, actual, 0.001);
    }
}
