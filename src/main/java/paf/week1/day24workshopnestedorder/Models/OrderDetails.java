package paf.week1.day24workshopnestedorder.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {
    private int id;
    private int orderId;    
    private String product;
    private float unitPrice;
    private float discount=1.0f;
    private int quantity;   
}
