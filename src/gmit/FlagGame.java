package gmit;
import java.util.*;
import javax.swing.JOptionPane;

public class FlagGame {
    private static long startTime;
    private static long timeElapsed;
    private static List<String> flagNames = new ArrayList<String>();
    private static List<String> selection = new ArrayList<String>();

    public static void main(String[] args) throws Exception {
        countryGame();
    }

    public static void countryGame(){
        int tries = 1;
        int score = 0;
        int answer = 0;

        try{
            startTime = System.currentTimeMillis() / 1000; // Start Timer
            do{
                fillCountryList(flagNames);
                Collections.shuffle(flagNames);
                do{
                    selection = flagNames.subList(0, 4);
                    // Uncomment for Testing
                        // testPrint(flagNames, selection);
                    // End of testing
                    JOptionPane.showMessageDialog(null, selection);
                    answerMethod(selection, answer);
                    score = userGuess(selection, tries, score, answer);
                    selection.clear(); // Required
                }while(!flagNames.isEmpty());
            }while(tries != 0);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static int userGuess(List<String> selection, int tries, int score, int answer){
        String guess;

        try{
            guess = JOptionPane.showInputDialog(null, "Please enter you Guess");
            if(guess.equals("")){
                JOptionPane.showMessageDialog(null, "Please Enter a country");
                tries = 1;
            }else{
                for(int i=0; i < selection.size(); i++){
                    if(selection.contains(guess)){
                        JOptionPane.showMessageDialog(null, "Correct");
                        score = scoreMethod(score);
                        break;
                    }else{
                        JOptionPane.showMessageDialog(null, "Incorrect");
                        tries--;
                        if(tries == 0){
                            timeElapsed = System.currentTimeMillis()/1000 - startTime; // End timer
                            JOptionPane.showMessageDialog(null, "Game Over\n You Scored " + score + "\nIn a time of " + timeElapsed + " seconds");
                            System.exit(0);
                        }
                        return tries;
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return score;
    }

    public static void answerMethod(List<String> selection, int answer){
        Random rnd = new Random();
        int index = rnd.nextInt(selection.size());
        String str = selection.get(index);
        int result = selection.indexOf(str) + 1;
        JOptionPane.showMessageDialog(null, "Which Country was Number " + result + "?");
    }

    public static int scoreMethod(int score){
        return score += 1;
    }

    public static void testPrint(List<String> flagNames, List<String> selection){
        System.out.println("\n\nOriginal Arraylist of Countries\n" + flagNames + " Current Size = " + flagNames.size());
        System.out.println("\n\nContents of the 4 Country Selection Arraylist\n" + selection + " Size of List " + selection.size());
    }

    public static void fillCountryList(List<String> flagNames){
        flagNames.add("Ireland");
        flagNames.add("England");
        flagNames.add("Germany");
        flagNames.add("Wales");
        flagNames.add("Scotland");
        flagNames.add("France");
        flagNames.add("Belgium");
        flagNames.add("Holland");
        flagNames.add("Spain");
        flagNames.add("Portugal");
        flagNames.add("Poland");
        flagNames.add("Czech Rep");
        flagNames.add("Slovakia");
        flagNames.add("Austria");
        flagNames.add("Monaco");
        flagNames.add("Hungary");
        flagNames.add("Romania");
        flagNames.add("Moldova");
        flagNames.add("Italy");
        flagNames.add("Greece");
        flagNames.add("Albania");
        flagNames.add("Bulgaria");
        flagNames.add("Russia");
        flagNames.add("Ukraine");
        flagNames.add("Iceland");
        flagNames.add("Sweden");
        flagNames.add("Denmark");
        flagNames.add("Finland");
    }
}