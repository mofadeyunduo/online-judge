package per.piers.onlineJudge.mapper;

import org.apache.ibatis.annotations.Param;
import per.piers.onlineJudge.model.TestInfo;

public interface TestInfoMapper {

    public int insertTestInfo(TestInfo testInfo);

}
