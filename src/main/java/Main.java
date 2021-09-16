import controller.*;
import model.dao.Configuration;
import model.dto.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.DurationType;
import view.ui.MovieUi;
import view.ui.UserUi;

import javax.persistence.Query;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

public class Main {
    public static void main(String[] args) {

        UserUi userUi = new UserUi();
        userUi.logIn();
    }
}
