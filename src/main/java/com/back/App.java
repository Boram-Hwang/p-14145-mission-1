package com.back;

import java.util.*;


public class App {
    private final Scanner scanner = new Scanner(System.in);
    private final List<WiseSaying> wiseSayings = new ArrayList<>();
    private int lastId = 0;

    public void run() {

        System.out.println("== 명언 앱 ==");

        while(true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();
            Rq rq = new Rq(cmd);

            switch (rq.getActionName()) {
                case "종료" -> {
                    System.out.println("프로그램이 종료합니다.");
                    return;
                }
                case "목록" -> actionList();
                case "등록" -> actionWrite();
                case "삭제" -> actionDelete(rq);
                case "수정" -> actionModify(rq);
            }
        }
    }

    private void actionModify(Rq rq) {
        int id = rq.getParamAsInt("id", -1);

        // 예외처리
        if(id == -1) {
            System.out.println("id를 입력해주세요.");
            return;
        }

        WiseSaying findWiseSaying = findById(id);

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

        update(findWiseSaying, newContent, newAuthor);
        System.out.printf("%d번 명언이 수정되었습니다.\n", id);
    }

    private void update(WiseSaying findWiseSaying, String newContent, String newAuthor) {
        // 데이터 변경
        findWiseSaying.setContent(newContent);
        findWiseSaying.setAuthor(newAuthor);
    }

    private void actionWrite() {
        System.out.print("명언 : ");
        String content = scanner.nextLine();
        System.out.print("작가 : ");
        String author = scanner.nextLine();

        WiseSaying wiseSaying = write(content, author);

        System.out.println("%d번 명언이 등록되었습니다.".formatted(wiseSaying.getId()));
    }

    private WiseSaying write(String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(++lastId, content, author);

        wiseSayings.add(wiseSaying);

        return wiseSaying;
    }

    private void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("-----------------");

        List<WiseSaying> forListWiseSayings = findAllReverse();

        for(WiseSaying ws : forListWiseSayings) {
            System.out.printf("%d / %s / %s\n", ws.getId(), ws.getAuthor(), ws.getContent());
        }
    }

    // 역순
    private List<WiseSaying> findAllReverse() {
        return Collections.unmodifiableList(wiseSayings.reversed());
    }

    private void actionDelete(Rq rq) {
        int id = rq.getParamAsInt("id", -1);

        if (id == -1) {
            System.out.println("id를 입력해주세요.");
            return;
        }

        // 존재 여부 확인
        boolean deleted = delete(id);
        // 없을 경우
        if(!deleted) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }
        System.out.printf("%d번 명언이 삭제되었습니다.\n".formatted(id));
    }

    // id 찾기
    private WiseSaying findById(int id) {
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

    private boolean delete(int id) {
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
