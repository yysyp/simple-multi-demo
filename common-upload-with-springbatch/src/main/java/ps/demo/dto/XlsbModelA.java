package ps.demo.dto;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ps.demo.excel.ExcelParsingConstant;
import ps.demo.excel.XlsbRowObjAttribute;

import java.util.Date;


@Getter
@Setter
@EqualsAndHashCode
@ToString
public class XlsbModelA {

    @ExcelIgnore //EasyExcel
    @XlsbRowObjAttribute(columnName = ExcelParsingConstant.ROW_NUM) //Xlsb
    private Integer rowNum;

    @ExcelProperty(value = "startDate", index = 0) //EasyExcel
    @XlsbRowObjAttribute(columnName = "A")//, format = "yyyy-MM-dd HH:mm:ss") //Xlsb
    private Date startDate;

    @ExcelProperty(value = "name", index = 1) //EasyExcel
    @XlsbRowObjAttribute(columnName = "B") //Xlsb

    @NotNull(message = "Name should not be null")
    //@Nullable
    //@Positive(message = "age must be positive number")
    //@NotNull(message = "score should not be null")
    //@Digits(integer = 3, fraction = 2, message = "score should in ###.## format")
    private String name;

}
