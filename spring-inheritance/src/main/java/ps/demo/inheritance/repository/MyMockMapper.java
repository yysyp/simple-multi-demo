package ps.demo.inheritance.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import ps.demo.inheritance.entity.MyMock;

@Mapper
public interface MyMockMapper extends BaseMapper<MyMock> {

}
