package management5.com.management5.exception;

public class DatabaseConnectivityException extends  RuntimeException{
    public DatabaseConnectivityException(String message){
        super(message);
    }
    public DatabaseConnectivityException(){
        super("Unable to connect to the database. Please check your database settings and try again");
    }

}
