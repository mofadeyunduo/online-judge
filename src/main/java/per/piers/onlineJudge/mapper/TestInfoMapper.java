package per.piers.onlineJudge.mapper;

import org.apache.ibatis.annotations.Param;
import per.piers.onlineJudge.model.TestInfo;

public interface TestInfoMapper {

    public int insertTestInfo(@Param("testInfo") TestInfo testInfo);

    public TestInfo[] selectTestInfo(@Param("testInfo") TestInfo testInfo);

}
