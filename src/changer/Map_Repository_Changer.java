package changer;

import java.util.Scanner;

public class Map_Repository_Changer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String template = "public class ♥Repository {\n" // Value가 저장되니 repository 이름은 Value
                + "\n"
                + "    protected final Map<■, ♥> store = new LinkedHashMap<>();\n"
                + "\n"
                + "    public void save(■ □, ♥ ♡) {\n"
                + "        store.put(□, ♡);\n"
                + "    }\n"
                + "\n"
                + "    public Optional<♥> findBySameObject(■ □) {\n"
                + "        return Optional.ofNullable(store.get(□));\n"
                + "    }\n"
                + "\n"
                + "    public Optional<♥> findByMission(Mission mission) { // ■ 내부에 Mission이 연관필드임\n"
                + "        return store.keySet()\n"
                + "                .stream()\n"
                + "                .filter(□ -> □.getMission().equals(mission))\n"
                + "                .map(□ -> store.get(□))\n"
                + "                .findAny();\n"
                + "    }\n"
                + "\n"
                + "    public void deleteAll() {\n"
                + "        store.clear();\n"
                + "    }\n"
                + "\n"
                + "}";

        System.out.print("변환할 Key 이름(■)을 첫글자 대문자로 입력(CourseMission 등) : ");
        String result = transform(scanner, template, "■", "□");

        System.out.print("변환할 Value 이름(♥)을 첫글자 대문자로 입력(MatchingResult 등) : ");
        result = transform(scanner, result, "♥", "♡");


        System.out.println("변환 결과:\n\n" + result);
    }

    private static String transform(Scanner scanner, String template, String Higher, String lower) {
        String starWord = scanner.nextLine();
        String diamondWord = starWord.substring(0, 1).toLowerCase() + starWord.substring(1);
        String result = template.replace(Higher, starWord).replace(lower, diamondWord);
        return result;
    }

}
