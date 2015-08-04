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
        String str = "";

        try{
            welcomeMessage();
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
                    // answerMethod(selection, answer, str);
                    score = userGuess(selection, tries, score, answer, str);
                    selection.clear(); // Required
                }while(!flagNames.isEmpty());
            }while(tries != 0);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static int userGuess(List<String> selection, int tries, int score, int answer, String str){
        String guess;
        Random rnd = new Random();
        int index = rnd.nextInt(selection.size());
        str = selection.get(index);
        answer = selection.indexOf(str);
        JOptionPane.showMessageDialog(null, "Which Country was Number " + (answer+1) + "?");

        try{
            guess = JOptionPane.showInputDialog(null, "Please enter you Guess");
            if(guess.equals("")){
                JOptionPane.showMessageDialog(null, "Please Enter a country");
                tries = 1;
            }else{
                for(int i=0; i < selection.size(); i++){
                    if(str.equalsIgnoreCase(guess)){
                        JOptionPane.showMessageDialog(null, "Correct");
                        score = scoreMethod(score);
                        break;
                    }else{
                        JOptionPane.showMessageDialog(null, "Incorrect");
                        tries--;
                        if(tries == 0){
                            timeElapsed = System.currentTimeMillis()/1000 - startTime; // End timer
                            JOptionPane.showMessageDialog(null, "GAME OVER\nThe Correct Answer was....\n" + str.toUpperCase()
                                                              + "\nYou Scored " + score
                                                              + "\nIn a time of " + timeElapsed + " seconds");
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

//    public static void answerMethod(List<String> selection, int answer, String str){
//        Random rnd = new Random();
//        int index = rnd.nextInt(selection.size());
//        str = selection.get(index);
//        answer = selection.indexOf(str);
//        JOptionPane.showMessageDialog(null, "Which Country was Number " + answer + "?");
//    }

    public static int scoreMethod(int score){
        return score += 1;
    }

    public static void welcomeMessage(){
        JOptionPane.showMessageDialog(null, "WELCOME\nPlease Choose the Correct Country\nfrom each list");
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
        flagNames.add("Andorra");
        flagNames.add("Armenia");
        flagNames.add("Azerbaijan");
        flagNames.add("Belarus");
        flagNames.add("Bosnia");
        flagNames.add("Croatia");
        flagNames.add("Cyprus");
        flagNames.add("Estonia");
        flagNames.add("Georgia");
        flagNames.add("Kazakhstan");
        flagNames.add("Kosovo");
        flagNames.add("Latvia");
        flagNames.add("Liechtenstein");
        flagNames.add("Lithuania");
        flagNames.add("Luxembourg");
        flagNames.add("Macedonia");
        flagNames.add("Malta");
        flagNames.add("Montenegro");
        flagNames.add("Norway");
        flagNames.add("San Marino");
        flagNames.add("Serbia");
        flagNames.add("Slovenia");
        flagNames.add("Switzerland");
        flagNames.add("Turkey");
        flagNames.add("Vatican City");
    }
}