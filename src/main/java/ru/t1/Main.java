package ru.t1;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.text.SimpleDateFormat;
import java.util.Base64;

public class Main {
    @Parameter(names = {"--config", "-c"})
    String configPath = "";
    @Parameter(names = {"--last_name", "-ln"})
    String lastName = "";
    @Parameter(names = {"--first_name", "-fn"})
    String firstName = "";
    @Parameter(names = {"--role", "-r"})
    String role = "";
    @Parameter(names = {"--email", "-e"})
    String email = "";

    public static void main(String... argv) {
        Main main = new Main();
        JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(argv);
        main.run();
    }

    public void run() {
        try {
//            email = "SDOSemenochkin" +(System.currentTimeMillis() / 1000L) + "@example.ru";
            email = "SDOSemenochkin1720182157@example.ru";
            var apiService = new SignupApiService(configPath, lastName, firstName, role, email);
            apiService.init();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}