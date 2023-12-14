## ⚾ 패키지 설명

~~~
 └─src
   ├─main
     └─java
         └─christmas
             ├─config       /** IoC Container */
             ├─constants    /** 상수들을 관리 */
             ├─controller   /** MVC의 Controller. 게임 흐름을 관리 */
             ├─domain       /** MVC의 Model */
             ├─mapper       /** layer간 데이터 전송 시 변환, 검증을 맡음 */
             ├─messages     /** 예외, 입출력 메세지들을 관리 */
             ├─repository   /** Model을 저장 */
             ├─service      /** 여러 객체가 묶인 비지니스 로직을 처리 */
             ├─util         /** 재사용 유틸리티 클래스를 모아둠 */
             ├─validation   /** 자주 사용될 수 있는 검증 기능을 모아둠 */
             └─view         /** MVC의 View. 사용자에게 보여지는 UI를 담당 */
~~~
　   
　   
## 🏀 클래스 설명

### 🚣 AppConfig Class

`AppConfig` 클래스는 어플리케이션의 다양한 객체들을 생성하고 관리합니다.　   
이들은 IoC 컨테이너와 의존성 주입 패턴을 활용하여 어플리케이션의 유연성과 확장성을 높이고, 코드 관리 및 테스트를 용이하게 합니다.　   
이와 관련하여 프리코스 기간에 블로그에 작성한 글을 첨부하겠습니다.　   
[객체에서 의존하는 객체가 너무 많을 때는 DIP(의존관계 역전 원칙), DI(의존성 주입), IoC Container을 적용하자](https://blog.naver.com/inpink_/223254913501)　   
[싱글톤 패턴에 IoC 컨테이너와 LazyHolder를 적용하여 싱글톤의 단점을 최소화](https://blog.naver.com/inpink_/223260522877)　   
　   
- **싱글톤 패턴**:
    - `AppConfig`는 싱글톤 패턴을 사용하여 구현됩니다.
    - `getInstance()` 메소드를 통해 전역적으로 하나의 인스턴스만을 유지합니다.
    - `LazyHolder` 내부 클래스를 사용하여 thread-safe한 인스턴스 생성을 보장합니다.

- **의존성 생성 및 제공**:
    - 여러 컴포넌트(예: `Controller`, `InputView`, `OutputView`..)의 인스턴스를 생성합니다.
    - 필요한 의존성을 각 컴포넌트의 생성자에 주입합니다.

- **IoC 컨테이너 역할**:
    - 애플리케이션의 흐름 제어를 개발자가 아닌 `AppConfig`가 담당합니다.
    - 코드의 결합도를 낮추고 유연성 및 확장성을 높입니다.
    - LazyHolder 방식으로 IoC 컨테이너를 구현하기 때문에, 싱글톤의 독립적인 테스트가 어렵다는 단점을 해결합니다.

  

### 🏔️ IntegerConstants Enum

어플리케이션에서 사용되는 다양한 정수형 상수들을 enum 클래스에 모아 관리합니다.　   
enum을 이용하면 관련있는 상수들을 같은 타입으로 열거할 수 있으며, 자동으로 싱글톤으로 관리됩니다. 　   
또, enum은 리플렉션 공격에 안전하며, 기본적으로 serializable Interface를 구현하고 있어 안전하게 직렬화/역직렬화가 가능합니다.　   

### 🏕️ ErrorMessages Enum

예외 처리 시 사용되는 출력 메세지들을 enum 클래스에 모아 관리합니다.　   
prefix를 통해 모든 에러 메세지의 시작 부분에 출력할 "[Error] "을 편리하게 관리합니다. 　    

### 🏖️ IOMessages Enum

입력(InputView)과 출력(OutputView)에서 사용할 메세지들을 enum 클래스에 모아 관리합니다.  　

### 🏝️ RandomUtil Class

프로젝트 전역에서 사용될 수 있는 내용을 Util class로 분리합니다.　   
이 클래스는 상태를 가지고 있지 않으며, static method로만 구성되므로 private 생성자를 통해 객체가 생성되지 않도록 막아두었습니다.　   
또, Random library의 기능은 주로 Domain에서 사용됩니다.
랜덤을 위해 사용하는 외부 library가 변하더라도 Domain 내부에는 변화를 주지 않기 위해 Random library의 사용을 분리하였습니다.
이를 통해 Domain 내부에서 직접 Random library을 사용하지 않기 때문에 의존성 문제가 줄어듭니다. 


