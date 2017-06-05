package per.piers.onlineJudge.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import per.piers.onlineJudge.model.Sex;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SexTypeHandler extends BaseTypeHandler<Sex> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Sex sex, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, sex.getId());
    }

    @Override
    public Sex getNullableResult(ResultSet resultSet, String s) throws SQLException {
        int sex = resultSet.getInt(s);
        if (resultSet.wasNull()) {
            return null;
        } else {
            return Sex.getSexType(sex);
        }
    }

    @Override
    public Sex getNullableResult(ResultSet resultSet, int i) throws SQLException {
        int sex = resultSet.getInt(i);
        if (resultSet.wasNull()) {
            return null;
        } else {
            return Sex.getSexType(i);
        }
    }

    @Override
    public Sex getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        int sex = callableStatement.getInt(i);
        if (callableStatement.wasNull()) {
            return null;
        } else {
            return Sex.getSexType(i);
        }
    }

}
