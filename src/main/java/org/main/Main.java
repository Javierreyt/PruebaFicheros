package org.main;

import org.films.Movie;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        File moviesFiles = new File("peliculas.csv");
        ArrayList<Movie> movies = new ArrayList<>();
        try (BufferedReader bfr = new BufferedReader(new FileReader(moviesFiles))) {
            String linea;
            while ((linea = bfr.readLine()) != null) {
                var split = linea.split(",");
                movies.add(new Movie(
                        split[0],
                        split[1],
                        Integer.parseInt(split[2]),
                        split[3],
                        split[4]
                ));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bfr = new BufferedReader(new FileReader("template.html"))) {
            String line;
            while ((line = bfr.readLine()) != null) {
                builder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File dir = new File("salida");
        dir.mkdir();
        for (Movie m : movies) {
            try (BufferedWriter bfw = new BufferedWriter(new FileWriter("salida/" + m.getTitle() + "-" + m.getId() + ".html"))) {
                bfw.write(builder.toString()
                        .replace("%%1%%", m.getId())
                        .replace("%%2%%", m.getTitle())
                        .replace("%%3%%", m.getYear().toString())
                        .replace("%%4%%", m.getDirector())
                        .replace("%%5%%", m.getGenre())
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}