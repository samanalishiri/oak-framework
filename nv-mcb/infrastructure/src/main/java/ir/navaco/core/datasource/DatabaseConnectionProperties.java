package ir.navaco.core.datasource;

/**
 * @author Saman Alishiri
 */
public enum DatabaseConnectionProperties implements Valuable<String> {
    DRIVER("jdbc.driver"),
    USERNAME("username"),
    PASSWORD("password"),
    URL("url"),
    MAX_IDL("maxIdl"),
    MIN_IDL("minIdl"),
    ;
    private final String value;

    DatabaseConnectionProperties(String value) {
        this.value = value;
    }

    @Override
    public String value() {
        return value;
    }
}
