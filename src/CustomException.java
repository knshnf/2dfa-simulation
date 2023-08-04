/**
 *  STALGCM Term 3 AY 2022 - 2023
 *  DIGNO, Kenneth Clark
 *  FETALVERO, Kenshin
 *  LIOBING, Aldwin
 *  S14
 */

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