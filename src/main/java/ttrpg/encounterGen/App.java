package ttrpg.encounterGen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ttrpg.encounterGen.ui.Controller;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        var context = SpringApplication.run(App.class, args);

        Controller controller = context.getBean(Controller.class);
        controller.run();
    }
}
