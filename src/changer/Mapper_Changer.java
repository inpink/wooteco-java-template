package changer;

import java.util.Scanner;

public class Mapper_Changer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Mapper 변환기. ★,end 포함 전체 템플릿을 입력하세요 : ");
        StringBuilder templateBuilder = new StringBuilder();
        String line;
        while (!(line = scanner.nextLine()).equals("end")) {
            templateBuilder.append(line).append("\n");
        }
        String template = templateBuilder.toString();


        System.out.print("변환할 대표 이름을 첫글자 대문자로 입력(Game, Order 등) : ");
        String starWord = scanner.nextLine();
        String diamondWord = starWord.toLowerCase();
        String result = template.replace("★", starWord).replace("☆", diamondWord);

        System.out.println("변환 결과:\n\n" + result);
    }
}
