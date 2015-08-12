package gmit;
import java.util.*;
import javax.swing.JOptionPane;

public class CountryGuessingGames {
    private static long startTime;
    private static long timeElapsed;
    private static List<String> countryNames = new ArrayList<String>();
    private static List<String> selection = new ArrayList<String>();
    static CountryNames names = new CountryNames();

    public static void main(String[] args) throws Exception {
        countryGame();
    } // End of main method

    public static void countryGame(){
        int tries = 1;
        int score = 0;
        int answer = 0;       
        int options = 0;
        int difficulty = 0;
        String str = "";
        
        try{
            options = welcomeOptions(options);
            difficulty = difficultyOption(difficulty);
            startTime = System.currentTimeMillis() / 1000; // Start Timer
            do{
                do{
                    switch(options)
                    {
                        case 1:{
                            names.fillEuropeList(countryNames);
                            break;
                        }
                        case 2:{
                            names.fillAfricaList(countryNames);
                            break;
                        }
                        case 3:{
                            names.fillAsianList(countryNames);
                            break;
                        }
                        default:
                            JOptionPane.showMessageDialog(null, "Please Enter a Valid option");
                            break;
                    }
                }while(options < 1 || options > 3);
                
                Collections.shuffle(countryNames);
                do{
                    selection = gameDifficulty(selection, difficulty);
                        // UNCOMMENT FOR TESTING
                            // testPrint(countryNames, selection);
                        // END OF TESTING
                    JOptionPane.showMessageDialog(null, selection);
                    score = userGuess(selection, tries, score, answer, str);
                    selection.clear(); // Required
                }while(!countryNames.isEmpty());
            }while(tries != 0);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    } // End countryGame method


    public static List gameDifficulty(List<String> selection, int difficulty){
        do{
            switch(difficulty){
                case 1:{
                    selection = countryNames.subList(0, 3);
                    break;
                }
                case 2:{
                    selection = countryNames.subList(0, 4);
                    break;
                }
                case 3:{
                    selection = countryNames.subList(0, 5);
                    break;
                }
                default:{
                    JOptionPane.showMessageDialog(null, "Please input a valid option");
                    break;
                }
            }
        }while(difficulty < 1 || difficulty > 3);
        return selection;
    }

    public static int difficultyOption(int difficulty){
        difficulty = Integer.parseInt(JOptionPane.showInputDialog(null, "Choose a Difficulty\n" +
                                          "1: Easy\n2:Medium\n3: Hard"));
        return difficulty;
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
    } // End userGuess method

    public static int scoreMethod(int score){
        return score += 1;
    } // End scoreMethod

    public static int welcomeOptions(int options){
        JOptionPane.showMessageDialog(null, "WELCOME\nPlease Choose the Correct Country from each list");
        options = Integer.parseInt(JOptionPane.showInputDialog(null, "First, Choose a Continent\n" +
                                                           "Press 1 for Europe\nPress 2 for Africa\nPress 3 for Asia"));
        return options;
    } // End welcomeMessage

    public static void testPrint(List<String> flagNames, List<String> selection){
        System.out.println("\n\nOriginal Arraylist of Countries\n" + flagNames + " Current Size = " + flagNames.size());
        System.out.println("\n\nContents of the 4 Country Selection Arraylist\n" + selection + " Size of List " + selection.size());
    } // End testPrint

} // End of Class