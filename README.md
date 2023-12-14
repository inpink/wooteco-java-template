# 목차
- [기능 목록](#기능-목록)
- DTO, Mapper (Changer에 있음)
    - [하나의 문자 입력 (게임 재시작 등 동작 메뉴 중 선택. 동작 메뉴는 enum으로 관리.)](#하나의-문자-입력-게임-재시작-등-동작-메뉴-중-선택-동작-메뉴는-enum으로-관리)
    - [한 줄에 여러 개의 문자 ,로 입력 (분리해서 일반 객체에 담기)](#-한-줄에-여러-개의-문자-로-입력-분리해서-일반-객체에-담기)
    - [하나의 정수 입력 (여기서는 Enum찾아줌. 객체로 반환해도 됨.)](#-하나의-정수-입력-여기서는-enum찾아줌-객체로-반환해도-됨)
    - [한 줄에 여러 개의 정수 ,로 입력 (분리해서 일반 객체에 담기) (,가 아니라면 ""사용)](#-한-줄에-여러-개의-정수-로-입력-분리해서-일반-객체에-담기-가-아니라면-사용)
- [출력 예시](#출력-예시)
- [에러 안날때까지 계속 입력받기(사용법)](#에러-안날때까지-계속-입력받기사용법)
- [날짜 관련LocalDate](#날짜-관련localdate)
- Changer
    - [AppConfig IoC 컨테이너(Container)](#appconfig-ioc-컨테이너container)
    - [repository](#repository)
- [돈](돈)
- Util
    - [Random 제공되는 라이브러리 (RandomUtil)](#random-제공되는-라이브러리-randomutil)
- 검증
    - [정수 검증(IntegerValidator)](#정수-검증integervalidator)
- [전략, 제출 전 체크할 것, 시간이 남으면 할 것](#tip)

　   
***
### 기능 목록
　   
> 입력 화면

[공통]
- 사용자가 잘못된 값을 입력하면 IllegalArgumentException 발생
- [ERROR]로 시작하는 에러 메시지를 출력 후 해당 부분부터 다시 입력을 받음


[정수 1개 입력]
- 사용자는 "☆" 정수 1개를 입력
- 검증: 만약 정수가 아닌 것을 입력하면 잘못 입력한 것
- 검증: 만약 1 이상 31 이하의 숫자가 아니라면 잘못 입력한 것
- 검증: 1개 이상 입력하지 않으면 잘못된 입력
- 오타 교정: 모든 공백을 제거

[정수 여러개 입력 (,로 구분)]
- 사용자는 "☆"을 ,로 구분하여 한 줄에 입력
- 검증: 만약 정수가 아닌 것을 입력하면 잘못 입력한 것
- 검증: 같은 것을 2개 이상 입력할 시 잘못된 입력
- 검증: 1개 이상 입력하지 않으면 잘못된 입력 예) 아무것도 입력하지 않거나, 콤마만 입력
- 오타 교정: 모든 공백을 제거

[정수 여러개 입력 (붙여서 입력 ex-123)]
- 사용자는 "☆"을 한 줄에 입력
- 검증: 만약 정수가 아닌 것을 입력하면 잘못 입력한 것
- 검증: 만약 숫자의 개수가 3개가 아니라면 잘못 입력한 것
- 검증: 같은 것을 2개 이상 입력할 시 잘못된 입력
- 검증: 1개 이상 입력하지 않으면 잘못된 입력
- 오타 교정: 모든 공백을 제거

[문자 1개 입력]
- 사용자는 "☆" 문자 1개를 입력
- 검증: 만약 ★가 아닌 것을 입력하면 잘못 입력한 것
- 검증: 1개 이상 입력하지 않으면 잘못된 입력
- 오타 교정: 모든 공백을 제거

[문자 여러개 입력 (,로 구분)]
- 사용자는 "☆"을 ,로 구분하여 한 줄에 입력
- 검증: 만약 ★가 아닌 것을 입력하면 잘못 입력한 것
- 검증: 같은 것을 2개 이상 입력할 시 잘못된 입력
- 검증: 1개 이상 입력하지 않으면 잘못된 입력 예) 아무것도 입력하지 않거나, 콤마만 입력
- 오타 교정: 모든 공백을 제거


***
> DTO

```record``` 이용
```java
public record HintsDto(int ballCount, int strikeCount, boolean isNothing) {

}
```
Controller->View에서 사용 
```outputView.output★(★Mapper.from(☆.get()))``` 

　   
> 각 Mapper

### 하나의 문자 입력 (게임 재시작 등 동작 메뉴 중 선택. 동작 메뉴는 enum으로 관리.)

```java
public class ★Mapper {
    private ★Mapper() {

    }

    public static ★ to★(String input) {
        final String deletedSpaces = StringUtil.removeAllSpaces(input);

        return ★.find★(deletedSpaces);
    }
}
end
```
▼ 게임 재시작 등 동작 메뉴 중 선택. 동작 메뉴는 enum으로 관리
```java
public enum Function {
    PAIR_MATCHING("1", "페어 매칭"),
    PAIR_CHECK("2", "페어 조회"),
    PAIR_RESET("3", "페어 초기화"),
    QUIT("Q", "종료");

    private final String option;
    private final String description;

    Function(String option, String description) {
        this.option = option;
        this.description = description;
    }

    public static Function findFunction(String otherOption) {
        return Arrays.stream(Function.values())
                .filter(function -> function.option.equals(otherOption))
                .findAny()
                .orElseThrow(() -> ExceptionUtil.returnInvalidValueException());
    }

    public String getOption() {
        return option;
    }

    public String getDescription() {
        return description;
    }
}
```

### ★ 한 줄에 여러 개의 문자 ,로 입력 (분리해서 일반 객체에 담기)
```java
public class ★Mapper {

    private ★Mapper() {

    }

    public static ★ to★(String input) {
        List<String> separated = seperate(input);

        final String courseName = separated.get(0);
        final String levelName = separated.get(1);
        final String missionName = separated.get(2);

        Course course = toCourse(courseName);
        Level level = toLevel(levelName);
        Mission mission = toMission(level, missionName);

        return ★.create(course, level, mission);
    }

    private static List<String> seperate(String input) {
        String deleteSpaces = StringUtil.removeAllSpaces(input);
        List<String> separated = Arrays.stream(deleteSpaces.split(","))
                .toList();
        return separated;
    }

    private static Level toLevel(String levelName) {
        return Level.findLevel(levelName);
    }

    private static Course toCourse(String courseName) {
        return Course.findCourse(courseName);
    }

    private static Mission toMission(Level level, String missionName) {
        return level.findMission(missionName);
    }
}
end
```

### ★ 하나의 정수 입력 (여기서는 Enum찾아줌. 객체로 반환해도 됨.)
```java
public class ★Mapper {
    public static ★ to★(String input) {
        String deleteSpaces = StringUtil.removeAllSpaces(input);
        IntegerValidator.validateInteger(input);
        int converted = Integer.parseInt(deleteSpaces);

        return ★.find★(converted);
    }
}
end
```


### ★ 한 줄에 여러 개의 정수 ,로 입력 (분리해서 일반 객체에 담기) (,가 아니라면 ""사용)
```java
public class ★Mapper {
    public static ★ to★(String input) {
        String deleteSpaces = StringUtil.removeAllSpaces(input);
        List<String> separated = Arrays.stream(deleteSpaces.split(","))
                .toList();

        IntegerValidator.validateInteger(separated);

        List<Integer> converted = separated
                .stream()
                .map(Integer::parseInt)
                .toList();

        return ★.create(converted);
    }
}
end
```


### 에러 안날때까지 계속 입력받기(사용법)
```java
    private 생성할객체명 inputValid생성할객체명() {
        return InputUtil.retryOnInvalidInput(inputView::input생성할객체명,
                errorMessage -> outputView.outputErrorMessage(INVALID_REMATCHING_SELECT.getMessage()));
    }
```
 
### 입력받는 예시
```java
    public CourseMission inputCourseMission() {
        System.out.println(INPUT_SELECT_OPTIONS.getMessage());
        System.out.println(SELECT_EXAMPLE.getMessage());
        String input = InputUtil.input();

        return CourseMissionMapper.toCourseMission(input);
    }
```

### 출력 예시
```java
    private void outputCourses() { //백엔드 | 프론트엔드
        String courses = Arrays.stream(Course.values())
                .map(Course::getDescription)
                .collect(Collectors.joining(" | "));
        System.out.println(COURSE.getMessage() + ": " + courses);
    }

    private void outputMissions() { // 레벨1: 자동차경주 | 로또 | 숫자야구게임      레벨2: 장바구니 | 결제 | 지하철노선도....
        System.out.println(MISSION.getMessage() + ": ");
        Arrays.stream(Level.values())
                .map(this::formatMissionsForLevel)
                .forEach(System.out::println);
    }
```

### ```AppConfig``` IoC 컨테이너(Container)  
- ```IoC_Changer``` class로 변환하기 
- 테스트코드 O


### 날짜 관련(LocalDate)
- 달력에 존재하는 날짜가 아니면 에러 발생 (DateValidator, Test)
- 시작~끝 기간 내에 존재하는 날짜인지 (LocalDateUtil, Test)

### repository
> index repository가 필요하다면 ```크리스마스``` 참고하기.

- IndexModel
- Repository package
- Repository test package

> Map 형태의 repository (key가 객체, value도 객체)


***
### 돈
- ```Money``` 구현 파일이 포함되어있음
- ```MoneyTest```도 구현되어있음

  
👉 천단위 구분자 관련
- money 내부에 있음
 　    
## 유틸
### Random 제공되는 라이브러리 (RandomUtil)

1) ```pickNumberInList``` : List<Integer>받아서 랜덤으로 int 하나 뽑아 반환 => RandomUtil.pickNumberInList()
2) ```pickNumberInRange``` : startInclusive int, endInclusive int 2개 받아서 이 사이의 int 하나 뽑아 반환 => RandomUtil.pickNumberInRange()
3) ```pickUniqueNumbersInRange``` : startInclusive int, endInclusive int, count int 3개 받아서 이 사이의 int를 count개만큼 뽑아 반환 => RandomUtil.pickUniqueNumbersInRange()
4) ```pickUniqueNumbersInRange``` : startInclusive int, endInclusive int, count int 3개 받아서 이 사이의 int를 count개만큼 뽑아, 정렬하여 반환 => RandomUtil.pickUniqueSortedNumbersInRange()
5) ```shuffle``` : List<String> 받아서 순서 섞어 List<String>으로 반환. ☆String아니고 다른거로 쓰려면 인자 바꿔주면 됨 => shuffle

　   
## 검증
(Validation이 Util을 이용. Util은 Validation 몰라야 함)
### 정수 검증(IntegerValidator) 
- 문자 1개가 정수인지 ▼
~~~
    public static void validateInteger(final String string) {
        if (!IntegerUtil.isInteger(string)) {
            ExceptionUtil.throwInvalidValueException();
        }
    }
~~~

- 문자 여러개가 정수인지(가변인자 이용) ▼
~~~
    public static void validateInteger(final String... strings) {
        Arrays.stream(strings)
                .forEach(string -> validateInteger(string));
    }
~~~

- 문자 List가 정수인지 ▼
~~~
    public static void validateInteger(List<String> separated) {
        boolean result = separated
                .stream()
                .allMatch( value -> IntegerUtil.isInteger(value));

        if (!result) {
            ExceptionUtil.throwInvalidValueException();
        }
    }
~~~

- 음수값이 아닌지 ▼
~~~
    public static void validateNotNegative(final int value) {
        if (value < 0) {
            ExceptionUtil.throwInvalidValueException();
        }
    }
~~~

- start~end 범위 내에 있는지 ▼
~~~
    public static void validateInRange(final int value, final int min, final int max) {
        validateNotSmaller(value, min);
        validateNotBigger(value, max);
    }

    public static void validateNotSmaller(final int value, final int min) {
        if (value < min) {
            ExceptionUtil.throwInvalidValueException();
        }
    }

    public static void validateNotBigger(final int value, final int max) {
        if (value > max) {
            ExceptionUtil.throwInvalidValueException();
        }
    }
~~~

- 합이 정수 범위를 넘지 않는지 ▼
~~~
    public static void validatePlusRange(final int value1, final int value2) {
        final BigInteger a = BigInteger.valueOf(value1);
        final BigInteger b = BigInteger.valueOf(value2);
        final BigInteger result = a.add(b);

        if (result.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0 ||
                result.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) < 0) {
            ExceptionUtil.throwInvalidValueException();
        }
    }
~~~

- 같은 정수인지 ▼
~~~
    public static void validateSame(final int value1, final int value2) {
        if (value1 != value2) {
            ExceptionUtil.throwInvalidValueException();
        }
    }
~~~

***
### tip
> 전략

- 메서드명 바꿀 때 Shift+F6
- 객체들을 연관관계없이 따로 관리하더라도, setter나 add같은건 지양하자. 객체지향이 깨져서 나중에 더 복잡해진다.
- 테스트코드에 주어진 것을 우선적으로 구현하자. 이를 기능목록에 반드시 체크하자

- 시간 관리

~30분(1시 30분) : 문제 이해, 테스트 코드 분석, 템플릿 셋팅, 기능 목록 작성  
~40분(2시 10분) : 입력 완성, 출력 틀  
~130분(4시 20분) : 5분 쉬는시간 포함, 비지니스 로직 모두 구현  
~70분(5시 30분) : 컨트롤러, 서비스, 출력 뷰 완성  
~30분(6시) : 소감문 작성, 최종 점검, 제출, 제출 재확인  


***
> 체크할 것

- Changer class 제거
- 모든 파일에 대해 format 적용 (Ctrl+Alt+L)
- 쓸데없는 주석 제거
- 


***

> 시간이 남으면 할 것

- VO화할 수 있는 것 분리하기
- 사용되지 않는 메서드와 필드 체크(안쓰는거면 지우고 실수로 안쓴거면 고치기) (☆ 테스트 코드에만 사용되는 메서드 체크)
- 비지니스 로직에 대한 테스트 코드 작성
- final 달기
- static만 있는 util성 클래스에 private 생성자 달기
- 도메인 설계도 작성(whimsical https://whimsical.com/FxhBSA3UYj7TBhfNCCCPQK)
- ParameterizedTest에 display 달기 예)  @ParameterizedTest(name = "값 {0}을 담는 ItemCount 객체가 생성된다.")
- 각 상황에 대해 에러 메세지 추가
- project description 추가 🏛️🏨🗼🌃🚉
