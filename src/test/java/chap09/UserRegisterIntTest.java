package chap09;

import chap07.join.UserRegister;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import static org.junit.jupiter.api.Assertions.*;

class UserRegisterIntTest {
    @Autowired
    private UserRegister register;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void 동일_ID가_이미_존재하면_익셉션(){
        //상황 : INSERT 쿼리 실행
        jdbcTemplate.update("insert into  user values (?,?,?)"+
                "on duplicate key updte password = ?, email =?",
                "cbk","pw","cbk@cbk.com","pw","cbk@cbk.com");

        //실행, 결과 확인
        assertThrows(DuqIdExcepion.class,
                ()-> register.register("cbk","strongpw","email@email.com"));
    }

    @Test
    void 존재하지_않으면_저장함(){
        //상황 : DELETE쿼리 실행
        jdbcTemplate.update("delete from user where id=?", "cbk");
        //실행
        register.register("cbk","strongpw","email@email.com");
        //결과 확인 : SELECT 쿼리 실행
        SqlRowSet rs = jdbcTemplate.queryForRowSet("select from user where id = ?, ","cbk");
        rs.next();
        assertEquals("email@email.com",rs.getString("email"));
    }



}