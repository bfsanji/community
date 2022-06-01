package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {

    /**
     *
     * @param userId 若userId为0 则不进行sql拼接
     * @param offset 起始行
     * @param limit 一页显示多少数据
     * @return
     */
    List<DiscussPost> selectDiscussPosts(int userId, int offset,int limit);
    //@Param注解用于给参数起别名
    // 如果只有一个参数，并且再<if>里使用，则必须加别名
    int selectDiscussPostRows(@Param("userId")int userId);


}
