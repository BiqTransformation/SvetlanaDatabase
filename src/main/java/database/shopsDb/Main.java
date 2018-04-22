package database.shopsDb;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {

        Controller api = new Controller();
        UserInterface ui = new UserInterface(api);
        ui.getChoise();


    }


}
