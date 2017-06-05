package per.piers.onlineJudge.mapper;

import org.apache.ibatis.annotations.Param;
import per.piers.onlineJudge.model.Question;

public interface QuestionMapper {

    public Question[] selectQuestion(@Param("question") Question question);

    public int insertQuestion(@Param("question")Question question);

    public int deleteQuestion(@Param("question")Question question);

    public int updateQuestion(@Param("question")Question question);

}
