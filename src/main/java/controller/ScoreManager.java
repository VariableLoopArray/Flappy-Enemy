package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;


public class ScoreManager {
    private static final String FILE_NAME = "scores.txt";
    private static final SimpleDateFormat formatter =
            new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss");

    // Comparer les scores pour les mettre en ordre
    private static class Score implements Comparable<Score> {
        Date date;
        int score;

        public Score (Date date, int score) {
            this.date = date;
            this.score = score;
        }

        @Override
        public int compareTo (Score o) {
            return o.score - this.score;
        }
    }

    // Méthode qui va mettre à jour les scores dans l'ordre
    public static void updateScore (int score) {
        ArrayList<Score> scores = readScores();
        scores.add(new Score(new Date(), score));
        Collections.sort(scores);
        writeScore(scores);
    }

    private static ArrayList<Score> readScores () {
        ArrayList<Score> scores = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME));
            String score;
            while ((score = bufferedReader.readLine()) != null) {
                String date = bufferedReader.readLine();
                bufferedReader.readLine();

                String[] parts = score.split(": ");
                if (parts.length != 2) {
                    continue;
                }
                int scoreValue = Integer.parseInt(parts[1]);

                parts = date.split(": ");
                if (parts.length != 2) {
                    continue;
                }
                String dateString = parts[1];
                Date dateValue = formatter.parse(dateString);
                scores.add(new Score(dateValue, scoreValue));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return scores;
    }

    private static void writeScore (ArrayList<Score> scores) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_NAME);
            for (Score score : scores) {
                fileWriter.write("Score: " + score.score + "\n");
                fileWriter.write("Date: " + formatter.format(score.date) + "\n");
                fileWriter.write("_______________________________\n");
                fileWriter.flush();
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    // Rechercher le score le plus élevé
    public static int getHighestScore(){
        ArrayList<Score> scores = readScores();
        if(!scores.isEmpty()) {
            return  scores.get(0).score;
        }
        return 0;
    }

}
