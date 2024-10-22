package dat.lyngby.dtos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PackDTO {
    private String name;
    private String description;
}
