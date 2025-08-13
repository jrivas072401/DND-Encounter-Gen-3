package ttrpg.encounterGen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ttrpg.encounterGen.ui.Controller;

@SpringBootApplication  // enables component scanning and auto-configuration
public class App {
    public static void main(String[] args) {
        var context = SpringApplication.run(App.class, args);

        // Get the Controller bean and start the CLI
        Controller controller = context.getBean(Controller.class);
        controller.run();
    }
}
