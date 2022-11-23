package Database;

public abstract class Table {

    protected static class Column<T> {
        public T value;
        
        @Override
        public String toString() {
            return value.toString();
        }
    }
}
