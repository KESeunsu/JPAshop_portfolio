package practiceboard.one.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practiceboard.one.domain.Member;
import practiceboard.one.repository.MemberRepository;

import java.util.List;

@Service
//jpa의 데이터 변경은 무조건 영속성 컨텍스트안에서 이루어져야하기에 아래의 어노테이션이 꼭있어야함
@Transactional(readOnly = true)//조회만 하는것 데이터 수정을 할필요 없는곳에서는 읽기 전용이라고 써준다
public class MemberService {
    @Autowired//필드가 하나라서 이 어노테이션 사용함
    private MemberRepository memberRepository;
    //회원가입
    @Transactional//이부분만 데이터의 변경이 필요한 부분이라 읽기전용이 아니다
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }
//회원 중복검사 사실 이런식의 중복 검사를 해도 2개의 동시 insert가 일어나면 그냥 넘어갈수있기때문에 name에 유니크 조건을 걸어두는것이 더 맞는 방법일수 있다
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers=memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원의 이름입니다");
        }
    }
    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    //한 회원만 조회
    public Member findOne(Long id){
        return memberRepository.findOne(id);
    }
}
