package com.back;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("== 명언 앱 ==");
        Scanner scanner = new Scanner(System.in);

        List<WiseSaying> wiseSayings = new ArrayList<>();

        int lastId = 0;
        int wiseSayingLastIndex = -1;

        while(true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();

            if(cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                System.out.print("명언 : ");
                String wiseSayingContent = scanner.nextLine();
                System.out.print("작가 : ");
                String wiseSayingAuthor = scanner.nextLine();

                int id = ++lastId;

                WiseSaying wiseSaying = new WiseSaying();
                wiseSaying.id = id;
                wiseSaying.content = wiseSayingContent;
                wiseSaying.author = wiseSayingAuthor;

                wiseSayings.add(wiseSaying);

                System.out.println("%d번 명언이 등록되었습니다.".formatted(id));
            } else if (cmd.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("-----------------");

                for(int i = wiseSayings.size() - 1; i >= 0; i--) {
                    WiseSaying wiseSaying = wiseSayings.get(i);

                    if(wiseSaying == null) continue;

                    System.out.printf("%d / %s / %s\n", wiseSaying.id, wiseSaying.author, wiseSaying.content);
                }
            } else if (cmd.startsWith("삭제")) {
                String[] cmdBites = cmd.split("=", 2);
                int deleteIndex = -1;

                if(cmdBites.length < 2 || cmdBites[1].isEmpty()) {
                    System.out.println("id를 입력해주세요.");
                    continue;
                }

                // 정수값으로 뽑기
                int index = Integer.parseInt(cmdBites[1]);

                // id가 같은 명언을 찾기
                for(int i = 0; i <=wiseSayings.size() - 1; i++) {
                    if(wiseSayings.get(i).id == index) {
                        deleteIndex = i;
                        break;
                    }
                }

                // 없을 경우
                if(deleteIndex == -1) {
                    System.out.println("%d번 명언은 존재하지 않습니다.".formatted(index));
                    continue;
                }

                // 삭제
                wiseSayings.remove(deleteIndex);

                System.out.printf("%d번 명언이 삭제되었습니다.\n".formatted(index));
            } else if (cmd.startsWith("수정")) {
                String[] cmdBites = cmd.split("=", 2);
                int updateIndex = -1;

                // 예외처리
                if(cmdBites.length < 2 || cmdBites[1].isEmpty()) {
                    System.out.println("id를 입력해주세요.");
                    continue;
                }

                int id = Integer.parseInt(cmdBites[1]);

                // id 찾기
                for(int i = 0; i <= wiseSayings.size() - 1; i++) {
                    if(wiseSayings.get(i).id == id) {
                        updateIndex = i;
                        break;
                    }
                }

                // 없을 경우
                if(updateIndex == -1) {
                    System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
                    continue;
                }

                // 기존 데이터 출력 및 새 데이터 입력
                WiseSaying wiseSaying = wiseSayings.get(updateIndex);
                System.out.println("기존 명언 : " + wiseSaying.content);
                System.out.print("명언 : ");
                String newContent = scanner.nextLine();

                System.out.println("기존 작가 : " + wiseSaying.author);
                System.out.print("작가 : ");
                String newAuthor = scanner.nextLine();

                // 데이터 변경
                wiseSaying.content = newContent;
                wiseSaying.author = newAuthor;


                System.out.printf("%d번 명언이 수정되었습니다.\n", id);
            }
        }
    }
}