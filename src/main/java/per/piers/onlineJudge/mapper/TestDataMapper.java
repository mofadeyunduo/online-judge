package per.piers.onlineJudge.mapper;

import org.apache.ibatis.annotations.Param;
import per.piers.onlineJudge.model.TestData;

public interface TestDataMapper {

    public TestData[] selectTestData(@Param("testData") TestData testData);

}
