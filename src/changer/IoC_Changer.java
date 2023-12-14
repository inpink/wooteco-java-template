package changer;

import java.util.Scanner;

public class IoC_Changer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String template = "public class AppConfig {\n"
                + "\n"
                + "    public static AppConfig getInstance() {\n"
                + "        return LazyHolder.INSTANCE;\n"
                + "    }\n"
                + "\n"
                + "    public ★Controller ☆Controller() {\n"
                + "        return LazyHolder.☆Controller;\n"
                + "    }\n"
                + "\n"
                + "     public ★Service ☆Service() {\n"
                + "        return LazyHolder.☆Service;\n"
                + "    }\n"
                + "\n"
                + "    public InputView inputView() {\n"
                + "        return LazyHolder.inputView;\n"
                + "    }\n"
                + "\n"
                + "    public OutputView outputView() {\n"
                + "        return LazyHolder.outputView;\n"
                + "    }\n"
                + "\n"
                + "    private static class LazyHolder {\n"
                + "        private static final AppConfig INSTANCE = new AppConfig();\n"
                + "        private static final InputView inputView = createInputView();\n"
                + "        private static final OutputView outputView = createOutputView();\n"
                + "        private static final ★Service ☆Service = create★Service();\n"
                + "        private static final ★Controller ☆Controller = create★Controller();\n"
                + "\n"
                + "        private static ★Controller create★Controller() {\n"
                + "            return new ★Controller(\n"
                + "                    inputView,\n"
                + "                    outputView,\n"
                + "                    ☆Service);\n"
                + "        }\n"
                + "\n"
                + "        private static ★Service create★Service() {\n"
                + "            return new ★Service();\n"
                + "        }\n"
                + "\n"
                + "        private static InputView createInputView() {\n"
                + "            return new InputView();\n"
                + "        }\n"
                + "\n"
                + "        private static OutputView createOutputView() {\n"
                + "            return new OutputView();\n"
                + "        }\n"
                + "    }\n"
                + "}\n";

        System.out.print("변환할 대표 이름을 첫글자 대문자로 입력(Game, Order 등) : ");
        String starWord = scanner.nextLine();
        String diamondWord = starWord.substring(0, 1).toLowerCase() + starWord.substring(1);
        printResult(template, starWord, diamondWord);

        String testTemplate = "    private static AppConfig config;\n"
                + "\n"
                + "@BeforeEach\n"
                + "    void setUp() {\n"
                + "        config = AppConfig.getInstance();\n"
                + "    }\n"
                + "\n"
                + "    static class ObjectSupplierTuple {\n"
                + "        final Supplier<Object> supplier;\n"
                + "        final Class<?> expectedType;\n"
                + "\n"
                + "        ObjectSupplierTuple(final Supplier<Object> supplier, final Class<?> expectedType) {\n"
                + "            this.supplier = supplier;\n"
                + "            this.expectedType = expectedType;\n"
                + "        }\n"
                + "    }\n"
                + "\n"
                + "    static Stream<ObjectSupplierTuple> singletonSuppliers() {\n"
                + "        return Stream.of(\n"
                + "                new ObjectSupplierTuple(() -> config.outputView(), OutputView.class),\n"
                + "                new ObjectSupplierTuple(() -> config.inputView(), InputView.class),\n"
                + "                new ObjectSupplierTuple(() -> config.☆Controller(), ★Controller.class),\n"
                + "                new ObjectSupplierTuple(() -> config.☆Service(), ★Service.class)\n"
                + "        );\n"
                + "    }\n"
                + "\n"
                + "    @ParameterizedTest(name = \"{0}은 싱글톤이며, {1}의 객체이다.\")\n"
                + "    @MethodSource(\"singletonSuppliers\")\n"
                + "    void assertSingletonAndType(final ObjectSupplierTuple tuple) {\n"
                + "        // Given\n"
                + "        final Supplier<Object> supplier = tuple.supplier;\n"
                + "        final Class<?> expectedType = tuple.expectedType;\n"
                + "\n"
                + "        // When\n"
                + "        Object firstInstance = supplier.get();\n"
                + "        Object secondInstance = supplier.get();\n"
                + "\n"
                + "        // Then\n"
                + "        assertThat(firstInstance).isNotNull();\n"
                + "        assertThat(firstInstance).isInstanceOf(expectedType);\n"
                + "        assertThat(firstInstance).isSameAs(secondInstance);\n"
                + "    }";

        printResult(testTemplate, starWord, diamondWord);

        String mainTemplate = "    public static void main(String[] args) {\n"
                + "        AppConfig config = generateConfig();\n"
                + "        ★Controller ☆Controller = generate★Controller(config);\n"
                + "        ☆Controller.play();\n"
                + "    }\n"
                + "\n"
                + "    private static AppConfig generateConfig() {\n"
                + "        return AppConfig.getInstance();\n"
                + "    }\n"
                + "\n"
                + "    private static ★Controller generate★Controller(AppConfig config) {\n"
                + "        return config.☆Controller();\n"
                + "    }";

        printResult(mainTemplate, starWord, diamondWord);

    }

    private static void printResult(String template, String starWord, String diamondWord) {
        String result = template.replace("★", starWord).replace("☆", diamondWord);

        System.out.println("변환 결과:\n\n" + result);
    }

}
