package ps.demo.inheritance.dto;

import lombok.*;
import ps.demo.inheritance.common.BaseResponse;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RemoveFromCartResponse extends BaseResponse {

    private Data data;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    @Builder
    public static class Data {
        private Long cartId;
    }

}
