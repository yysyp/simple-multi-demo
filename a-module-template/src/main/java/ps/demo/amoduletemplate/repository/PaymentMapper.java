package ps.demo.amoduletemplate.repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import ps.demo.amoduletemplate.entity.Payment;

@Mapper
public interface PaymentMapper extends BaseMapper<Payment> {

}
