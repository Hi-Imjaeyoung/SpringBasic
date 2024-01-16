package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcMemberRepository implements MemberRepo{
// DataSource는 DB와 JDBC에서 사용하는 DB연결 드라이버 객체
    @Autowired
    private DataSource dataSource;

    @Override
    public Member save(Member member) {
        try{
            Connection connection = dataSource.getConnection();
            String sql = "insert into member(name,email,pwd) values(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,member.getName());
            preparedStatement.setString(2,member.getEmail());
            preparedStatement.setString(3, member.getPwd());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return member;
    }

    @Override
    public List<Member> findAll() {
        List<Member> members = new ArrayList<>();
        try{
            Connection connection = dataSource.getConnection();
            String sql = "select * from member";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String pwd =resultSet.getString("pwd");;
                LocalDateTime now = resultSet.getTimestamp("create_time").toLocalDateTime();
                System.out.println(now.toString());
                Member member = new Member(name,email,pwd);
                member.setId(id);
                member.setCreateTime(now);
                members.add(member);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return members;
    }

    @Override
    public Optional<Member> findById(int id){
       Optional<Member> member = Optional.empty();
        try{
            Connection connection = dataSource.getConnection();
            String sql = "select * from member where id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,String.valueOf(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            member = Optional.of(new Member(resultSet.getString("name")
                    ,resultSet.getString("email")
                    ,resultSet.getString("pwd")));
            member.get().setId(resultSet.getInt("id"));
            member.get().setCreateTime(resultSet.getTimestamp("create_time").toLocalDateTime());
        }catch (SQLException e){
            e.printStackTrace();
        }
        return member;
    }
}
