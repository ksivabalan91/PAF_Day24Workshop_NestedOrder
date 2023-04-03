package paf.week1.day24workshopnestedorder.Controllers;

import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import paf.week1.day24workshopnestedorder.Models.OrderDetails;
import paf.week1.day24workshopnestedorder.Models.Orders;
import paf.week1.day24workshopnestedorder.Services.OrderService;

@Controller
@RequestMapping
public class OrderController {

    @Autowired
    private OrderService orderSvc;

    Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping(path="/order", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> addOrder(@RequestBody MultiValueMap<String,String> form) throws Exception{
        
        OrderDetails orderDetails = new OrderDetails();
        Orders order = new Orders();

        //todo order mapping
        logger.info("setting info into order model");
        order.setCustomerName(form.getFirst("customerName"));
        order.setNotes(form.getFirst("notes"));
        order.setOrderDate(Date.valueOf(form.getFirst("orderDate")));
        order.setShipAddress(form.getFirst("shipAddress"));
        if(null!=form.getFirst("tax")){
            order.setTax(Float.parseFloat(form.getFirst("tax")));
        }
        
        //todo order_details mapping
        logger.info("setting info into orderdetails model");
        orderDetails.setProduct(form.getFirst("product"));        
        orderDetails.setQuantity(Integer.parseInt(form.getFirst("quantity")));
        orderDetails.setUnitPrice(Float.parseFloat(form.getFirst("unitPrice")));
        if(null!=form.getFirst("discount")){
            orderDetails.setDiscount(Float.parseFloat(form.getFirst("discount")));
        }
        
        //todo place order
        logger.info("running orderSvc");
        int rowsUpdated = orderSvc.placeOrder(orderDetails, order);
        
        logger.info("Checking number of rows updated");
        if(rowsUpdated>0){
            return ResponseEntity.ok().body("updated %d rows".formatted(rowsUpdated));
        }else{
            return ResponseEntity.badRequest().body("update failed");
        }
    }

        
}
