package ps.demo.mybatchupload.repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import ps.demo.mybatchupload.entity.Order;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
