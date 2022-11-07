package ReflectionAPI;

import ReflectionAPI.Annotations.Column;
import ReflectionAPI.Annotations.Table;

import java.util.LinkedHashMap;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class AnnotationHandler {
    protected static String getTableName(Class<?> clazz) {
        try {
            Table table = clazz.getAnnotation(Table.class);

            return table.value();
        }
        catch (NullPointerException e) {
            throw new NullPointerException(clazz + " dont annotated with @Table");
        }
    }

    protected static LinkedHashMap<String, DataType> getTableColumns(Class<?> clazz) {

        LinkedHashMap<String, DataType> tableColumns = new LinkedHashMap<>();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {

            Annotation[] annotations = field.getDeclaredAnnotations();

            for (Annotation annotation : annotations) {

                if (annotation.annotationType().equals(Column.class)) {

                    Column column = field.getAnnotation(Column.class);

                    if (!column.name().equals("")) {
                        tableColumns.put(column.name(), getFieldType(field));
                    }
                    else {
                        tableColumns.put(field.getName(), getFieldType(field));
                    }
                }
            }
        }
        return tableColumns;
    }

    protected static List<Object> getFieldsValues(Object object) {

        Field[] fields = object.getClass().getDeclaredFields();
        List<Object> valuesList = new ArrayList<>();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                try {
                    field.setAccessible(true);

                    valuesList.add(field.get(object));

                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return valuesList;
    }

    protected static DataType getFieldType(Field field) {
        return switch (field.getType().getSimpleName()) {
            case "int" -> DataType.INTEGER;
            case "String", "Enum" -> DataType.TEXT;
            default -> throw new RuntimeException("no datatype " + field.getType() + " yet");
        };
    }
}
