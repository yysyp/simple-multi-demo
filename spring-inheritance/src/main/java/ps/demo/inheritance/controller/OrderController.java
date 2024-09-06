package ps.demo.inheritance.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ps.demo.inheritance.common.BaseController;
import ps.demo.inheritance.dto.PlaceOrderRequest;
import ps.demo.inheritance.dto.PlaceOrderResponse;
import ps.demo.inheritance.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    @Operation(summary = "Order to place order for current user's cart")
    @PostMapping("/place-order")
    public PlaceOrderResponse placeOrder(@RequestBody @Validated PlaceOrderRequest request) {


        PlaceOrderResponse response = orderService.placeOrder(request);
        return response;

    }

}
