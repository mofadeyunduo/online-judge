package per.piers.onlineJudge.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import per.piers.onlineJudge.model.Score;
import per.piers.onlineJudge.model.TestInfo;

public interface ScoreMapper {

   public TestInfo[] selectAllScore(@Param("uid") int uid);

   public Score selectSumScoreAndRank(@Param("uid") int uid);

}
