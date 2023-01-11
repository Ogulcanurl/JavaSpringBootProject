package Demo.demoo.core.utitilies.results;

public class SuccessResult extends Result{
    public SuccessResult() {
        super(true);
    }

    public SuccessResult(boolean success, String message) {
        super(true, message);
    }
    public SuccessResult(String message){
        super(true,message);
    }
}
