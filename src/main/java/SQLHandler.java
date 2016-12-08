
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Created by oerlex on 07.12.16.
 */
public class SQLHandler {

    private static JSONParser jsonParser;
    private Connection connect = null;
    PreparedStatement preparedStatement = null;

    public int importIntoSQL() throws Exception {
        jsonParser = new JSONParser();


        int status = 0;
        try {
            // Load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");

            // DB connection setup
            connect = DriverManager.getConnection("jdbc:mysql://139.59.131.10/reddit" , "alex","hammy");

            // PreparedStatements
            preparedStatement = connect.prepareStatement("insert into Reddit values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            System.out.println(System.getProperty("user.dir"));
            Object obj = jsonParser.parse(new FileReader("src/data2"));
            JSONObject jsonObject = (JSONObject) obj;

            String id = (String) jsonObject.get("id");
            preparedStatement.setString(1, id);

            String parent_id = (String) jsonObject.get("parent_id");
            preparedStatement.setString(2, parent_id);

            String link_id = (String) jsonObject.get("link_id");
            preparedStatement.setString(3, link_id);

            String name = (String) jsonObject.get("name");
            preparedStatement.setString(4, name);

            String author = (String) jsonObject.get("author");
            preparedStatement.setString(5, author);

            String body = (String) jsonObject.get("body");
            preparedStatement.setString(6, body);

            String subreddit_id = (String) jsonObject.get("subreddit_id");
            preparedStatement.setString(7, subreddit_id);

            String subreddit = (String) jsonObject.get("subreddit");
            preparedStatement.setString(8, subreddit);

            long score = (Long) jsonObject.get("score");
            preparedStatement.setLong(9, score);

            String create_utc = (String) jsonObject.get("create_utc");
            preparedStatement.setString(10, create_utc);

            status = preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (connect != null) {
                    connect.close();
                }

            } catch (Exception e) {

            }
        }
        return status;
    }
}
