package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static String city = "???";
    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT);) {// порт можете выбрать любой в доступном диапазоне 0-65536. Но чтобы не нарваться на уже занятый - рекомендуем использовать около 8080

            while (true) {

                try (Socket clientSocket = serverSocket.accept(); // ждем подключения
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ) {



                    if (city == "???") {
                        out.println("Введите город");
                        city = in.readLine();
                        out.println("ОК");
                    } else {

                        out.println(city);
                        String newCity = in.readLine();
                        if (city.charAt(city.length()-1) == newCity.charAt(0)) {
                            city = newCity;
                            out.println("ОК");
                        } else {
                            out.println("NOT OK");
                        }
                    }

                    out.println("Введите имя: ");
                    System.out.println("New connection accepted");
                    final String name = in.readLine();

                    out.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));
                }

            }

        }


    }



}