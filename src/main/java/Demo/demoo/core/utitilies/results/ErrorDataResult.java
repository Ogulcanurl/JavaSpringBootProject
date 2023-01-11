package Demo.demoo.core.utitilies.results;

public class ErrorDataResult<T> extends DataResult<T>{
    public ErrorDataResult(String a) {
        super(null, false);
    }
    public ErrorDataResult(){
        super(null, false);
    }
    public ErrorDataResult(T data, String message){
        super(data, false, message);
    }
}
