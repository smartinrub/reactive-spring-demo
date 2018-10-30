package org.smartinrub.reactivespringdemo.service;

import org.smartinrub.reactivespringdemo.model.Member;
import reactor.core.publisher.Flux;

public interface MemberService {

    Flux<Member> findAllByNameIgnoreCase(String name) throws InterruptedException;
}
