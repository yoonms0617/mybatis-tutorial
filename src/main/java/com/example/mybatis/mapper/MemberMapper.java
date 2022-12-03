package com.example.mybatis.mapper;

import com.example.mybatis.model.Member;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MemberMapper {

    @Insert("INSERT INTO MEMBER (name, email) VALUES (#{member.name}, #{member.email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(@Param("member") Member member);

    @Select("SELECT * FROM MEMBER AS m WHERE m.id = #{id}")
    Member findById(@Param("id") Long id);

    @Select("SELECT * FROM MEMBER AS m WHERE m.email = #{email}")
    Member findByEmail(@Param("email") String email);

    @Select("SELECT EXISTS(SELECT 1 FROM MEMBER AS m WHERE m.email = #{email})")
    boolean isExistsEmail(@Param("email") String email);

    @Select("SELECT * FROM MEMBER;")
    List<Member> findAll();

    @Update("UPDATE MEMBER AS m SET name = #{member.name}, email = #{member.email} WHERE m.id = #{member.id}")
    void update(@Param("member") Member member);

    @Delete("DELETE FROM MEMBER AS m WHERE m.id = #{id}")
    void delete(@Param("id") Long id);

}
