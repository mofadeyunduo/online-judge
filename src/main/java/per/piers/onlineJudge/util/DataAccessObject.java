package per.piers.onlineJudge.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import per.piers.onlineJudge.mapper.*;
import per.piers.onlineJudge.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class DataAccessObject {

    private SqlSessionFactory sqlSessionFactory;

    public DataAccessObject(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public User selectUser(User user) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            return mapper.selectUser(user);
            //return sqlSession.selectOne("per.piers.onlineJudge.mapper.selectUserByEmail",  email);
        }
    }

    public int insertUser(User user) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int line = mapper.insertUser(user);
            if (line != 1) sqlSession.rollback();
            sqlSession.commit();
            return line;
        }
    }

    public int updateUser(User user) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int line = mapper.updateUser(user);
            sqlSession.commit();
            if (line != 1) sqlSession.rollback();
            return line;
        }
    }

    public Question[] selectQuestion(Question question) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            QuestionMapper mapper = sqlSession.getMapper(QuestionMapper.class);
            return mapper.selectQuestion(question);
        }
    }

    public Category[] selectCategory(Category category) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
            return mapper.selectCategory(category);
        }
    }

    public TestData[] selectTestData(TestData testData) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            TestDataMapper mapper = sqlSession.getMapper(TestDataMapper.class);
            return mapper.selectTestData(testData);
        }
    }

    public int insertTestInfo(TestInfo testInfo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            TestInfoMapper mapper = sqlSession.getMapper(TestInfoMapper.class);
            int line = mapper.insertTestInfo(testInfo);
            sqlSession.commit();
            if (line != 1) sqlSession.rollback();
            return line;
        }
    }

    public TestInfo[] selectAllScore(int uid) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ScoreMapper mapper = sqlSession.getMapper(ScoreMapper.class);
            return mapper.selectAllScore(uid);
        }
    }

    public Score selectSumScoreAndRank(int uid) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ScoreMapper mapper = sqlSession.getMapper(ScoreMapper.class);
            return mapper.selectSumScoreAndRank(uid);
        }
    }

    public int insertCategory(Category category) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
            int line = mapper.insertCategory(category);
            sqlSession.commit();
            if (line != 1) sqlSession.rollback();
            return line;
        }
    }

    public int updateCategory(Category category) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
            int line = mapper.updateCategory(category);
            sqlSession.commit();
            if (line != 1) sqlSession.rollback();
            return line;
        }
    }

    public int deleteCategory(Category category) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
            int line = mapper.deleteCategory(category);
            sqlSession.commit();
            if (line != 1) sqlSession.rollback();
            return line;
        }
    }

    public int insertQuestion(Question question) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            QuestionMapper mapper = sqlSession.getMapper(QuestionMapper.class);
            int line = mapper.insertQuestion(question);
            sqlSession.commit();
            if (line != 1) sqlSession.rollback();
            return line;
        }
    }

    public int updateQuestion(Question question) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            QuestionMapper mapper = sqlSession.getMapper(QuestionMapper.class);
            int line = mapper.updateQuestion(question);
            sqlSession.commit();
            if (line != 1) sqlSession.rollback();
            return line;
        }
    }

    public int deleteQuestion(Question question) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            QuestionMapper mapper = sqlSession.getMapper(QuestionMapper.class);
            int line = mapper.deleteQuestion(question);
            sqlSession.commit();
            if (line != 1) sqlSession.rollback();
            return line;
        }
    }

    public TestInfo[] selectTestInfo(TestInfo testInfo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            TestInfoMapper mapper = sqlSession.getMapper(TestInfoMapper.class);
            return mapper.selectTestInfo(testInfo);
        }
    }

    public AllScore[] selectPracticeAndUsualScoreFromAdmin(int uidAdmin, int cid) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ScoreMapper mapper = sqlSession.getMapper(ScoreMapper.class);
            return mapper.selectPracticeAndUsualScoreFromAdmin(uidAdmin, cid);
        }
    }

    public AllScore[] selectUsualScore(AllScore allScore) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ScoreMapper mapper = sqlSession.getMapper(ScoreMapper.class);
            return mapper.selectUsualScore(allScore);
        }
    }

    public int insertAdvisor(Advisor advisor) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            AdvisorMapper mapper = sqlSession.getMapper(AdvisorMapper.class);
            int line = mapper.insertAdvisor(advisor);
            sqlSession.commit();
            if (line != 1) sqlSession.rollback();
            return line;
        }
    }

    public int updateUsualScore(AllScore allScore) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ScoreMapper mapper = sqlSession.getMapper(ScoreMapper.class);
            int line = mapper.updateUsualScore(allScore);
            sqlSession.commit();
            return line;
        }
    }

    public HashMap<String, String> importUser(HashSet<String> emails, int uidAdmin) {
        HashMap<String, String> status = new HashMap<>();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            AdvisorMapper advisorMapper = sqlSession.getMapper(AdvisorMapper.class);
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            for (String email : emails) {
                User insertUser = new User();
                insertUser.setEmail(email);
                insertUser.setPassword("000000");
                insertUser.setEnabled(true);
                insertUser.setRole("user");
                User user = selectUser(insertUser);
                int uidUser;
                if (user == null) {
                    status.put(email, "创建用户");
                    int line = userMapper.insertUser(insertUser);
                    if (line != 1) sqlSession.rollback();
                    uidUser = insertUser.getId();
                } else {
                    status.put(email, "用户存在");
                    uidUser = user.getId();
                }
                Advisor insertAdvisor = new Advisor();
                insertAdvisor.setUidAdmin(uidAdmin);
                insertAdvisor.setUidUser(uidUser);
                Advisor[] advisors = advisorMapper.selectAdvisor(insertAdvisor);
                if (advisors.length == 1) {
                    status.replace(email, status.get(email) + "，已经导入的用户");
                } else if (advisors.length == 0) {
                    int line = advisorMapper.insertAdvisor(insertAdvisor);
                    if (line != 1) sqlSession.rollback();
                    status.replace(email, status.get(email) + "，导入用户成功");
                }
            }
            sqlSession.commit();
        }
        return status;
    }

    public Advisor[] selectAdvisor(Advisor advisor) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            AdvisorMapper mapper = sqlSession.getMapper(AdvisorMapper.class);
            return mapper.selectAdvisor(advisor);
        }
    }

    public int insertTestData(InputOutput inputOutput) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            TestDataMapper mapper = sqlSession.getMapper(TestDataMapper.class);
            int line = mapper.insertTestData(inputOutput);
            sqlSession.commit();
            return line;
        }
    }

    public int deleteTestData(int qid) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            TestDataMapper mapper = sqlSession.getMapper(TestDataMapper.class);
            int line = mapper.deleteTestData(qid);
            sqlSession.commit();
            return line;
        }
    }

    public void importTestData(ArrayList<InputOutput> inputOutputs, int qid) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            TestDataMapper mapper = sqlSession.getMapper(TestDataMapper.class);
            mapper.deleteTestData(qid);
            for (InputOutput inputOutput : inputOutputs) {
                mapper.insertTestData(inputOutput);
            }
            sqlSession.commit();
        }
    }

}