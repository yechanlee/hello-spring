package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
// 컨트롤 쉬프트 T 누르면 테스트 만들 수 있음.

//@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     * @param member
     * @return
     */
    public Long join(Member member){

        // optional 로 한번에 만들때는 컨트롤 알트 브이 누르면 된다.
        //컨트롤 알트 m 눌러서 extract method 해주고
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                //바로 옵셔널로 들어가지게 .붙여서 ㅇㅇ
            .ifPresent(m->{
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    /*
    전체회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
