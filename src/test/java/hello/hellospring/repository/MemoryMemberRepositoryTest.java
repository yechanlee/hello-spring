package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();
    MemberService service2 = new MemberService(repository);
    //하나 끝날때마다 저장소나 공용데이터를 지워줘야한다.
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("yellong");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        //System.out.print("같은지 결과result = " + (result == member));
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("yelly1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("yelly2");
        repository.save(member2);

        Member result = repository.findByName("yelly1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("yelly1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("yelly2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void serviceTest(){
        Member member1 = new Member();
        member1.setName("yelly");
        service2.join(member1);
        Member member2 = new Member();
        member2.setName("yelly");
        service2.join(member2);


    }

}
