package ReflectionAPI;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class TableBuilder {
    private static boolean showQuery = false;

    public static void showQueries(boolean showQuery) {
        TableBuilder.showQuery = showQuery;
    }

    public static void buildTable(Class<?> clazz) {
        String tableName = AnnotationHandler.getTableName(clazz);

        LinkedHashMap<String, DataType> tableColumns = AnnotationHandler.getTableColumns(clazz);

        StringBuilder buildCommand = new StringBuilder("CREATE TABLE " + tableName + " ( ");

        for (Map.Entry<String, DataType> stringTypeEntry : tableColumns.entrySet()) {
            buildCommand.append(stringTypeEntry.getKey()).append(" ").append(stringTypeEntry.getValue()).append(", ");
        }

        buildCommand.deleteCharAt(buildCommand.length() - 2).append(");");

        if (showQuery) {
            System.out.println(buildCommand);
        }

        try (Statement statement = DataSource.getConnection().createStatement()) {
            statement.executeUpdate(buildCommand.toString());
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}