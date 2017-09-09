package View;

import javafx.scene.control.*;
import javafx.fxml.FXML;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

    @FXML private Button SubmitBtn;
    @FXML private TextField PasswWordTxt;
    @FXML private Label AssignmentDetail;
    @FXML public static String ErrorMessage;
    /**
     * This method will be run when the program start
     * It will show the description of the assignment
     */
    @FXML private void initialize() {
        String Assignment6 = "Problem 6.18:\n(Check password) Some websites impose certain rules for passwords. Write a" +
                                "method that checks whether a string is a valid password. Suppose the password" +
                                "rules are as follows:\n" +
                                "■ A password must have at least eight characters.\n" +
                                "■ A password consists of only letters and digits.\n" +
                                "■ A password must contain at least two digits.";

        AssignmentDetail.setText(Assignment6);
        PasswWordTxt.setFocusTraversable(false);
    }

    @FXML public void SubmitBtn_Click(){

        String Input = PasswWordTxt.getText();
        boolean PasswordAccepted = false;

        /**
         * Check if the textbox field is empty or not
         * If it is empty, prompt user to enter the password
         */
        if(Input.trim().isEmpty())
        {
            ErrorMessage = "Please enter password!";
            PasswWordTxt.requestFocus();
            infoBox(ErrorMessage, "Error",null);
        }
        else
        {
            try
            {
                PasswordAccepted = CheckPassword(Input);
            }
            catch (Exception e){
                PasswordAccepted = false;
            }

            if(PasswordAccepted)
            {
                String Message = "Congratulation, your password "+Input+" has been accepted!";
                infoBox(Message,"Congratulation!", null);
            }
        }
    }

    /**
     * Check Password for length, letters, digits
     * @param Input
     * @return
     */
    @FXML public static boolean CheckPassword(String Input) {

        boolean PasswordAccepted = false;

        /**
         * Check if input has at least 8 chars
         */
        boolean HasAtLeast8 = false;
        try {
            HasAtLeast8 = GetPassWordLength(Input);
        }
        catch (Exception e) {
            HasAtLeast8 = false;
        }

        if(HasAtLeast8) {

            /**
             * Check if input has letters
             */
            boolean HasLetter = false;
            try {
                HasLetter = CheckLetters(Input);
            } catch (Exception e) {
                HasLetter = false;
            }

            /**
             * Check if input has special chars
             */
            boolean HasNoSpecialLetters = false;
            try {
                HasNoSpecialLetters = CheckSpecialLetters(Input);
            } catch (Exception e) {
                HasNoSpecialLetters = false;
            }

            /**
             * This one is the combination of2 steps:
             * Check if input has difit
             * And if it has 2 or more digits
             */
            boolean HasDigits = false;
            try {
                HasDigits = CheckDigits(Input);
            } catch (Exception e) {
                HasDigits = false;
            }

            if(HasAtLeast8 && HasLetter && HasNoSpecialLetters && HasDigits)
            {
                PasswordAccepted = true;
            }
            else
            {
                PasswordAccepted = false;
            }
        }
        else
        {
            PasswordAccepted = false;
        }

        return PasswordAccepted;
    }

    /**
     * Check if input has at least 8 chars
     */
    @FXML
    private static boolean GetPassWordLength(String Input)
    {
        boolean HasAtLeast8 = false;
        if(Input.length() < 8)
        {
            ErrorMessage = "Password is too short, must have at least 8 letters.";
            //PasswWordTxt.requestFocus();
            infoBox(ErrorMessage, "Error",null);
            HasAtLeast8 = false;
        }
        else
        {
            HasAtLeast8 = true;
        }
        return  HasAtLeast8;
    }

    /**
     * Check if input has letters
     */
    @FXML
    private static boolean CheckLetters(String Input)
    {
        boolean HasLetter = false;
        Pattern letter = Pattern.compile("[a-zA-z]");

        Matcher hasLetter = letter.matcher(Input);
        if(hasLetter.find())
        {
            HasLetter = true;
        }
        else
        {
            HasLetter = false;
            ErrorMessage = "Password must contain letters.";
            //PasswWordTxt.requestFocus();
            infoBox(ErrorMessage, "Error",null);
        }
        return  HasLetter;
    }

    /**
     * Check if input has special chars
     */
    @FXML
    private static boolean CheckSpecialLetters(String Input)
    {
        boolean HasNoSpecialLetters = false;
        Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");

        Matcher hasSpecial= special.matcher(Input);
        if(hasSpecial.find())
        {
            HasNoSpecialLetters = false;
            ErrorMessage = "Password can not contain spcial characters.";
            //PasswWordTxt.requestFocus();
            infoBox(ErrorMessage, "Error",null);
        }
        else
        {
            HasNoSpecialLetters = true;
        }
        return  HasNoSpecialLetters;
    }

    /**
     * This one is the combination of2 steps:
     * Check if input has difit
     * And if it has 2 or more digits
     */
    @FXML
    private static boolean CheckDigits(String Input)
    {
        boolean HasDigits = false;
        int TotalDigitCount = 0;
        for(int i = 0; i < Input.length(); i++)
        {
            Character charAtIndex = Input.charAt(i);
            try
            {
                Integer.parseInt(charAtIndex.toString());
                TotalDigitCount +=1;
            }
            catch (Exception e)
            {
                //Not a digit
            }
        }

        if(TotalDigitCount >=2)
        {
            HasDigits = true;
        }
        else
        {
            HasDigits = false;
            ErrorMessage = "Password must contain at least 2 digits.";
            //PasswWordTxt.requestFocus();
            infoBox(ErrorMessage, "Error",null);
        }
        return  HasDigits;
    }

    /**
     * This method will show the alert box
     * @param infoMessage
     * @param titleBar
     * @param headerMessage
     */
    @FXML private static void infoBox(String infoMessage, String titleBar, String headerMessage)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }


}
