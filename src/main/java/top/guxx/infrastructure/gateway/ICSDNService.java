package top.guxx.infrastructure.gateway;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import top.guxx.domain.csdn.model.ArticleFunctionRequest;
import top.guxx.infrastructure.gateway.dto.SaveArticleRequest;
import top.guxx.infrastructure.gateway.dto.ArticleResponse;

public interface ICSDNService {
    
    @POST("blog-console-api/v3/mdeditor/saveArticle")
    @Headers({
        "accept: */*",
        "accept-language: zh-CN,zh;q=0.9",
        "content-type: application/json",
        "dnt: 1",
        "origin: https://editor.csdn.net",
        "priority: u=1, i",
        "referer: https://editor.csdn.net/",
        "sec-ch-ua: \"Not(A:Brand\";v=\"99\", \"Google Chrome\";v=\"133\", \"Chromium\";v=\"133\"",
        "sec-ch-ua-mobile: ?0",
        "sec-ch-ua-platform: \"macOS\"",
        "sec-fetch-dest: empty",
        "sec-fetch-mode: cors",
        "sec-fetch-site: same-site",
        "user-agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36",
        "x-ca-key: 203803574",
        "x-ca-nonce: 138d7d05-2600-482d-83a6-62e6c02bdc17",
        "x-ca-signature: I1z2XgTgYqo839qPZYINgKRHWp3v7XlHO8QbmLDKMDA=",
        "x-ca-signature-headers: x-ca-key,x-ca-nonce"
    })
    Call<ArticleResponse> saveArticle(
        @Header("Cookie") String cookie,
        @Body SaveArticleRequest request
    );
}