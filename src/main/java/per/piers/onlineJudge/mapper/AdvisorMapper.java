package per.piers.onlineJudge.mapper;

import org.apache.ibatis.annotations.Param;
import per.piers.onlineJudge.model.Advisor;
import per.piers.onlineJudge.model.Question;

public interface AdvisorMapper {

    public Advisor[] selectAdvisor(@Param("advisor") Advisor advisor);

    public int insertAdvisor(@Param("advisor") Advisor advisor);

    public int deleteAdvisor(@Param("id") int id);

}
