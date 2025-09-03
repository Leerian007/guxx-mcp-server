package top.guxx.domain.csdn.adapter;

import top.guxx.domain.csdn.model.ArticleFunctionRequest;
import top.guxx.infrastructure.gateway.dto.ArticleResponse;

import java.io.IOException;

public interface ICSDNPortService {
    ArticleResponse writeArticle(ArticleFunctionRequest request) throws IOException;
}
