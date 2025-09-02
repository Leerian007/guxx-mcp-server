package top.guxx.domain.database.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataBaseMcpRequest {

    @JsonProperty(required = false, value = "id")
    @JsonPropertyDescription("id")
    private Long id;

    @JsonProperty(required = false, value = "name")
    @JsonPropertyDescription("姓名")
    private String name;

    @JsonProperty(required = false, value = "age")
    @JsonPropertyDescription("年龄")
    private Integer age;

    @JsonProperty(required = false, value = "gender")
    @JsonPropertyDescription("性别")
    private Integer gender;

    @JsonProperty(required = false, value = "address")
    @JsonPropertyDescription("地址")
    private String address;
}
