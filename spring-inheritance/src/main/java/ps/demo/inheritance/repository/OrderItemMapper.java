package ps.demo.inheritance.repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import ps.demo.inheritance.entity.OrderItem;

@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {

}
