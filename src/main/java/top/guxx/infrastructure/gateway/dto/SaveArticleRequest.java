package top.guxx.infrastructure.gateway.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class SaveArticleRequest {
    private String title;
    
    @SerializedName("markdowncontent")
    private String markdownContent;
    
    private String content;
    
    @SerializedName("readType")
    private String readType;
    
    private String level;
    private String tags;
    private Integer status;
    private String categories;
    private String type;
    
    @SerializedName("original_link")
    private String originalLink;
    
    @SerializedName("authorized_status")
    private Boolean authorizedStatus;
    
    @SerializedName("Description")
    private String description;
    
    @SerializedName("resource_url")
    private String resourceUrl;
    
    @SerializedName("not_auto_saved")
    private String notAutoSaved;
    
    private String source;
    
    @SerializedName("cover_images")
    private List<String> coverImages;
    
    @SerializedName("cover_type")
    private Integer coverType;
    
    @SerializedName("is_new")
    private Integer isNew;
    
    @SerializedName("vote_id")
    private Integer voteId;
    
    @SerializedName("resource_id")
    private String resourceId;
    
    @SerializedName("pubStatus")
    private String pubStatus;
    
    @SerializedName("sync_git_code")
    private Integer syncGitCode;
}