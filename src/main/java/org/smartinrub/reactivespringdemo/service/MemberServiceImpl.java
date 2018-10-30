package org.smartinrub.reactivespringdemo.service;

import lombok.AllArgsConstructor;
import org.smartinrub.reactivespringdemo.model.Member;
import org.smartinrub.reactivespringdemo.repository.MemberRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
    
    private final MemberRepository repository;

    @Override
    public Flux<Member> findAllByNameIgnoreCase(String name) {
        return repository.findAllByNameIgnoreCase(name);
    }
}
