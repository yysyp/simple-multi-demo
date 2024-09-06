package ps.demo.amoduletemplate.repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import ps.demo.amoduletemplate.entity.UploadDownloadExcel;

@Mapper
public interface UploadDownloadMapper extends BaseMapper<UploadDownloadExcel> {

}
