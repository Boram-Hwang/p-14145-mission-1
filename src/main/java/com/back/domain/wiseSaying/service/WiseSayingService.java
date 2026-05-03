package com.back.domain.wiseSaying.service;

import com.back.WiseSaying;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WiseSayingService {

    private final WiseSayingRepository wiseSayingRepository;

    public WiseSayingService() {
        this.wiseSayingRepository = new WiseSayingRepository();
    }

    // 역순
    public List<WiseSaying> findForList() {
        return wiseSayingRepository.findForList();
    }

    public WiseSaying write(String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(content, author);

        wiseSayingRepository.save(wiseSaying);

        return wiseSaying;
    }

    // id 찾기
    public WiseSaying findById(int id) {
        return wiseSayingRepository.findById(id);
    }


    public boolean delete(int id) {
        return wiseSayingRepository.deleteById(id);
    }

    public void modify(WiseSaying findWiseSaying, String newContent, String newAuthor) {
        // 데이터 변경
        findWiseSaying.setContent(newContent);
        findWiseSaying.setAuthor(newAuthor);

        wiseSayingRepository.save(findWiseSaying);
    }
}
