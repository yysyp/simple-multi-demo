package ps.demo.uploadspringbatch.excel;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.ExcelStyleDateFormatter;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomizedDataFormatter extends DataFormatter {

    @Override
    public String formatRawCellContents(double value, int formatIndex, String formatString, boolean use1904Windowing) {

        if (DateUtil.isADateFormat(formatIndex, formatString)) {
            Format numberFormat;
            if (DateUtil.isValidExcelDate(value)) {

                Date d = DateUtil.getJavaDate(value, use1904Windowing);
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
            }

        }

        return String.valueOf(value);

    }

}
