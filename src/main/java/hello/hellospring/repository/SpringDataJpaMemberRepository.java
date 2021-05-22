package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{

    //select m from Member m where m.name= ? 으로 해준다 자동으로 ㄷㄷ
    @Override
    public Optional<Member> findByName(String name);
}
