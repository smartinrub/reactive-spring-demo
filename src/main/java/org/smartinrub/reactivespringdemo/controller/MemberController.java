package org.smartinrub.reactivespringdemo.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.smartinrub.reactivespringdemo.model.Member;
import org.smartinrub.reactivespringdemo.repository.MemberRepository;
import org.smartinrub.reactivespringdemo.service.MemberServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    
    private final MemberServiceImpl service;

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Member>> getMemberById(@PathVariable("id") String id) {
        return repository.findById(id)
                .map(member -> ResponseEntity.ok(member))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/{name}/ids")
    public Flux<String> getAllMembersByName(@PathVariable("name") String name) {
        return service.findAllByNameIgnoreCase(name).map(Member::getId);
    }

    @GetMapping
    public Flux<Member> getAllMembers() {
        return repository.findAll();
    }
    
    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Member> streamGetAllMembers() {
        return repository.findAll();
    }

}
