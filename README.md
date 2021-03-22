# Validator
Java Validator interface
Simple and easy to use a "user input" validation interface.

By implementing the interface we get 7 validation methods by default + a wraper method that holds use of all of them, by selection the validation method we want via Enum cases as parameter.

# All this methods return boolean (after validation done):

- isOnlyAlphabetic(String userInput, boolean printError)
- isValidPhoneNumber(String phoneNumber,boolean printError)
- isValidEmail(String email,boolean printError)
- isNumbersOnly(String input, boolean printError , boolean isDoubleNumbersSupported)
- isAlphabeticAndNumbersAndNoSpecialChars(String userInput, boolean printError)
- maxCharAllowed(String userInput, int maxCharAllowed)
- minCharAllowed(String userInput, int minCharAllowed)

# The wraper method (inputValidation):

inputValidation(String massage, Scanner scanner, ValidationMethod validationMethod)

gets a Scanner object + String massage for user (before the use of the scanner),than by selecting from the Enum cases for the desiresd validation method, the method will return the user input string only if met the validation process that was selected via Enum.
this method uses all the boolean methods according to the Enum validation method request.

# This are the Enum validation cases:

 - ONLY_NUMBERS
 - ONLY_ALPHABETIC,
 - ONLY_VALID_EMAIL ,
 - ONLY_ALPHABETIC_AND_NUMBERS 
 - ONLY_VALID_PHONE_NUMBER


# If we are not using "user input", we can use all the methods that return boolean in the "Setters" methods in our Object classes.
for example:

public void setFirstName(String firstName)
    {
        if (isOnlyAlphabetic(firstName,true))
        {
            this.firstName = firstName; 
        }
    }


