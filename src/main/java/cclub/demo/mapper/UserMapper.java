package cclub.demo.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    /**
     *
     * @param phone
     * 当用户的phone没有注册时执行该sql
     */
    @Insert("insert into user(user_id) values(#{phone})")
    void login(String phone);


    /**
     *
     * @param phone
     * @return
     * 查询当前用户是否已经注册
     */
    @Select("select user_company from user where user_id=#{phone}")
    String getUserCompany(String phone);



    /**
     *
     * @param user_id
     * @param user_company
     * 给新注册的用户初始化对应的团队
     */
    @Update("update user set user_company=#{user_company} where user_id=#{user_id}")
    void init_company(String user_id,String user_company);
}
