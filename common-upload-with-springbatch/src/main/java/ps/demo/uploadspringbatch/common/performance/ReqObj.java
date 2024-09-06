package ps.demo.uploadspringbatch.common.performance;


import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReqObj {

    private int index;

    private String fileStr;

    private int num;

    private long length;

    private String resultStr;


}
