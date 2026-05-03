package com.back.domain.wiseSaying.service;

import com.back.WiseSaying;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WiseSayingService {
    private final List<WiseSaying> wiseSayings = new ArrayList<>();
    private int lastId = 0;
    // 역순
    public List<WiseSaying> findAllReverse() {
        return Collections.unmodifiableList(wiseSayings.reversed());
    }

    public WiseSaying write(String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(++lastId, content, author);

        wiseSayings.add(wiseSaying);

        return wiseSaying;
    }

    // id 찾기
    public WiseSaying findById(int id) {
        for(WiseSaying ws : wiseSayings) {
            if(ws.getId() == id) return ws;
        }
        return null;
    }

    private int findIndexById(int id) {
        for(int i = 0; i<= wiseSayings.size(); i++) {
            if(wiseSayings.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public boolean delete(int id) {
        int deleteIndex = -1;
        // id가 같은 명언을 찾기
        for(int i = 0; i <=wiseSayings.size() - 1; i++) {
            if(wiseSayings.get(i).getId() == id) {
                deleteIndex = i;
                break;
            }
        }

        if(deleteIndex != -1) {
            // 삭제
            wiseSayings.remove(deleteIndex);
            return true;
        }
        return false;
    }

    public void update(WiseSaying findWiseSaying, String newContent, String newAuthor) {
        // 데이터 변경
        findWiseSaying.setContent(newContent);
        findWiseSaying.setAuthor(newAuthor);
    }
}
