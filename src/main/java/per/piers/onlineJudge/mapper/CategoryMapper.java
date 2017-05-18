package per.piers.onlineJudge.mapper;

import org.apache.ibatis.annotations.Param;
import per.piers.onlineJudge.model.Category;

public interface CategoryMapper {

    public Category[] selectCategory(@Param("category") Category category);

    public int insertCategory(@Param("category")Category category);

    public int deleteCategory(@Param("category")Category category);

    public int updateCategory(@Param("category")Category category);

}
