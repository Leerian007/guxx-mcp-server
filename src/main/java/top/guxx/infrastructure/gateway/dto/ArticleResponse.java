package top.guxx.infrastructure.gateway.dto;

import lombok.Data;

@Data
public class ArticleResponse {
    private Integer code;
    private String traceId;
    private SaveArticleData data;
    //private String data;
    private String msg;
}