package utilities.links;

public class LinksAndAccount {
    public static String DOMAIN = "https://dev-cm-api-funcapp-001-cmcj.azurewebsites.net";
    public static String LOGIN_USER = "a.namiki@thk.co.jp";
    public static String LOGIN_PASSWORD = "0120444444";
    public static String LOGIN_PATH = "/api/v3/user/auth/login";
    public static String LOGIN_BODY = "{\"userid\":\"" + LOGIN_USER + "\", \"pass\":\"" + LOGIN_PASSWORD + "\"}";
    public static String CONTENT_TYPE = "application/json";
    public static String ACCEPT_LANGUAGE = "en-EU";
    public static String X_FUNCTIONS_KEY = "xCEEgcw0ttqTaRbhPlsIDndehl3jcI7tfELt1VzXM6xeqdToVP/qGA==";
    public static String GET_THRESHOLD_PATH = "/api/v3/data/threshold";
    public static String DB_HOST = "dev-iot-api-sqlsrv-001.database.windows.net";
    public static String DB_PORT = "1433";
    public static String DB_DATABASE = "dev-cm-api-sqldb-001-cmcj";
    public static String DB_USER = "dbadmin";
    public static String DB_PASSWORD = "hA3p8Jy6";
}
