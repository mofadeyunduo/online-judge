package per.piers.onlineJudge.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import per.piers.onlineJudge.mapper.*;
import per.piers.onlineJudge.model.*;

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
            sqlSession.commit();
            if (line != 1) sqlSession.rollback();
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

}