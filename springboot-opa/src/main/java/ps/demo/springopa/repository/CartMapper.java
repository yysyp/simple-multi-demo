package ps.demo.springopa.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ps.demo.springopa.entity.Cart;

@Mapper
public interface CartMapper extends BaseMapper<Cart> {

    Cart getCartAndItems(@Param("id") Long id);

}
