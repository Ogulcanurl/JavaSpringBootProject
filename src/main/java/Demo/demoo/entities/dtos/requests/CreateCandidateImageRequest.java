package Demo.demoo.entities.dtos.requests;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateCandidateImageRequest {
    private int candidateId;
    private MultipartFile imgUrl;
}
