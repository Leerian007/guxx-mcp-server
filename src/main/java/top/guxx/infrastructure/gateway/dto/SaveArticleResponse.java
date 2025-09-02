package top.guxx.infrastructure.gateway.dto;

import lombok.Data;

@Data
public class SaveArticleResponse {
    private Integer code;
    private String traceId;
    private SaveArticleData data;
    private String msg;
}