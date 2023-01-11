package Demo.demoo.core.utitilies;


public class StringConvertHelper {

    public String multiFileValidation(String data, String[] readList, String replacement){
        String newData = data;
        for (String a: readList) {
            newData = data.replace(a, replacement);
            if (!newData.equals(data)) {
                break;
            }
        }
        return newData;
    }
}
