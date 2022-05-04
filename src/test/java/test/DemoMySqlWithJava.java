package test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.Model.loginRes;
import utilities.dbconnection.DBConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static io.restassured.RestAssured.given;
import static utilities.links.LinksAndAccount.*;

@SuppressWarnings("ALL")
public class DemoMySqlWithJava {
    static String token;
    DBConfiguration dbConfiguration;

    @BeforeMethod
    public void init() {
        dbConfiguration = new DBConfiguration();
    }

    @Test
    public void getAccessToken() throws SQLException {

        RestAssured.baseURI = DOMAIN;
        Response loginRes = given()
                .headers("Content-Type", CONTENT_TYPE,
                        "Accept-Language", ACCEPT_LANGUAGE,
                        "x-functions-key", X_FUNCTIONS_KEY)
                .when()
                .body(LOGIN_BODY)
                .post(LOGIN_PATH);

        loginRes resAPI = new loginRes();
        resAPI.setCompanyId(loginRes.jsonPath().getInt("companyid"));
        resAPI.setCompanyName(loginRes.jsonPath().getString("companyname"));
        resAPI.setUserLevel(loginRes.jsonPath().getInt("userlevel"));

        // Connect DB and get user information
        Connection connection = dbConfiguration.createConnection();
        String query = "SELECT mu.company_id, mc.company_name, mu.user_level FROM MT_user mu \n" +
                "JOIN MT_company mc \n" +
                "ON mu.company_id = mc.company_id \n" +
                "WHERE user_id = '" + LOGIN_USER + "' ";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        loginRes resSQL = new loginRes();

        while (resultSet.next()) {
            resSQL.setCompanyId(resultSet.getInt("company_id"));
            resSQL.setCompanyName(resultSet.getString("company_name"));
            resSQL.setUserLevel(resultSet.getInt("user_level"));
        }

        // Compare user information from API and SQL
        Assert.assertEquals(resSQL,resAPI);

        // Get token for others API
        token = loginRes.jsonPath().getString("token");
    }

    @Test
    public void getThresholdAPI() {
        Response getThresholdRes = given()
                .headers("Content-Type", CONTENT_TYPE,
                        "Accept-Language", ACCEPT_LANGUAGE,
                        "x-functions-key", X_FUNCTIONS_KEY,
                        "token", token)
                .when()
                .queryParam("factoryid", 1)
                .queryParam("lineid", 1)
                .queryParam("machineid", 2)
                .queryParam("sensorid", 12345678)
                .get(GET_THRESHOLD_PATH);
        getThresholdRes.prettyPrint();
    }

}
