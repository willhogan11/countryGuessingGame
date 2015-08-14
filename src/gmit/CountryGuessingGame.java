package gmit;
import java.util.*;
import javax.swing.JOptionPane;

public class CountryGuessingGames {
    // Declaration of Associated Variables, Lists & Objects
    private static long startTime;
    private static long timeElapsed;
    private static List<String> countryNames = new ArrayList<String>();
    private static List<String> selection = new ArrayList<String>();
    static CountryNames names = new CountryNames();

    public static void main(String[] args) throws Exception {
        countryGame(); // Launches Game
    } // End of main method


    // Game Method, details line by line. 
    public static void countryGame(){
        int tries = 1, score = 0, answer = 0, options = 0, difficulty = 0; // Method Variable Declarations / initialisations
        String str = "";
        
        try{
            options = welcomeOptions(options); // Welcome message / continent choice
            difficulty = difficultyOption(difficulty); // Difiiculty choice, Easy, Medium or Hard
            startTime = System.currentTimeMillis() / 1000; // Start Game Timer
            do{
                continentOption(countryNames, options); // Fills the ArrayList with users continent choice
                Collections.shuffle(countryNames); // Randomly Shuffles the Arraylist
                do{
                    selection = gameDifficulty(selection, difficulty); // Creates sublist based in the users selected difficulty
                    // testPrint(countryNames, selection);  // UNCOMMENT FOR TESTING
                    JOptionPane.showMessageDialog(null, selection); // Displays the Sublist containing the countries
                    score = userGuess(selection, tries, score, answer, str); // Logs the Score for each round
                    selection.clear(); // Clears the sublist
                    if(countryNames.size() < 5){ // Ensures that the main ArrayList always has the correct amount of Elements for the next round
                        countryNames.clear(); // So if below 5 (Hard option), clears the main feeder ArrayList
                        continentOption(countryNames, options); // Repopulates the Arraylist with a new list
                        Collections.shuffle(countryNames); // and finally reshuffles the ArrayList
                    }   
                }while(!countryNames.isEmpty()); // Do all this while the ArrayList is not empty
            }while(tries != 0); // Do all this while the user has NOT guessed incorrectly
            
        }catch(Exception e){ 
            e.printStackTrace(); // Prints out the stack trace should there be any errors
        }
    } // End countryGame method

    
    // Method to Fill the Arraylist of countryNames with the users continent selection
    public static List continentOption(List<String> countryNames, int options){
        do{
            switch(options){
                case 1:{
                    names.fillEuropeList(countryNames); // Europe
                    break;
                }
                case 2:{
                    names.fillAfricaList(countryNames); // Africa
                    break;
                }
                case 3:{
                    names.fillAsianList(countryNames); // Asia
                    break;
                }
                case 4:{
                    names.fillNorthAmericaList(countryNames); // North America
                    break;
                }
                case 5:{
                    names.fillSouthAmericaList(countryNames); // South America
                    break;
                }
                case 6:{
                    names.fillAustralasianList(countryNames); // Australasia
                    break;
                }
                default:{
                    JOptionPane.showMessageDialog(null, "Please Enter a Valid option"); // If invalid selection
                    break;
                }
            }
        }while(options < 1 || options > 6); // Do this while selections is valid
        return countryNames; // Return the selection from the method
    } // End continentOption method


    // Method to return a sublist from the main feeder ArrayList based on the users difficulty choice
    public static List gameDifficulty(List<String> selection, int difficulty){
        do{
            switch(difficulty){
                case 1:{
                    selection = countryNames.subList(0, 3); // Easy
                    break;
                }
                case 2:{
                    selection = countryNames.subList(0, 4); // Medium
                    break;
                }
                case 3:{
                    selection = countryNames.subList(0, 5); // Hard
                    break;
                }
                default:{
                    JOptionPane.showMessageDialog(null, "Please input a valid option"); // If invalid selection
                    break;
                }
            }
        }while(difficulty < 1 || difficulty > 3); // Do this while selections is valid
        return selection; // Return the selection from the method
    } // End gameDifficulty method


    // Method to store the difficulty input from the user
    public static int difficultyOption(int difficulty){
        difficulty = Integer.parseInt(JOptionPane.showInputDialog(null, "Choose a Difficulty\n1: Easy\n2:Medium\n3: Hard"));
        return difficulty; // Return the selection from the method
    }

    // Method to process the users guess and compare against the programs guess, more details inside the method
    public static int userGuess(List<String> selection, int tries, int score, int answer, String str){
        // Delcarions of Method Variables etc
        String guess;
        Random rnd = new Random(); // New Random Object creation
        int index = rnd.nextInt(selection.size()); // Stores the randomly selected number from the sublist
        str = selection.get(index); // The String representation of the Above, ie the Country name randomly selected number from the sublist
        JOptionPane.showMessageDialog(null, "Which Country was Number " + (index+1) + "?"); // Displays the random number to screen

        try{
            guess = JOptionPane.showInputDialog(null, "Please enter you Guess"); // User enters their guess
            if(guess.equals("")){
                JOptionPane.showMessageDialog(null, "Please Enter a country"); // If nothing is entered, displays message to user
            }else{
                for(int i=0; i < selection.size(); i++){ // Loop over the sublist
                    if(str.equalsIgnoreCase(guess)){ // If the Random Country equals the users entered guess...
                        JOptionPane.showMessageDialog(null, "Correct"); // Displays correct
                        score = scoreMethod(score); // Increment the score by 1 for each correct answer
                        break; // Break out of the loop, now that we've got a match
                    }else{
                        JOptionPane.showMessageDialog(null, "Incorrect"); // If in correct display message
                        tries--; // Decrement the tries
                        if(tries == 0){ // User has one try, so game is over, stop timer and display game results
                            timeElapsed = System.currentTimeMillis()/1000 - startTime; // End game timer
                            JOptionPane.showMessageDialog(null, "GAME OVER\nThe Correct Answer was....\n" + str.toUpperCase()
                                                              + "\nYou Scored " + score
                                                              + "\nIn a time of " + timeElapsed + " seconds");
                            System.exit(0); // Exit game
                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace(); // Prints out the stack trace should there be any errors
        }
        return score; // Return the Score count
    } // End userGuess method


    // Method to increment the users score
    public static int scoreMethod(int score){
        return score += 1;
    } // End scoreMethod


    // Welcome method with options
    public static int welcomeOptions(int options){
        JOptionPane.showMessageDialog(null, "WELCOME\nPlease Choose the Correct Country from each list");
        options = Integer.parseInt(JOptionPane.showInputDialog(null, "First, Choose a Continent\n" +
                                                           "Press 1 for Europe\nPress 2 for Africa\nPress 3 for Asia\n" +
                                                           "Press 4 for North America\nPress 5 for South America\nPress 6 for Australasia"));
        return options;
    } // End welcomeMessage

    
    // Test print method
    public static void testPrint(List<String> flagNames, List<String> selection){
        System.out.println("\n\nOriginal Arraylist of Countries\n" + flagNames + " Current Size = " + flagNames.size());
        System.out.println("\n\nContents of the 4 Country Selection Arraylist\n" + selection + " Size of List " + selection.size());
    } // End testPrint

} // End of Class