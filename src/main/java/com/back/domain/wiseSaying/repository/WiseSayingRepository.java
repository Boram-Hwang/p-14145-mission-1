package com.back.domain.wiseSaying.repository;

import com.back.WiseSaying;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WiseSayingRepository {
    private final List<WiseSaying> wiseSayings = new ArrayList<>();
    private int lastId = 0;
    
    public List<WiseSaying> findForList() {
        return Collections.unmodifiableList(wiseSayings.reversed());
    }

    public void save(WiseSaying wiseSaying) {
        if (wiseSaying.getId() == 0) {
            wiseSaying.setId(++lastId);
            wiseSayings.add(wiseSaying);
        }
    }

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

    public boolean deleteById(int id) {
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
}
