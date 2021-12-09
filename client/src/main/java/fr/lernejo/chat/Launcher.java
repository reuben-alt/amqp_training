package fr.lernejo.chat;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class Launcher {
    public static void main(String[] args) {
        RabbitTemplate template = new AnnotationConfigApplicationContext(Launcher.class).getBean(RabbitTemplate.class);
        Scanner scanner = new Scanner(System.in);
        String message = null;
        do {
            if (message != null) {
                template.convertAndSend("chat_messages", message);
                System.out.print("Message sent. ");
            }
            System.out.println("Input a message, we will sent it for you (q for quit)");
            message = scanner.nextLine();
        } while (!message.equals("q") && !message.equals("quit"));
        scanner.close();
        System.exit(0);
    }
}
