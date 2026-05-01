package com.back;

import java.util.*;


public class App {
    private final Scanner scanner = new Scanner(System.in);
    private List<WiseSaying> wiseSayings = new ArrayList<>();
    private int lastId = 0;

    public void run() {

        System.out.println("== 명언 앱 ==");

        while(true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();

            if(cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                actionWrite();
            } else if (cmd.equals("목록")) {
                actionList();
            } else if (cmd.startsWith("삭제")) {
                actionDelete(cmd);
            } else if (cmd.startsWith("수정")) {
                actionModify(cmd);
            }
        }

    }

    private void actionModify(String cmd) {
        String[] cmdBites = cmd.split("=", 2);
//        int updateIndex = -1;

        // 예외처리
        if(cmdBites.length < 2 || cmdBites[1].isEmpty()) {
            System.out.println("id를 입력해주세요.");
            return;
        }

        int id = Integer.parseInt(cmdBites[1]);

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

    private void actionDelete(String cmd) {
        String[] cmdBites = cmd.split("=", 2);

        if (cmdBites.length < 2 || cmdBites[1].isEmpty()) {
            System.out.println("id를 입력해주세요.");
            return;
        }

        int id = Integer.parseInt(cmdBites[1]);

        // 존재 여부 확인
        WiseSaying findWiseSaying = findById(id);

        // 없을 경우
        if(findWiseSaying == null) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }

        // 삭제 로직 수행
        delete(id);
        System.out.printf("%d번 명언이 삭제되었습니다.\n".formatted(id));

        return;
    }

    // id 찾기
    private WiseSaying findById(int id) {
        int index = findIndexById(id);

        return wiseSayings.get(index);
    }

    private int findIndexById(int id) {
        for(int i = 0; i<= wiseSayings.size(); i++) {
            if(wiseSayings.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    private void delete(int id) {
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
        }
    }
}
