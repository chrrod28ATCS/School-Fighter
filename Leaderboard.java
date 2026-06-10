import java.io.*;
import java.util.*;
import java.time.LocalDate;

public class Leaderboard {
    private static int[] lb = new int[10];
    private static String[] dates = new String[10];
    private static int count = 0;
    private static final String FILE_NAME = "lb.txt";

    public static void addScore(int score) {
        if (score < 1000) return;
        
        String date = LocalDate.now().toString();

        if (count < lb.length) {
            lb[count] = score;
            dates[count] = date;
            count++;
        } else if (score > lb[count-1]) {
            lb[count-1] = score;
            dates[count-1] = date;
        }

        sortScores();
        saveScores();
    }

    private static void sortScores() {
        for (int i = 0; i < count - 1; i++) {
            for (int j = i + 1; j < count; j++) {
                if (lb[j] > lb[i]) {
                    int tempS = lb[i];
                    lb[i] = lb[j];
                    lb[j] = tempS;
                    
                    String tempD = dates[i];
                    dates[i] = dates[j];
                    dates[j] = tempD;
                }
            }
        }
    }
    
    public static void loadScores() {
        try {
            File file = new File(FILE_NAME);
    
            if (!file.exists()) return;
            
            Scanner scanner = new Scanner(file);
            count = 0;
    
            while (scanner.hasNextLine() && count < lb.length) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                lb[count] = Integer.parseInt(parts[0]);
                dates[count] = parts[1];
                
                count++;
            }
    
            scanner.close();
            sortScores();
        } catch (Exception e) {System.out.println("Could not load leaderboard.");}
    }
    
    public static void saveScores() {
        try {
            PrintWriter writer = new PrintWriter(FILE_NAME);
    
            for (int i = 0; i < count; i++) writer.println(lb[i] + "," + dates[i]);
    
            writer.close();
        } catch (Exception e) {System.out.println("Could not save leaderboard.");}
    }

    public static int[] getLB() {return lb;}
    
    public static String[] getDates() {return dates;}

    public static int getCount() {return count;}
}