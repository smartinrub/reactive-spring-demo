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
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/members")
@AllArgsConstructor
public class MemberController {

    // Elastic creates new worker pools as needed, and reuse idle ones
    // Worker pools that stay idle for too long (default is 60s) are disposed
    private static final Scheduler SCHEDULER = Schedulers.parallel();

    private final MemberRepository repository;
    private final MemberServiceImpl service;

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Member>> getMemberById(@PathVariable("id") String id) {
        return repository.findById(id)
                .map(member -> ResponseEntity.ok(member))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/{name}/ids")
    public Mono<List<String>> getAllMembersByName(@PathVariable("name") String name) throws InterruptedException {
        return service.findAllByNameIgnoreCase(name)
                .map(Member::getId)
                .collectList() // will convert the Flux<Member> into a Mono<List<Member>>
                .flatMap(json ->  {
                    System.out.println("Thread: " + Thread.currentThread().getName());
                    return Mono.just(json);
                })
                .subscribeOn(SCHEDULER);
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
