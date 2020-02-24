/*
Ohjelma ei vielä toimi full stackina. Back toimii omanaan, ei vielä keskustele frontin kanssa.
req.open mm ei vielä määritelty JavaScriptissä. Jdbc toimii omanaan eli tallettaa topiceja tietokantaan
ja hakee sieltä kaikki konsoliin.
 */

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TopicDAO {

    public TopicDAO(){

    }

    public void driverLoad() {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Ajuri ladattu.");
        } catch (ClassNotFoundException e) {
            System.err.println("Ajurin lataus ei onnistunut." + e.getMessage());
            e.printStackTrace();
            System.exit(2);
        }

        //tämä kommentoitu osa salasanoista on GitHubissa
        //**********     need to add user and password    *************
        String conName = "jdbc:postgresql://localhost:5432/diary";
        try(Connection con = DriverManager.getConnection(conName, user, password)) {


            addToDatabase(con, "Aihe1", "kertaa ihan kaikki.");
            addToDatabase(con, "Aihe2", "kertaa kaikki.");
            addToDatabase(con, "Aihe3", "kertaa.");
            System.out.println(listTopics(con));

        } catch (SQLException e) {
            System.out.println("SQL exception " + e.getMessage());
            e.printStackTrace();
        }

    }

    public void addToDatabase (Connection con, String title, String descr) throws SQLException {
        String sql = "insert into topic (title, description) values (?,?)";
        try(PreparedStatement stat = con.prepareStatement(sql)) {
            stat.setString(1, title);
            stat.setString(2, descr);
            int i = stat.executeUpdate();
            if(i == 1) {
                System.out.println("Aihe \"" + title + "\" lisätty tietokantaan.");
            } else {
                System.out.println("Lisäys ei onnistunut.");
            }
        }
    }

    public List<Topic> listTopics(Connection con) throws SQLException {
        String sql = "select id, title, description from topic";
        ResultSet rs;
        List<Topic> topics = new ArrayList<>();
        try(PreparedStatement stat = con.prepareStatement(sql)) {
            rs = stat.executeQuery();
            while(rs.next()) {
                Topic top = new Topic(rs.getString("title"), rs.getString("description"));
                topics.add(top);
            }

        } catch (SQLException e) {
            System.out.println("ääskuuäl exception");
            e.printStackTrace();
        }
        return topics;
    }
}
