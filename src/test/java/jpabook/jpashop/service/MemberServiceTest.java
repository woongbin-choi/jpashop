package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입 테스트")
    public void joinTest() throws Exception {
        // given
        Member member = new Member();
        member.setName("kim");

        // when
        Long savedId = memberService.join(member);

        // then
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test
    @DisplayName("회원가입 중복 예외")
    public void duplicateTest() throws Exception {
        // given

        // when

        // then
    }

}