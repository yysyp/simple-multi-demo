package ps.demo.commonlibx.protobuf.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ps.demo.commonlibx.common.BaseController;
import ps.demo.commonlibx.common.BaseResponse;
import ps.demo.commonlibx.common.StringDataResponse;
import ps.demo.commonlibx.protobuf.model.Person;


@Slf4j
@RestController
@RequestMapping("/protobuf-person")
public class ProtoBufPersonController extends BaseController {


    @GetMapping("/get")
    public BaseResponse findPerson() {


        return StringDataResponse.successWithData(Person.newBuilder()
                .setId(10000)
                .setEmail("testprotobuf@aa.com")
                .setName("xiaohaha")
//                .setNickname(0, "xiao")
//                .setNickname(1, "pi")
                .build());
    }




}
