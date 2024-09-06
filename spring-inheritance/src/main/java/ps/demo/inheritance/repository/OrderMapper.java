package ps.demo.inheritance.repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import ps.demo.inheritance.entity.Order;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
