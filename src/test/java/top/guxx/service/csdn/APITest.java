package top.guxx.service.csdn;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import retrofit2.Call;
import retrofit2.Response;
import top.guxx.Application;
import top.guxx.infrastructure.gateway.ICSDNService;
import top.guxx.infrastructure.gateway.dto.SaveArticleRequest;
import top.guxx.infrastructure.gateway.dto.SaveArticleResponse;

import java.io.IOException;
import java.util.ArrayList;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class APITest {
    
    @Resource
    private ICSDNService icsdnService;
    
    @Test
    public void testSaveArticle() {
        String cookie = "dc_sid=21f5bb4de98e88049f5292ae3593f70b; fid=20_48032897523-1743237693494-081936; uuid_tt_dd=11_22498201076-1743237693495-184730; dc_session_id=11_1743237693495.122370; c_first_ref=default; c_first_page=https%3A//editor.csdn.net/md/%3Fnot_checkout%3D1%26spm%3D1000.2115.3001.5352; c_dsid=11_1743237693496.168400; c_segment=10; c_page_id=default; loginbox_strategy=%7B%22taskId%22%3A349%7D; SESSION=c25265f3-b98f-477c-9bb2-fba89ede5d12; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1743237695; HMACCOUNT=BF81B60489032C87; creative_btn_mp=1; hide_login=1; UserName=weixin_46755643; UserInfo=023fca3559d24cbabca34a712d75f1d0; UserToken=023fca3559d24cbabca34a712d75f1d0; UserNick=%E5%B0%8F%E5%82%85%E5%93%A5%E7%9A%84%E7%A0%81%E4%BB%94; AU=1C3; UN=weixin_46755643; BT=1743237720298; p_uid=U010000; creativeSetApiNew=%7B%22toolbarImg%22%3A%22https%3A//img-home.csdnimg.cn/images/20231011044944.png%22%2C%22publishSuccessImg%22%3A%22https%3A//img-home.csdnimg.cn/images/20240229024608.png%22%2C%22articleNum%22%3A0%2C%22type%22%3A0%2C%22oldUser%22%3Afalse%2C%22useSeven%22%3Atrue%2C%22oldFullVersion%22%3Afalse%2C%22userName%22%3A%22weixin_46755643%22%7D; csdn_newcert_weixin_46755643=1; c_pref=https%3A//editor.csdn.net/; c_ref=https%3A//mp.csdn.net/mp_blog/creation/success/146694967; log_Id_pv=8; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1743237915; dc_tos=stvmzg; log_Id_view=199; log_Id_click=12";
        
        SaveArticleRequest request = new SaveArticleRequest();
        request.setTitle("测试文章标题");
        request.setMarkdownContent("# 测试内容\n这是一个测试文章");
        request.setContent("<h1>测试内容</h1><p>这是一个测试文章</p>");
        request.setReadType("public");
        request.setLevel("0");
        request.setTags("Java测试");
        request.setStatus(0);
        request.setCategories("");
        request.setType("original");
        request.setOriginalLink("");
        request.setAuthorizedStatus(false);
        request.setDescription("这是一个测试文章描述。");
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
        
        try {
            Call<SaveArticleResponse> call = icsdnService.saveArticle(cookie, request);
            Response<SaveArticleResponse> response = call.execute();
            
            if (response.isSuccessful() && response.body() != null) {
                SaveArticleResponse result = response.body();
                log.info("Article saved successfully!");
                log.info("Response code: {}", result.getCode());
                log.info("Trace ID: {}", result.getTraceId());
                log.info("Message: {}", result.getMsg());
                
                if (result.getData() != null) {
                    log.info("Article ID: {}", result.getData().getId());
                    log.info("Article URL: {}", result.getData().getUrl());
                    log.info("Article Title: {}", result.getData().getTitle());
                    log.info("Article Description: {}", result.getData().getDescription());
                    log.info("QR Code: {}", result.getData().getQrcode());
                }
            } else {
                log.error("Failed to save article. Response code: {}", response.code());
                log.error("Response message: {}", response.message());
                if (response.errorBody() != null) {
                    log.error("Error body: {}", response.errorBody().string());
                }
            }
        } catch (IOException e) {
            log.error("Error occurred while calling CSDN API", e);
        }
    }
}