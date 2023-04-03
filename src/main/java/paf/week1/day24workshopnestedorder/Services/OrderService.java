package paf.week1.day24workshopnestedorder.Services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import paf.week1.day24workshopnestedorder.Models.OrderDetails;
import paf.week1.day24workshopnestedorder.Models.Orders;
import paf.week1.day24workshopnestedorder.Repositories.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepo;

    @Transactional
    public int placeOrder(OrderDetails orderDetails, Orders order) throws Exception{
        
        final Logger logger= LoggerFactory.getLogger(getClass());

        //! check if order exists by name

        int order_id = 0;
        int rowsinserted =0;
        try{
            List<Orders> orderList = orderRepo.findItemBy("*", "orders", "customer_name", order.getCustomerName(), Orders.class);
            logger.info("list of orders: %s".formatted(orderList.toString()));
            //! create order if does not exist
            if(orderList.size()<=0){
                String valuesList ="'"+order.getOrderDate()+"','"+order.getCustomerName()+"','"+order.getShipAddress()+"','"+order.getNotes()+"',"+order.getTax();
                String fieldList = "order_date, customer_name, ship_address, notes, tax";
                orderRepo.insert("orders", fieldList, valuesList);
                orderList = orderRepo.findItemBy("*", "orders", "customer_name", order.getCustomerName(), Orders.class);
                order_id = orderList.get(0).getId();
            }else{
                order_id = orderList.get(0).getId();
            }
        } catch(Exception ex){
            System.out.println(ex.getMessage());
            throw new Exception("unable to access/create order");
        }
        
        //! add orderdetails
        try{
            String valuesList2=order_id+",'"+orderDetails.getProduct()+"',"+orderDetails.getUnitPrice()+","+orderDetails.getDiscount()+","+orderDetails.getQuantity();       
            String fieldsList2= "order_id, product, unit_price, discount, quantity";
            rowsinserted = orderRepo.insert("order_details", fieldsList2, valuesList2);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
            throw new Exception("unable to insert order details");
        }

        if(rowsinserted<=0){
            throw new Exception("update failed");
        }

        return rowsinserted;        
    }

    
}
