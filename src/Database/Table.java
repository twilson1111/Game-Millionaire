package Database;

public abstract class Table {

    protected static String tableName;

    protected Table(String name) {
        tableName = name;
    }
    
    public static String getTableName() {
        return tableName;
    }

    protected static class Column<T> {

        public Column(String name) {
            this.name = name;
        }

        public final String name;

        public T value;
    }
}
