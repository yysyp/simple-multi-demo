package ps.demo.springopa.repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import ps.demo.springopa.entity.Payment;

@Mapper
public interface PaymentMapper extends BaseMapper<Payment> {

}
