package com.back.domain.wiseSaying.repository;

import com.back.WiseSaying;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class WiseSayingRepository {
    private final List<WiseSaying> wiseSayings = new ArrayList<>();
    private int lastId = 0;
    
    public List<WiseSaying> findForList() {
        return Collections.unmodifiableList(wiseSayings.reversed());
    }

    public void save(WiseSaying wiseSaying) {
        if (wiseSaying.isNew()) {
            // 최초 저장 시
            wiseSaying.setId(++lastId);
            LocalDateTime now = LocalDateTime.now();
            wiseSaying.setCreateDate(now);
            wiseSaying.setModifyDate(now);
            wiseSayings.add(wiseSaying);
        } else {
            // 수정
            wiseSaying.setModifyDate(LocalDateTime.now());
        }
    }

    public WiseSaying findById(int id) {
        return wiseSayings.stream()
                .filter(ws -> ws.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private int findIndexById(int id) {
        return IntStream.range(0, wiseSayings.size())
                .filter(i -> wiseSayings.get(i).getId() == id)
                .findFirst()
                .orElse(-1);
    }

    public boolean deleteById(int id) {
        int index = findIndexById(id);

        if(index == -1) return false;

        wiseSayings.remove(index);

        return true;
    }
}
