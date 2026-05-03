package com.back.domain.wiseSaying.controller;

import com.back.AppContext;
import com.back.Rq;
import com.back.WiseSaying;
import com.back.domain.wiseSaying.service.WiseSayingService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class WiseSayingController {
    private final Scanner scanner = AppContext.scanner;
    private final WiseSayingService wiseSayingService = AppContext.wiseSayingService;

    public void actionModify(Rq rq) {
        int id = rq.getParamAsInt("id", -1);

        // 예외처리
        if(id == -1) {
            System.out.println("id를 입력해주세요.");
            return;
        }

        WiseSaying findWiseSaying = wiseSayingService.findById(id);

        // id 찾기
        // 없을 경우
        if(findWiseSaying == null) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }
        // 기존 데이터 출력 및 새 데이터 입력
        System.out.println("기존 명언 : " + findWiseSaying.getContent());
        System.out.print("명언 : ");
        String newContent = scanner.nextLine();

        System.out.println("기존 작가 : " + findWiseSaying.getAuthor());
        System.out.print("작가 : ");
        String newAuthor = scanner.nextLine();

        wiseSayingService.modify(findWiseSaying, newContent, newAuthor);
        System.out.printf("%d번 명언이 수정되었습니다.\n", id);
    }


    public void actionWrite() {
        System.out.print("명언 : ");
        String content = scanner.nextLine();
        System.out.print("작가 : ");
        String author = scanner.nextLine();

        WiseSaying wiseSaying = wiseSayingService.write(content, author);

        System.out.println("%d번 명언이 등록되었습니다.".formatted(wiseSaying.getId()));
    }

    public void actionList() {
        System.out.println("번호 / 작성날짜 / 수정날짜 / 작가 / 명언");
        System.out.println("-----------------------------");

        List<WiseSaying> forListWiseSayings = wiseSayingService.findForList();

        for(WiseSaying ws : forListWiseSayings) {
            System.out.printf(
                    "%d / %s / %s / %s / %s\n",
                    ws.getId(),
                    ws.getCreateDate(),
                    ws.getModifyDate(),
                    ws.getAuthor(),
                    ws.getContent()
            );
        }
    }

    public void actionDelete(Rq rq) {
        int id = rq.getParamAsInt("id", -1);

        if (id == -1) {
            System.out.println("id를 입력해주세요.");
            return;
        }

        // 존재 여부 확인
        boolean deleted = wiseSayingService.delete(id);
        // 없을 경우
        if(!deleted) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }
        System.out.printf("%d번 명언이 삭제되었습니다.\n".formatted(id));
    }

}
