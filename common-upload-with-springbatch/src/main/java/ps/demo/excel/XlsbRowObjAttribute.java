package ps.demo.excel;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface XlsbRowObjAttribute {

    // Column name will be: A, B, C...
    String columnName() default "A";

    String format() default "";


}
