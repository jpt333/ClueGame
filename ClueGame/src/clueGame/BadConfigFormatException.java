package clueGame;

//fix this warning
public class BadConfigFormatException extends Exception {

	public BadConfigFormatException() {
		super("file's data is incorectly forrmated");
	}
	
	public BadConfigFormatException(String message) {
		super(message + " is incorrect");
	}
}
