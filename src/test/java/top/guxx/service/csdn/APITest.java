package top.guxx.service.csdn;

import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import retrofit2.Call;
import retrofit2.Response;
import top.guxx.Application;
import top.guxx.domain.csdn.model.ArticleFunctionRequest;
import top.guxx.domain.csdn.service.CSDNAutoArticleService;
import top.guxx.infrastructure.gateway.ICSDNService;
import top.guxx.infrastructure.gateway.dto.SaveArticleRequest;
import top.guxx.infrastructure.gateway.dto.ArticleResponse;

import java.io.IOException;
import java.util.ArrayList;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class APITest {
    
    @Resource
    private ICSDNService icsdnService;

    @Resource
    private CSDNAutoArticleService csdnAutoArticleService;

    @Value("${csdn.api.cookie}")
    private String cookie;

    @Test
    public void testSaveArticle() {
        //String cookie = "uuid_tt_dd=10_19015963630-1750143800631-176219; fid=20_21610282401-1750143801689-958452; UserName=qq_60413090; UserInfo=b204c2d9c57545a9b5ede5df99d056a2; UserToken=b204c2d9c57545a9b5ede5df99d056a2; UserNick=guxx325; AU=499; UN=qq_60413090; BT=1751337645514; p_uid=U010000; csdn_newcert_qq_60413090=1; tfstk=gXOxK9qPs0mc23OArEkujMyPk20otYY42n8QsGj0C3KJ7hF0i1fcCNsJbSAMSF9T6eSziEADSgI9j31i7xoqXrRJv5Vm0x8_61fsxDcntEuV_1iHUudubi7wPGglhHEtWX1sxDcotE8V_1tMid5reUsPWN_f5h6SNg7Nf1sfCuw5Rg111N6_Pz_Ckr_fchTSyNS5f116fUgR7jW-Xgafj5pnZkI1xNS815ORlcSBkgQPzQQAHMGF25TvwZBAAEOKboelPQARKT4iq_TeEn_R9jEFkLTdGNdZRRIOBI5R2HhQ_aJXDQ69ZVlprsQOd16855TR1K-e16GQ2aJWodO2DPF1uIAhBMW-55WGNBXBdnUZ8U1f5hWesDPVcFTeTpfKMk7vFeCR4jAH9iTYxMQgh438QRWfzbMQO0A8LPt1yM0x6RyNU47Rx438QRWfzaInkVeaQTuP.; __gads=ID=09ec45ca3b841533:T=1750149696:RT=1756801240:S=ALNI_MbMNPiVHHhmnt0_usGiXXKMndJc9Q; __gpi=UID=00001130f8737673:T=1750149696:RT=1756801240:S=ALNI_MaotMQRMx7bBesazg9abUyIjQMvDg; __eoi=ID=6308387afec28438:T=1750149696:RT=1756801240:S=AA-AfjYDCgyZXIimSvEukOBL3oRQ; FCNEC=%5B%5B%22AKsRol-GgnEZaG1xEcV2bspqz14svqx5CAedhv7DNqrDcUTSFNps-xVdiowQE_lAd2pG-6dzOnHwV4CztFhHW028JckWG3tSLntiSh4G1vg7EsJBYMmDcu5UIfDxkQ5P30iI18YTTg86I1XMgNUVC2PNk6Idz5cUrg%3D%3D%22%5D%5D; dc_session_id=10_1756870888562.868017; c_first_ref=www.bing.com; c_first_page=https%3A//www.csdn.net/; c_dsid=11_1756870889301.038043; c_segment=11; c-sidebar-collapse=0; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1756437644,1756696170,1756777552,1756870890; HMACCOUNT=C865D62C6E610C23; c_ab_test=1; dc_sid=c5942a69f13bf05df31d7f22643dff48; creativeSetApiNew=%7B%22toolbarImg%22%3A%22https%3A//img-home.csdnimg.cn/images/20231011044944.png%22%2C%22publishSuccessImg%22%3A%22https%3A//img-home.csdnimg.cn/images/20240229024608.png%22%2C%22articleNum%22%3A0%2C%22type%22%3A0%2C%22oldUser%22%3Afalse%2C%22useSeven%22%3Atrue%2C%22oldFullVersion%22%3Afalse%2C%22userName%22%3A%22qq_60413090%22%7D; _clck=cp292p%5E2%5Efz0%5E0%5E1994; _clsk=10ei8v6%5E1756870904891%5E1%5E0%5Eo.clarity.ms%2Fcollect; c_pref=https%3A//www.csdn.net/; c_ref=https%3A//mp.csdn.net/; c_page_id=default; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1756870952; log_Id_pv=4; log_Id_view=144; log_Id_click=6; dc_tos=t1zucg";
        //String cookie = "dc_sid=21f5bb4de98e88049f5292ae3593f70b; fid=20_48032897523-1743237693494-081936; uuid_tt_dd=11_22498201076-1743237693495-184730; dc_session_id=11_1743237693495.122370; c_first_ref=default; c_first_page=https%3A//editor.csdn.net/md/%3Fnot_checkout%3D1%26spm%3D1000.2115.3001.5352; c_dsid=11_1743237693496.168400; c_segment=10; c_page_id=default; loginbox_strategy=%7B%22taskId%22%3A349%7D; SESSION=c25265f3-b98f-477c-9bb2-fba89ede5d12; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1743237695; HMACCOUNT=BF81B60489032C87; creative_btn_mp=1; hide_login=1; UserName=weixin_46755643; UserInfo=023fca3559d24cbabca34a712d75f1d0; UserToken=023fca3559d24cbabca34a712d75f1d0; UserNick=%E5%B0%8F%E5%82%85%E5%93%A5%E7%9A%84%E7%A0%81%E4%BB%94; AU=1C3; UN=weixin_46755643; BT=1743237720298; p_uid=U010000; creativeSetApiNew=%7B%22toolbarImg%22%3A%22https%3A//img-home.csdnimg.cn/images/20231011044944.png%22%2C%22publishSuccessImg%22%3A%22https%3A//img-home.csdnimg.cn/images/20240229024608.png%22%2C%22articleNum%22%3A0%2C%22type%22%3A0%2C%22oldUser%22%3Afalse%2C%22useSeven%22%3Atrue%2C%22oldFullVersion%22%3Afalse%2C%22userName%22%3A%22weixin_46755643%22%7D; csdn_newcert_weixin_46755643=1; c_pref=https%3A//editor.csdn.net/; c_ref=https%3A//mp.csdn.net/mp_blog/creation/success/146694967; log_Id_pv=8; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1743237915; dc_tos=stvmzg; log_Id_view=199; log_Id_click=12";
        
        SaveArticleRequest request = new SaveArticleRequest();
        request.setTitle("测试文章标题2");
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


            Call<ArticleResponse> call = icsdnService.saveArticle(cookie, request);
            Response<ArticleResponse> response = call.execute();
            
            if (response.isSuccessful() && response.body() != null) {
                ArticleResponse result = response.body();
                log.info("Article saved successfully!");
                log.info("Response code: {}", result.getCode());
                log.info("Trace ID: {}", result.getTraceId());
                log.info("Message: {}", result.getMsg());
                
                if (result.getData() != null) {
//                    log.info("Article ID: {}", result.getData().getId());
//                    log.info("Article URL: {}", result.getData().getUrl());
//                    log.info("Article Title: {}", result.getData().getTitle());
//                    log.info("Article Description: {}", result.getData().getDescription());
//                    log.info("QR Code: {}", result.getData().getQrcode());
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

    @Test
    public void test_domain_saveArticle() throws IOException {
        String json = "{\"content\":\"<h2>场景：</h2>\\n<p>在某互联网大厂的面试室，一位严肃的面试官正准备提问，而对面坐着一位看似紧张却又想显得轻松的程序员小张。</p>\\n<p><strong>面试官</strong>：我们先来聊聊Java核心知识。第一个问题，Java中的JVM是如何管理内存的？</p>\\n<p><strong>程序员小张</strong>：哦，这个简单！JVM就像一个巨大的购物车，负责把所有的变量都放进去，呃……然后就……管理起来？</p>\\n<p><strong>面试官</strong>：嗯，第二个问题，请说说HashMap的工作原理。</p>\\n<p><strong>程序员小张</strong>：HashMap嘛，就是……呃，一个很大的箱子，大家都往里面扔东西，有时候会打架……</p>\\n<p><strong>面试官</strong>：那么第三个问题，能不能讲讲Spring和SpringBoot的区别？</p>\\n<p><strong>程序员小张</strong>：Spring是……呃，春天？SpringBoot就是穿靴子的春天嘛！哈哈……</p>\\n<p><strong>面试官</strong>：好，今天的问题就问到这里。回去等通知吧。</p>\\n<h2>答案解析：</h2>\\n<ol>\\n<li>\\n<p><strong>JVM内存管理</strong>：JVM内存管理包括堆内存和栈内存，堆内存用于存储对象实例，栈内存用于执行线程时的栈帧。</p>\\n</li>\\n<li>\\n<p><strong>HashMap原理</strong>：HashMap通过哈希函数将键映射到对应的值，并通过链表解决哈希冲突。</p>\\n</li>\\n<li>\\n<p><strong>Spring与SpringBoot区别</strong>：Spring是一个大型应用框架，而SpringBoot是基于Spring的快速开发套件，简化了Spring应用的配置。</p>\\n</li>\\n</ol>\\n\",\"cover_images\":[],\"cover_type\":0,\"description\":\"在互联网大厂的面试中，严肃的面试官与搞笑的程序员上演了一场精彩的对话。面试官提出Java核心知识、HashMap、Spring等问题，程序员则用幽默的方式作答。本文不仅展现了轻松的面试氛围，还附上了详细的技术问题答案解析，帮助读者更好地理解相关知识。\",\"is_new\":1,\"level\":\"0\",\"markdowncontent\":\"## 场景：\\n\\n在某互联网大厂的面试室，一位严肃的面试官正准备提问，而对面坐着一位看似紧张却又想显得轻松的程序员小张。\\n\\n**面试官**  ：我们先来聊聊Java核心知识。第一个问题，Java中的JVM是如何管理内存的？\\n\\n**程序员小张**  ：哦，这个简单！JVM就像一个巨大的购物车，负责把所有的变量都放进去，呃……然后就……管理起来？\\n\\n**面试官**  ：嗯，第二个问题，请说说HashMap的工作原理。\\n\\n**程序员小张**  ：HashMap嘛，就是……呃，一个很大的箱子，大家都往里面扔东西，有时候会打架……\\n\\n**面试官**  ：那么第三个问题，能不能讲讲Spring和SpringBoot的区别？\\n\\n**程序员小张**  ：Spring是……呃，春天？SpringBoot就是穿靴子的春天嘛！哈哈……\\n\\n**面试官**  ：好，今天的问题就问到这里。回去等通知吧。\\n\\n## 答案解析：\\n\\n1. **JVM内存管理**  ：JVM内存管理包括堆内存和栈内存，堆内存用于存储对象实例，栈内存用于执行线程时的栈帧。\\n\\n2. **HashMap原理**  ：HashMap通过哈希函数将键映射到对应的值，并通过链表解决哈希冲突。\\n\\n3. **Spring与SpringBoot区别**  ：Spring是一个大型应用框架，而SpringBoot是基于Spring的快速开发套件，简化了Spring应用的配置。\",\"not_auto_saved\":\"0\",\"pubStatus\":\"draft\",\"readType\":\"public\",\"resource_id\":\"\",\"resource_url\":\"\",\"source\":\"pc_mdeditor\",\"status\":0,\"sync_git_code\":0,\"tags\":\"Java,面试,互联网,程序员,Spring,SpringBoot,HashMap,JVM\",\"title\":\"互联网大厂Java面试：严肃面试官与搞笑程序员的对决\",\"vote_id\":0}";
        ArticleFunctionRequest request = JSON.parseObject(json, ArticleFunctionRequest.class);
        ArticleResponse response = csdnAutoArticleService.saveArticle(request);
        log.info("测试结果:{}", JSON.toJSONString(response));
    }

    public static void main(String[] args) {
        String json = "{\"content\":\"<h2>场景：</h2>\\n<p>在某互联网大厂的面试室，一位严肃的面试官正准备提问，而对面坐着一位看似紧张却又想显得轻松的程序员小张。</p>\\n<p><strong>面试官</strong>：我们先来聊聊Java核心知识。第一个问题，Java中的JVM是如何管理内存的？</p>\\n<p><strong>程序员小张</strong>：哦，这个简单！JVM就像一个巨大的购物车，负责把所有的变量都放进去，呃……然后就……管理起来？</p>\\n<p><strong>面试官</strong>：嗯，第二个问题，请说说HashMap的工作原理。</p>\\n<p><strong>程序员小张</strong>：HashMap嘛，就是……呃，一个很大的箱子，大家都往里面扔东西，有时候会打架……</p>\\n<p><strong>面试官</strong>：那么第三个问题，能不能讲讲Spring和SpringBoot的区别？</p>\\n<p><strong>程序员小张</strong>：Spring是……呃，春天？SpringBoot就是穿靴子的春天嘛！哈哈……</p>\\n<p><strong>面试官</strong>：好，今天的问题就问到这里。回去等通知吧。</p>\\n<h2>答案解析：</h2>\\n<ol>\\n<li>\\n<p><strong>JVM内存管理</strong>：JVM内存管理包括堆内存和栈内存，堆内存用于存储对象实例，栈内存用于执行线程时的栈帧。</p>\\n</li>\\n<li>\\n<p><strong>HashMap原理</strong>：HashMap通过哈希函数将键映射到对应的值，并通过链表解决哈希冲突。</p>\\n</li>\\n<li>\\n<p><strong>Spring与SpringBoot区别</strong>：Spring是一个大型应用框架，而SpringBoot是基于Spring的快速开发套件，简化了Spring应用的配置。</p>\\n</li>\\n</ol>\\n\",\"cover_images\":[],\"cover_type\":0,\"description\":\"在互联网大厂的面试中，严肃的面试官与搞笑的程序员上演了一场精彩的对话。面试官提出Java核心知识、HashMap、Spring等问题，程序员则用幽默的方式作答。本文不仅展现了轻松的面试氛围，还附上了详细的技术问题答案解析，帮助读者更好地理解相关知识。\",\"is_new\":1,\"level\":\"0\",\"markdowncontent\":\"## 场景：\\n\\n在某互联网大厂的面试室，一位严肃的面试官正准备提问，而对面坐着一位看似紧张却又想显得轻松的程序员小张。\\n\\n**面试官**  ：我们先来聊聊Java核心知识。第一个问题，Java中的JVM是如何管理内存的？\\n\\n**程序员小张**  ：哦，这个简单！JVM就像一个巨大的购物车，负责把所有的变量都放进去，呃……然后就……管理起来？\\n\\n**面试官**  ：嗯，第二个问题，请说说HashMap的工作原理。\\n\\n**程序员小张**  ：HashMap嘛，就是……呃，一个很大的箱子，大家都往里面扔东西，有时候会打架……\\n\\n**面试官**  ：那么第三个问题，能不能讲讲Spring和SpringBoot的区别？\\n\\n**程序员小张**  ：Spring是……呃，春天？SpringBoot就是穿靴子的春天嘛！哈哈……\\n\\n**面试官**  ：好，今天的问题就问到这里。回去等通知吧。\\n\\n## 答案解析：\\n\\n1. **JVM内存管理**  ：JVM内存管理包括堆内存和栈内存，堆内存用于存储对象实例，栈内存用于执行线程时的栈帧。\\n\\n2. **HashMap原理**  ：HashMap通过哈希函数将键映射到对应的值，并通过链表解决哈希冲突。\\n\\n3. **Spring与SpringBoot区别**  ：Spring是一个大型应用框架，而SpringBoot是基于Spring的快速开发套件，简化了Spring应用的配置。\",\"not_auto_saved\":\"0\",\"pubStatus\":\"draft\",\"readType\":\"public\",\"resource_id\":\"\",\"resource_url\":\"\",\"source\":\"pc_mdeditor\",\"status\":0,\"sync_git_code\":0,\"tags\":\"Java,面试,互联网,程序员,Spring,SpringBoot,HashMap,JVM\",\"title\":\"互联网大厂Java面试：严肃面试官与搞笑程序员的对决\",\"vote_id\":0}";
        ArticleFunctionRequest request = JSON.parseObject(json, ArticleFunctionRequest.class);
        System.out.println( request);
    }
}
