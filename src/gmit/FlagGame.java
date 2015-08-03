package gmit;
import java.util.*;
import javax.swing.JOptionPane;

public class FlagGame {

    public static void main(String[] args) throws Exception {
        countryGame();
    }

    public static void countryGame(){
        int tries = 1;
        int score = 0;
        List<String> flagNames = new ArrayList<String>();
        List<String> selection = new ArrayList<String>();
        List<String> answer = new ArrayList<String>();

        try{
            do{
                fillCountryList(flagNames);
                Collections.shuffle(flagNames);
                do{
                    selection = flagNames.subList(0, 4);
                    // For Testing Purposes
                    // System.out.println("\n\nOriginal Arraylist of Countries\n" + flagNames + " Current Size = " + flagNames.size());
                    // System.out.println("\n\nContents of the 4 Country Selection Arraylist\n" + selection + " Size of List " + selection.size());
                    JOptionPane.showMessageDialog(null, selection);
                    answerMethod(selection, answer);
                    score = userGuess(selection, tries, score);
                    selection.clear(); // Required
                }while(!flagNames.isEmpty());
            }while(tries != 0);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static int userGuess(List<String> selection, int tries, int score){
        String guess;
        
        try{
            guess = JOptionPane.showInputDialog(null, "Please enter you Guess");
            if(guess.equals("")){
                JOptionPane.showMessageDialog(null, "Please Enter a country");
                tries = 1;
            }else{
                for(int i=0; i < selection.size(); i++){
                    if(guess.equals(selection.get(i))){
                        JOptionPane.showMessageDialog(null, "Correct");
                        score = scoreMethod(score);
                        break;
                    }else{
                        JOptionPane.showMessageDialog(null, "Incorrect");
                        tries--;
                        if(tries == 0){
                            JOptionPane.showMessageDialog(null, "Game Over\n You Scored " + score);
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

    public static void answerMethod(List<String> selection, List<String> answer){
        Collections.shuffle(selection);
        JOptionPane.showMessageDialog(null, "Which Flag was " + selection.get(0) + "?");
        // answer = selection.subList(0, 1);
        answer = selection;
    }

    public static int scoreMethod(int score){
        return score += 1;
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