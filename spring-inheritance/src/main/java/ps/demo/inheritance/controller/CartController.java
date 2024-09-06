package ps.demo.inheritance.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ps.demo.inheritance.common.BaseController;
import ps.demo.inheritance.common.ClientErrorException;
import ps.demo.inheritance.common.CodeEnum;
import ps.demo.inheritance.dto.*;
import ps.demo.inheritance.dto.*;
import ps.demo.inheritance.service.CartService;
import ps.demo.inheritance.dto.*;

@Tag(name = "CartController", description = "CartController")
@RestController
@RequestMapping("/cart")
public class CartController extends BaseController {

    @Autowired
    private CartService cartService;

    @Operation(summary = "Cart to get basic info and its detail items")
    @GetMapping(value = "/detail", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetCartResponse cartDetail(@RequestParam(value = "userId") Long userId,
                                      @RequestParam(value = "cartId") Long cartId) {
        if (userId.compareTo(0L) <= 0 || cartId.compareTo(0L) <= 0) {
            throw new ClientErrorException(CodeEnum.INVALID_ID);
        }
        GetCartResponse response = cartService.getCartDetail(userId, cartId);
        return response;
    }

    @Operation(summary = "Cart to additional add product to cart")
    @PostMapping("/add")
    public AddToCartResponse addToCart(@RequestBody @Validated AddToCartDto req) {
        Long cartId = cartService.addToCart(req.getUserId(), req.getProductId(), req.getQuantity());
        AddToCartResponse response = new AddToCartResponse(
                AddToCartResponse.Data.builder().cartId(cartId).build()
        );
        return response;
    }

    @Operation(summary = "Cart to partially remove cart items from cart")
    @PostMapping("/remove")
    public RemoveFromCartResponse removeFromCart(@RequestBody @Validated RemoveFromCartDto req) {
        Long cartId = cartService.removeFromCart(req.getUserId(), req.getCartId(), req.getProductId(), req.getQuantity());
        RemoveFromCartResponse response = new RemoveFromCartResponse(
                RemoveFromCartResponse.Data.builder().cartId(cartId).build()
        );
        return response;
    }

}

