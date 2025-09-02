package top.guxx.infrastructure.dao;


import org.apache.ibatis.annotations.Mapper;
import top.guxx.domain.database.model.DataBaseMcpRequest;
import top.guxx.domain.database.model.DataBaseMcpResp;

import java.util.List;

@Mapper
public interface UserInfoMapper {
    List<DataBaseMcpResp> findAll();

    List<DataBaseMcpResp> findAllByRequest(DataBaseMcpRequest request);
}
