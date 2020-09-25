package god.hu.usage.tool;

import java.security.SecureRandom;
import java.util.Random;

public class SerialNumberGenerator {

    private final String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private int splitInto = 1;
    private char splitter = '-';
    private boolean isToSplit = false;
    private int LENGTH = 4;
    private Random random = new SecureRandom();

    public SerialNumberGenerator() {

    }

    /**
     * Generates random serial number on the fry
     * @param length Defines how long your serial number can be
     */
    public SerialNumberGenerator(int length){
        LENGTH = length;
    }

    public SerialNumberGenerator split(int splitInto, char splitter){
        this.splitInto = splitInto;
        this.splitter = splitter;
        isToSplit = true;
        return this;
    }

    private String split() throws InvalidSerialNumberLengthException {
        if(LENGTH % splitInto != 0){
            throw new InvalidSerialNumberLengthException();
        }

        StringBuilder sb = new StringBuilder();
        int spacer = 0, tempLength = LENGTH;

        while(tempLength > 0){
            if(spacer == (LENGTH / splitInto)){
                sb.append(splitter);
                spacer = 0;
            }
            tempLength--;
            spacer++;
            sb.append(pickRandomCharacter());
        }

        return sb.toString();
    }

    private char pickRandomCharacter() {
        return alphabets.charAt(random.nextInt(LENGTH));
    }

    /**
     * Generates random char string
     */
    public String generate(){

        StringBuilder serial = new StringBuilder();

//        check if need splitting serial number
        if(isToSplit){
            try {
                return split();
            } catch (InvalidSerialNumberLengthException e) {
                e.printStackTrace();
                return "Error! " + e.getLocalizedMessage();
            }
        }

//        generate whole serial number (no split)
        for (int i = 0; i < LENGTH; i++) serial.append(pickRandomCharacter());

        return serial.toString();
    }


    @Override
    public String toString() {
        return "Generates your serial numbers on the fry";
    }

    private static class InvalidSerialNumberLengthException extends Exception {
        InvalidSerialNumberLengthException(){
            super("Serial number length must be divisible by the number of parts to perform split action.");
        }
    }
}
