package ps.demo.excel;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.binary.XSSFBSheetHandler;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.usermodel.XSSFComment;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Data
public class XlsbXSSFSheetContentHandler<T> implements XSSFSheetXMLHandler.SheetContentsHandler {

    private List<T> list = new ArrayList<>();

    private Class<T> clazz;

    private T data = null;

    private Map<String, Field> fieldColumnMap = new HashedMap();

    private int headerRow;

    public XlsbXSSFSheetContentHandler(Class<T> clazz, int headerRow) {
        this.clazz = clazz;
        this.headerRow = headerRow;
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(XlsbRowObjAttribute.class)) {
                fieldColumnMap.put(field.getAnnotation(XlsbRowObjAttribute.class).columnName(), field);
            }
        }
    }

    @Override
    public void startRow(int i) {
        if (i >= headerRow) {
            try {
                data = clazz.getDeclaredConstructor().newInstance();
                Field field = fieldColumnMap.get(ExcelParsingConstant.ROW_NUM);
                if (field == null) {
                    return;
                }
                field.setAccessible(true);
                field.set(data, i + 1);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public void endRow(int i) {
        if (data != null) {
            list.add(data);
        }
    }

    @Override
    public void cell(String cellName, String cellValue, XSSFComment xssfComment) {
        if (data == null || StringUtils.isBlank(cellValue)) {
            return;
        }

        Pattern pattern = Pattern.compile("[A-Z]+");
        Matcher matcher = pattern.matcher(cellName);
        String column = null;
        if (matcher.find()) {
            column = matcher.group();
        }
        Field field = fieldColumnMap.get(column);
        if (field == null) {
            return;
        }

        field.setAccessible(true);
        try {
            field.set(data, convertToType(field, cellValue));
        } catch (Exception e) {
            StringBuilder msg = new StringBuilder("Error when parsing Row: ");
            msg.append(getRowNum());
            msg.append(" , Column: ");
            msg.append(column);
            msg.append(" , Message: ");
            msg.append(e.getMessage());
            throw new RuntimeException(msg.toString());
        }

    }

    @SneakyThrows
    private String getRowNum() {
        Field rowNumField = fieldColumnMap.get(ExcelParsingConstant.ROW_NUM);
        if (rowNumField != null) {
            rowNumField.setAccessible(true);
            Object obj = rowNumField.get(data);
            return obj == null ? "" : obj.toString();
        }
        return "";
    }

    private Object convertToType(Field field, String value) throws ParseException {
        if (value == null) {
            return null;
        }
        Class<?> fieldType = field.getType();
        if (!String.class.isAssignableFrom(fieldType) && StringUtils.isBlank(value)) {
            return null;
        }
        if (String.class.isAssignableFrom(fieldType)) {
            return value;
        } else if (Date.class.isAssignableFrom(fieldType)) {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value);
            return date;
//            String fmt = field.getAnnotation(XlsbRowObjAttribute.class).format();
//            SimpleDateFormat sdf = new SimpleDateFormat(fmt);
//            sdf.setLenient(false);
//            return sdf.parse(value);
        } else if (BigDecimal.class.isAssignableFrom(fieldType)) {
            return new BigDecimal(regulateNumber(value));
        } else if (double.class.isAssignableFrom(fieldType) || Double.class.isAssignableFrom(fieldType)) {
            return Double.parseDouble(regulateNumber(value));
        } else if (float.class.isAssignableFrom(fieldType) || Float.class.isAssignableFrom(fieldType)) {
            return Float.parseFloat(regulateNumber(value));
        } else if (long.class.isAssignableFrom(fieldType) || Long.class.isAssignableFrom(fieldType)) {
            return Long.parseLong(regulateNumber(value));
        } else if (int.class.isAssignableFrom(fieldType) || Integer.class.isAssignableFrom(fieldType)) {
            return Integer.parseInt(regulateNumber(value));
        } else if (boolean.class.isAssignableFrom(fieldType) || Boolean.class.isAssignableFrom(fieldType)) {
            return Boolean.parseBoolean(value.trim());
        } else if (short.class.isAssignableFrom(fieldType) || Short.class.isAssignableFrom(fieldType)) {
            return Short.parseShort(regulateNumber(value));
        } else if (byte.class.isAssignableFrom(fieldType) || Byte.class.isAssignableFrom(fieldType)) {
            return Byte.parseByte(value);
        } else {
            return value;
        }

    }

    private static String regulateNumber(String value) {
        if (value == null) {
            return null;
        }
        value = value.replaceAll(",|\\s", "");
        if (value.endsWith("%")) {
            value = new BigDecimal(value.replace("%", ""))
                    .divide(new BigDecimal(100)).setScale(9, RoundingMode.HALF_UP).toString();
        }
        return value;
    }

}

