package ps.demo.springopa.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import ps.demo.springopa.entity.MyMock;

@Mapper
public interface MyMockMapper extends BaseMapper<MyMock> {

}
