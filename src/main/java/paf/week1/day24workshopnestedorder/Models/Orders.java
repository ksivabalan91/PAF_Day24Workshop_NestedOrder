package paf.week1.day24workshopnestedorder.Models;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

    private int id;    
    private Date orderDate;
    private String customerName;
    private String shipAddress;
    private String notes;
    private float tax = 0.05f;
    private List<OrderDetails> orderDetails;   
}
