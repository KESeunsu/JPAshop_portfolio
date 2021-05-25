package practiceboard.one.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import practiceboard.one.domain.Member;
import practiceboard.one.repository.MemberRepository;
import javax.persistence.EntityManager;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;
    @Autowired
    private EntityManager em;

    @Test
    public void 가입() throws Exception{
        //given
        Member member=new Member();
        member.setName("member");

        //when
        Long saveId=memberService.join(member);

        //then
        assertEquals(member,memberRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복회원예외() throws Exception{
        //given
        Member member1=new Member();
        member1.setName("member");
        Member member2=new Member();
        member2.setName("member");

        //when
        memberService.join(member1);
        memberService.join(member2);//여기서 터지는 예외가 (expected = IllegalStateException.class)이 예외가 발생한다면 잘 굴러가는 테스트케이스라는것
        //then
        fail();//만약 memberService.join(member2);위에 예외 터지는게 정상...이게 이곳으로
        // 넘어가서 와버리면 안된다!! fail을 뜨도록한다
    }
}