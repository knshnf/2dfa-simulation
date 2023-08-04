/**
 * The CustomException class extends the Exception class which allows for user defined exception
 */
public class CustomException extends Exception{
    /**
     * Constructor for custom exception
     * @param errorMessage string containg information about the Exception that occured
     */
    public CustomException(String errorMessage) {
        super(errorMessage);
    }
}