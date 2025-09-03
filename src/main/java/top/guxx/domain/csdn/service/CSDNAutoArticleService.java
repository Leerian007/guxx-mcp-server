package top.guxx.domain.csdn.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import top.guxx.domain.csdn.adapter.ICSDNPortService;
import top.guxx.domain.csdn.model.ArticleFunctionRequest;
import top.guxx.infrastructure.gateway.ICSDNService;
import top.guxx.infrastructure.gateway.dto.ArticleResponse;

import java.io.IOException;

@Service
@Slf4j
public class CSDNAutoArticleService {
    @Resource
    private ICSDNPortService port;

    @Tool(description = "发布文章到CSDN")
    public ArticleResponse saveArticle(ArticleFunctionRequest request) throws IOException {
        log.info("CSDN发帖，标题:{} 内容:{} 标签:{}", request.getTitle(), request.getMarkdowncontent(), request.getTags());
        return port.writeArticle(request);
    }

}
