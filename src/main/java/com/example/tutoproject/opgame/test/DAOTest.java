package com.example.tutoproject.opgame.test;

import static org.junit.jupiter.api.Assertions.*;

import com.example.tutoproject.opgame.model.Member;
import com.example.tutoproject.opgame.model.MemberDAO;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class DAOTest {

    @Test
    public void testLoginAndLogout() {
        String id = "tester";
        String pw = "1234";


        MemberDAO dao = new MemberDAO();
        dao.initMember(); //테스트를 위해서 db초기화 원래는 하면 안됨

        Optional<Member> user = dao.login(id, pw);
        if (user.isEmpty()) {
            dao.join(id, pw);
            user = dao.login(id, pw);
        }

        assertEquals(id, user.get().getID());
        assertTrue(user.get().isLogined()); //dao에서 확인하거나 user애서 속성으로 갖게 하거나

        user = dao.logout(id);
        assertFalse(user.get().isLogined());
    }
}
