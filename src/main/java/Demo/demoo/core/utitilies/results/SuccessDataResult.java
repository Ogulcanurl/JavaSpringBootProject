package Demo.demoo.core.utitilies.results;

public class SuccessDataResult<T> extends DataResult<T>{
    public SuccessDataResult(T data){
        super(data, true);
    }

    public SuccessDataResult(T data, String message) {
        super(data, true, message);
    }
    public SuccessDataResult(T data, boolean success, String message){
        super(data, true, message);
    }
}
