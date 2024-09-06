package ps.demo.inheritance.dto;

import lombok.*;
import ps.demo.inheritance.common.BaseResponse;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderResponse extends BaseResponse {

    private Data data;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    @Builder
    public static class Data {
        private Long orderId;
        private BigDecimal total;
        private String status;
    }

}
