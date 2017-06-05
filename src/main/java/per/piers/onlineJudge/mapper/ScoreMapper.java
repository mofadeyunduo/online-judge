package per.piers.onlineJudge.mapper;

import org.apache.ibatis.annotations.Param;
import per.piers.onlineJudge.model.AllScore;
import per.piers.onlineJudge.model.Score;
import per.piers.onlineJudge.model.TestInfo;

public interface ScoreMapper {

    public TestInfo[] selectAllScore(@Param("uid") int uid);

    public Score selectSumScoreAndRank(@Param("uid") int uid);

    public AllScore[] selectPracticeAndUsualScoreFromAdmin(@Param("uidAdmin") int uidAdmin, @Param("cid") int cid);

    public AllScore[] selectUsualScore(@Param("allScore") AllScore allScore);

    public int updateUsualScore(@Param("allScore") AllScore allScore);

}
