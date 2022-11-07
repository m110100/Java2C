package ReflectionAPI;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public abstract class Session {

    public static void persist(Object object) {
        try (Statement statement = DataSource.getConnection().createStatement()){
            System.out.println(buildCommand(object));

            statement.executeUpdate(buildCommand(object));
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static String buildCommand(Object object) {
        List<Object> values = AnnotationHandler.getFieldsValues(object);

        LinkedHashMap<String, DataType> tableColumns = AnnotationHandler.getTableColumns(object.getClass());

        List<String> fields = new ArrayList<>(tableColumns.keySet());

        String tableName = AnnotationHandler.getTableName(object.getClass());

        StringBuilder buildCommand = new StringBuilder("INSERT INTO " + tableName + " ( ");

        for (String field : fields) {
            buildCommand.append(field).append(", ");
        }

        buildCommand.deleteCharAt(buildCommand.length() - 2).append(") VALUES ( ");

        for (Object value : values) {
            if (value instanceof String || value instanceof Enum) {
                buildCommand.append("'").append(value).append("'").append(", ");
            }
            else {
                buildCommand.append(value).append(", ");
            }
        }

        buildCommand.deleteCharAt(buildCommand.length() - 2).append(");");

        return buildCommand.toString();
    }
}
