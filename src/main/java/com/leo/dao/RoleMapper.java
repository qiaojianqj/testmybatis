package com.leo.dao;

import com.leo.domain.Role;
import com.leo.domain.RoleKey;
import com.leo.domain.RoleParam;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface RoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbg.generated Wed Apr 29 10:35:51 CST 2020
     */
    @SelectProvider(type=RoleSqlProvider.class, method="countByExample")
    long countByExample(RoleParam example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbg.generated Wed Apr 29 10:35:51 CST 2020
     */
    @DeleteProvider(type=RoleSqlProvider.class, method="deleteByExample")
    int deleteByExample(RoleParam example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbg.generated Wed Apr 29 10:35:51 CST 2020
     */
    @Delete({
        "delete from role",
        "where seqid = #{seqid,jdbcType=INTEGER}",
          "and no = #{no,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(RoleKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbg.generated Wed Apr 29 10:35:51 CST 2020
     */
    @Insert({
        "insert into role (seqid, no, ",
        "name, type, remark)",
        "values (#{seqid,jdbcType=INTEGER}, #{no,jdbcType=VARCHAR}, ",
        "#{name,jdbcType=VARCHAR}, #{type,jdbcType=INTEGER}, #{remark,jdbcType=VARCHAR})"
    })
    int insert(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbg.generated Wed Apr 29 10:35:51 CST 2020
     */
    @InsertProvider(type=RoleSqlProvider.class, method="insertSelective")
    int insertSelective(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbg.generated Wed Apr 29 10:35:51 CST 2020
     */
    @SelectProvider(type=RoleSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="seqid", property="seqid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="no", property="no", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<Role> selectByExample(RoleParam example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbg.generated Wed Apr 29 10:35:51 CST 2020
     */
    @Select({
        "select",
        "seqid, no, name, type, remark",
        "from role",
        "where seqid = #{seqid,jdbcType=INTEGER}",
          "and no = #{no,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="seqid", property="seqid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="no", property="no", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    Role selectByPrimaryKey(RoleKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbg.generated Wed Apr 29 10:35:51 CST 2020
     */
    @UpdateProvider(type=RoleSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleParam example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbg.generated Wed Apr 29 10:35:51 CST 2020
     */
    @UpdateProvider(type=RoleSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Role record, @Param("example") RoleParam example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbg.generated Wed Apr 29 10:35:51 CST 2020
     */
    @UpdateProvider(type=RoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbg.generated Wed Apr 29 10:35:51 CST 2020
     */
    @Update({
        "update role",
        "set name = #{name,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=INTEGER},",
          "remark = #{remark,jdbcType=VARCHAR}",
        "where seqid = #{seqid,jdbcType=INTEGER}",
          "and no = #{no,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Role record);
}