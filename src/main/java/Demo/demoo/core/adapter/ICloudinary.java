package Demo.demoo.core.adapter;

import Demo.demoo.core.utitilies.results.DataResult;
import Demo.demoo.core.utitilies.results.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface ICloudinary {
    DataResult<Map<String, String>> upload(MultipartFile multipartFile);
    DataResult<Map> delete(String id) throws IOException;
}
