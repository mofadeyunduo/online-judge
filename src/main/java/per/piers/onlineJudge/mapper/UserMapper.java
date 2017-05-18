package per.piers.onlineJudge.mapper;

import org.apache.ibatis.annotations.Param;
import per.piers.onlineJudge.model.User;

/**
 * Created by Piers on 2017/5/8.
 */
public interface UserMapper {

    public int insertUser(@Param("user")User user);

    public int updateUser(@Param("user")User user);

    public int deleteUser(@Param("user") User user);

    public User selectUser(@Param("user") User user);

}
