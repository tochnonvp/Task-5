package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);


        User vasya = new User("User1", "Lastname1", "user1@mail.ru");
        User petya = new User("User2", "Lastname2", "user2@mail.ru");
        User olga = new User("User3", "Lastname3", "user3@mail.ru");
        User sveta = new User("User4", "Lastname4", "user4@mail.ru");

        Car volvo = new Car("Volvo", 8);
        Car bmw = new Car("BMW", 3);
        Car suzuki = new Car("Volvo", 4);
        Car lada = new Car("BMW", 5);

        userService.add(vasya.setCar(volvo).setUser(vasya));
        userService.add(petya.setCar(bmw).setUser(petya));
        userService.add(olga.setCar(suzuki).setUser(olga));
        userService.add(sveta.setCar(lada).setUser(sveta));


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(user + " " + user.getCar());
        }

        System.out.println(userService.getUserByCar("BMW", 3));


        context.close();


    }
}
