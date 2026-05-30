import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Controller {

    @FXML
    private TextField txtName;

    @FXML
    private ComboBox<String> comboMovie;

    @FXML
    private ComboBox<String> comboTickets;

    @FXML
    private TextArea txtSummary;

    @FXML
    public void initialize() {

        comboMovie.getItems().addAll(
                "Avengers",
                "Batman",
                "Frozen",
                "Spider-Man",
                "Inside Out"
        );

        comboTickets.getItems().addAll(
                "1",
                "2",
                "3",
                "4",
                "5"
        );
    }

    @FXML
    private void bookTicket() {

        try {

            String name = txtName.getText();
            String movie = comboMovie.getValue();
            String tickets = comboTickets.getValue();

            if(name.isEmpty() || movie == null || tickets == null) {

                txtSummary.setText("Please complete all fields.");
                return;
            }

            Connection connection = DatabaseConnection.connect();

            String sql = "INSERT INTO bookings(customer_name, movie_name, tickets) VALUES (?, ?, ?)";

            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, name);
            pst.setString(2, movie);
            pst.setInt(3, Integer.parseInt(tickets));

            pst.executeUpdate();

            txtSummary.setText(
                    "BOOKING SUCCESSFUL!\n\n" +
                            "Customer: " + name +
                            "\nMovie: " + movie +
                            "\nTickets: " + tickets
            );

            pst.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}