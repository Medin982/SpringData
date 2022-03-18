package com.example.mappingex;

import com.example.mappingex.Exceptions.ValidationExceptions;
import com.example.mappingex.Service.ExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private ExecutorService executorService;

    @Autowired
    public ConsoleRunner(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String date = scan.nextLine();
        while (!date.equals("END")) {
            String command = date.split("\\|")[0];
            String output = null;
            try {
                output = executorService.execute(command, date);
            } catch (ValidationExceptions e) {
                output = e.getMessage();
            }
            System.out.println(output);
            date = scan.nextLine();
        }
    }
}
