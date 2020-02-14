import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

        String conName = "jdbc:postgresql://localhost:5432/diary";
        try(Connection con = DriverManager.getConnection(conName, "postgres", "AcademyMY")) {

            addToDatabase(con, "Aihe1", "kertaa ihan kaikki.");

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
}
