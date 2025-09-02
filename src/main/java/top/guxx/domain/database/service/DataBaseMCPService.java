package top.guxx.domain.database.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;
import top.guxx.domain.database.model.DataBaseMcpRequest;
import top.guxx.domain.database.model.DataBaseMcpResp;

import java.util.List;

@Slf4j
@Service
public class DataBaseMCPService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Tool(description = "查询数据库用户信息")
    public List<DataBaseMcpResp> queryDataBaseUserInfo(DataBaseMcpRequest request){
        log.info("queryDataBaseUserInfo: {}", request);
        List<DataBaseMcpResp> allByRequest = userInfoMapper.findAllByRequest(request);
        return allByRequest;
    }
}
