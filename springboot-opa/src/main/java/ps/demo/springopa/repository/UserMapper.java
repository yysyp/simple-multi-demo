package ps.demo.springopa.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import ps.demo.springopa.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
