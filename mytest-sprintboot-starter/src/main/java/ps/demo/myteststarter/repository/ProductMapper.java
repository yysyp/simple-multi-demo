package ps.demo.myteststarter.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import ps.demo.myteststarter.entity.Product;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {

}
