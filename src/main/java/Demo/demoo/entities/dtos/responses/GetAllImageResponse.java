package Demo.demoo.entities.dtos.responses;

import Demo.demoo.entities.candidate.CandidateImage;
import lombok.Data;

@Data
public class GetAllImageResponse {
    private int candidateId;
    private String urlImage;

    public GetAllImageResponse(CandidateImage candidateImage){
        this.candidateId = candidateImage.getCandidate().getId();
        this.urlImage = candidateImage.getImage();
    }
}
