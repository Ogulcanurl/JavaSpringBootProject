package Demo.demoo.core.utitilies.results;

public class Result {
    boolean success;
    String message;

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
    public Result(boolean success){
        this.success = success;
    }
    public Result(boolean success, String message){
        this(success);
        this.message = message;
    }
}
