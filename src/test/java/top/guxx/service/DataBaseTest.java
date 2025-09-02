package top.guxx.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.guxx.Application;
import top.guxx.domain.database.model.DataBaseMcpResp;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class DataBaseTest {
    @Resource
    UserInfoMapper userInfoMapper;

    @Test
    public void query(){
        List<DataBaseMcpResp> allByRequest = userInfoMapper.findAll();
        System.out.println(allByRequest);
    }
}
