package org.smartinrub.reactivespringdemo.repository;

import org.smartinrub.reactivespringdemo.model.Member;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MemberRepository extends ReactiveCrudRepository<Member, String> {

}
