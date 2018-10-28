package org.smartinrub.reactivespringdemo.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.smartinrub.reactivespringdemo.model.Member;
import org.smartinrub.reactivespringdemo.repository.MemberRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/members")
@AllArgsConstructor
public class MemberController {

    private final MemberRepository repository;

    @GetMapping("/{id}")
    public Mono<Member> getMemberById(@PathVariable("id") String id) {
        return repository.findById(id);
    }

    @GetMapping("/{name}/ids")
    public Flux<String> getAllMembersByName(@PathVariable("name") String name) {
        return repository.findAllByNameIgnoreCase(name).map(Member::getId);
    }

    @GetMapping
    public Flux<Member> getAllMembers() {
        return repository.findAll();
    }

}
