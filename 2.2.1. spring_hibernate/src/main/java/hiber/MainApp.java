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

      User user1 = new User("John", "Smith", "user1@mail.ru");
      User user2 = new User("Dave", "Song", "user2@mail.ru");
      User user3 = new User("Karen", "Zabrisky", "user3@mail.ru");
      User user4 = new User("Alex", "Black", "user4@mail.ru");

      Car car1 = new Car("Lada", 1);
      Car car2 = new Car("Kia", 2);
      Car car3 = new Car("Audi", 3);
      Car car4 = new Car("Ferrari", 4);

      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));
      userService.add(user4.setCar(car4).setUser(user4));

      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
      }

      System.out.println(userService.getUserByCar("Kia", 2));

      try {
         User notFoundUser = userService.getUserByCar("FAW", 44);
      } catch (NoResultException e) {
         System.out.println("User not found");
      }

      context.close();
   }
}