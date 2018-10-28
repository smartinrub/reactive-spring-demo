package org.smartinrub.reactivespringdemo.controller;

import lombok.AllArgsConstructor;
import org.smartinrub.reactivespringdemo.model.Member;
import org.smartinrub.reactivespringdemo.repository.MemberRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/members")
@AllArgsConstructor
public class MemberController {

    private final MemberRepository repository;

    @GetMapping("/{id}")
    public Mono<Member> getMemberById(@PathVariable("id") String id) {
        return repository.findById(id);
    }

    @GetMapping
    public Flux<Member> getAllMembers() {
        return repository.findAll();
    }

}
