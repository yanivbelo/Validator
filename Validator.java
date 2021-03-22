package Test3;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Validator
{
    //! Enum cases in use for default method in line 16:
    enum ValidationMethod
    {
        ONLY_NUMBERS, ONLY_ALPHABETIC, ONLY_VALID_EMAIL , ONLY_ALPHABETIC_AND_NUMBERS , ONLY_VALID_PHONE_NUMBER
    }

    //! generic userInput (Scanner validation), gets massage to user + scanner and enum case for the desired validation:
    default String inputValidation(String massage, Scanner scanner, ValidationMethod validationMethod)
    {
        String userInput = "";

        switch (validationMethod)
        {
            case ONLY_NUMBERS -> {
                do
                {
                    System.out.println(massage);
                    userInput = scanner.next();
                    scanner.nextLine(); //! clear Scanner garbage:
                }
                while (!isNumbersOnly(userInput,true,false));
            } //! if only numbers allowed.
            case ONLY_VALID_EMAIL -> {
                do
                {
                    System.out.println(massage);
                    userInput = scanner.next();
                    scanner.nextLine(); //! clear Scanner garbage:
                }
                while (isValidEmail(userInput, true));
            } //! only valid email template allowed.
            case ONLY_ALPHABETIC -> {
                do
                {
                    System.out.println(massage);
                    userInput = scanner.next();
                    scanner.nextLine(); //! clear Scanner garbage:
                }
                while (!isOnlyAlphabetic(userInput,true));
            } //! if only alphabetic allowed.
            case ONLY_ALPHABETIC_AND_NUMBERS -> {
                do
                {
                    System.out.println(massage);
                    userInput = scanner.next();
                    scanner.nextLine(); //! clear Scanner garbage:
                }
                while (!isAlphabeticAndNumbersAndNoSpecialChars(userInput,true));
            } //! if only (alphabetic + numbers) allowed (No special chars).
            case ONLY_VALID_PHONE_NUMBER -> {
                do
                {
                    System.out.println(massage);
                    userInput = scanner.next();
                    scanner.nextLine(); //! clear Scanner garbage:
                }
                while (!isValidPhoneNumber(userInput,true));
            } //! only valid Phone number template allowed.
        }

        return userInput;
    }

    //! method checks if the String parameter is only Alphabetic.
    default boolean isOnlyAlphabetic(String userInput, boolean printError)
    {
        //! if input too short, exit with error:
        if(userInput.length() < 2)
        {
            if (printError)
            {
                System.err.println("Invalid input, Min 2 chars required...");
            }

            return false;
        }

        //! regex pattern -> do not allowed this chars at input String ([]<>()*#@!,`~'/|+:;&{}=_%$?):
        Pattern pattern = Pattern.compile("[\\[\\]<>()*#@!,`~/|+:;&{}=_%$?0-9]");
        Matcher matcher = pattern.matcher(userInput);
        boolean isStringContainsSpecialCharacter = matcher.find();


        //! if found -> print out error:
        if (isStringContainsSpecialCharacter)
        {
            if (printError)
            {
                System.err.println("Invalid input, only Alphabetic allowed.");
            }

            return false;
        }

        return true;
    }

    //! method checks if Phone number string is in a valid template:
    default boolean isValidPhoneNumber(String phoneNumber,boolean printError)
    {
        String phonePattern = "^\\+(?:[0-9] ?){6,14}[0-9]$";
        Pattern pattern = Pattern.compile(phonePattern);
        Matcher matcher = pattern.matcher(phoneNumber);

        //! if didn't found this chars only -> print out error:
        if (printError && !matcher.matches())
        {
            System.err.println("Invalid Phone number input.");
        }

        return matcher.matches();
    }

    //! method checks if email string is valid template:
    default boolean isValidEmail(String email,boolean printError)
    {
        String emailPattern = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern p = Pattern.compile(emailPattern);
        Matcher m = p.matcher(email);

        //! if found -> print out error:
        if (printError && !m.matches())
        {
            System.err.println("Invalid mail input.");
        }

        return !m.matches();
    }

    //! method checks if the String parameter is a number.
    default boolean isNumbersOnly(String input, boolean printError , boolean isDoubleNumbersSupported)
    {
        Pattern pattern;

        if (isDoubleNumbersSupported)
        {
            pattern = Pattern.compile("^[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)$"); //! for float numbers
        }

        else
        {
            pattern = Pattern.compile("^[0-9]+$");  //! for natural numbers
        }


        Matcher matcher = pattern.matcher(input);
        boolean isFound = matcher.find();

        if (!isFound)
        {
            if (printError)
            {
                System.err.println("Invalid input !! only Numbers allowed.");
            }

            return false;
        }

        return true;
    }

    //! method checks if the String parameter is only Alphabetic + numbers.
    default boolean isAlphabeticAndNumbersAndNoSpecialChars(String userInput, boolean printError)
    {
        //! if input too sort, exit with error:
        if(userInput.length() < 2)
        {
            if (printError)
            {
                System.err.println("Invalid input, Min 2 chars required...");
            }

            return false;
        }

        //! regex pattern -> do not allowed this chars at input String ([]<>()*#@!,`~'/|+:;&{}=_%$?) :
        Pattern pattern = Pattern.compile("[\\[\\]<>()*#@!,`~'/|+:;&{}=_%$?]");
        Matcher matcher = pattern.matcher(userInput);
        boolean isStringContainsSpecialCharacter = matcher.find();

        if (isStringContainsSpecialCharacter)
        {
            //! if found -> print out error:
            if (printError)
            {
                System.err.println("Invalid input, only Alphabetic and numeric allowed.");
            }

            return false;
        }

        return true;
    }

    //! validate max char allowed:
    default boolean maxCharAllowed(String userInput, int maxCharAllowed)
    {
        if (userInput.length() <= maxCharAllowed)
        {
            return true;
        }

        System.out.println("Input is over the char limits...");
        return false;
    }

    //! validate min char allowed:
    default boolean minCharAllowed(String userInput, int minCharAllowed)
    {
        if (userInput.length() >= minCharAllowed)
        {
            return true;
        }

        System.out.println("Input is lower from min char limits...");
        return false;
    }
}
