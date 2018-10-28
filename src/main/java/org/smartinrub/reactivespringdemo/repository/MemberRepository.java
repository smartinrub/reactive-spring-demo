package org.smartinrub.reactivespringdemo.repository;

import org.smartinrub.reactivespringdemo.model.Member;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface MemberRepository extends ReactiveCrudRepository<Member, String> {

    @Query("{ 'name': {$regex : '^?0$', $options: 'i'}}")
    Flux<Member> findAllByNameIgnoreCase(String name);

}
