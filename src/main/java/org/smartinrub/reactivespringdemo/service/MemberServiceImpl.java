package org.smartinrub.reactivespringdemo.service;

import org.smartinrub.reactivespringdemo.model.Member;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MemberServiceImpl implements MemberService {

    private static final Scheduler SCHEDULER = Schedulers.parallel();

    private final List<Member> members;

    public MemberServiceImpl() {
        this.members = Stream.of(
                new Member("1", "Sergio"),
                new Member("2", "Antonio"),
                new Member("100", "Juan")
        ).collect(Collectors.toList());
    }

    @Override
    public Flux<Member> findAllByNameIgnoreCase(String name) {
        return Flux.fromIterable(members).subscribeOn(SCHEDULER);
    }
}
