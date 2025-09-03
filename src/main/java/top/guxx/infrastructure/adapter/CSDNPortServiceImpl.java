package top.guxx.infrastructure.adapter;

import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import top.guxx.domain.csdn.adapter.ICSDNPortService;
import top.guxx.domain.csdn.model.ArticleFunctionRequest;
import top.guxx.infrastructure.gateway.ICSDNService;
import top.guxx.infrastructure.gateway.dto.SaveArticleRequest;
import top.guxx.infrastructure.gateway.dto.ArticleResponse;
import top.guxx.types.properties.CSDNApiProperties;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class CSDNPortServiceImpl implements ICSDNPortService  {

    private static final Logger log = LoggerFactory.getLogger(CSDNPortServiceImpl.class);

    @Resource
    private CSDNApiProperties csdnApiProperties;

    @Resource
    private ICSDNService csdnService;


    @Override
    public ArticleResponse writeArticle(ArticleFunctionRequest request) throws IOException {
        SaveArticleRequest articleRequestDTO = new SaveArticleRequest();
        articleRequestDTO.setTitle(request.getTitle());
        articleRequestDTO.setContent(request.getContent());
        articleRequestDTO.setTags(request.getTags());
        articleRequestDTO.setDescription(request.getDescription());
        articleRequestDTO.setCategories(csdnApiProperties.getCategories());
        fullObject(articleRequestDTO);

        Call<ArticleResponse> call = csdnService.saveArticle(csdnApiProperties.getCookie(), articleRequestDTO);
        Response<ArticleResponse> response = call.execute();

        log.info("请求CSDN发帖 \nreq:{} \nres:{}", JSON.toJSONString(articleRequestDTO), JSON.toJSONString(response));

        if (response.isSuccessful()) {
            ArticleResponse articleResponse = response.body();
            if (null == articleResponse) return null;
//            ArticleResponse.ArticleData articleData = articleResponse.getData();
//
//            ArticleFunctionResponse articleFunctionResponse = new ArticleFunctionResponse();
//            articleFunctionResponse.setCode(articleResponse.getCode());
//            articleFunctionResponse.setMsg(articleResponse.getMsg());
//            articleFunctionResponse.setArticleData(ArticleFunctionResponse.ArticleData.builder()
//                    .url(articleData.getUrl())
//                    .id(articleData.getId())
//                    .qrcode(articleData.getQrcode())
//                    .title(articleData.getTitle())
//                    .description(articleData.getDescription())
//                    .build());

            return articleResponse;
        }

        return null;

    }

    private void fullObject(SaveArticleRequest  request){
        //request.setMarkdownContent("# 测试内容\n这是一个测试文章");
        request.setReadType("public");
        request.setLevel("0");
//        request.setTags("Java测试");
        request.setStatus(0);
//        request.setCategories("");
        request.setType("original");
        request.setOriginalLink("");
        request.setAuthorizedStatus(false);
        request.setResourceUrl("");
        request.setNotAutoSaved("1");
        request.setSource("pc_mdeditor");
        request.setCoverImages(new ArrayList<>());
        request.setCoverType(1);
        request.setIsNew(1);
        request.setVoteId(0);
        request.setResourceId("");
        request.setPubStatus("publish");
        request.setSyncGitCode(0);
    }
}
