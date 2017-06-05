package per.piers.onlineJudge.mapper;

import org.apache.ibatis.annotations.Param;
import per.piers.onlineJudge.model.InputOutput;
import per.piers.onlineJudge.model.TestData;

public interface TestDataMapper {

    public TestData[] selectTestData(@Param("testData") TestData testData);

    public int insertTestData(@Param("inputOutput") InputOutput inputOutput);

    public int deleteTestData(@Param("qid") int qid);

}
