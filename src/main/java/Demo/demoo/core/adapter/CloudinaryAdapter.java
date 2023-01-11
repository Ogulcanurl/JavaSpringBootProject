package Demo.demoo.core.adapter;

import Demo.demoo.core.utitilies.results.*;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
@Service
public class CloudinaryAdapter implements ICloudinary{
    private final Cloudinary cloudinary;

    public CloudinaryAdapter() {
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("cloud_name", "dvcxmja46");
        valuesMap.put("api_key","192959176756264");
        valuesMap.put("api_secret", "1rbNi7NIxpkaKAyebbgpz4UdzVI");
        cloudinary = new Cloudinary(valuesMap);
    }
    @Override
    public  DataResult<Map<String, String>> upload(MultipartFile multipartFile){
        File file;
        try {
            file = convert(multipartFile);
            Map<String, String> result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            file.delete();
            return new SuccessDataResult<>(result);
        } catch (IOException e) {
            e.printStackTrace();
            return new ErrorDataResult<>("Dosya yüklenemedi");
        }
    }

    @Override
    public DataResult<Map> delete (String id) throws IOException {
        Map result = cloudinary.uploader().destroy(id,ObjectUtils.emptyMap());
        return new SuccessDataResult<>(result);
    }
    private static File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream stream = new FileOutputStream(file);
        stream.write(multipartFile.getBytes());
        stream.close();

        return file;
    }
}
