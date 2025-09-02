package top.guxx.infrastructure.gateway.dto;

import lombok.Data;

@Data
public class SaveArticleData {
    private String url;
    private Long id;
    private String qrcode;
    private String title;
    private String description;
}