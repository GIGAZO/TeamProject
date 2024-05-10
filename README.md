# 수강생 관리 프로그램

이 프로그램은 수강생들의 정보를 효율적으로 관리하는 프로그램입니다.

Member : [김예찬](https://github.com/wondo8449), [노상윤](https://github.com/rohtable/), [이지우](https://github.com/20210805jiwoo), [정효진](https://github.com/hyojjin-jeong), [한승훈](https://github.com/hsh1105)

## 🛠️ Tech Stack

- 언어: Java
- 버전: JDK 17
- 개발 환경: IntelliJ

## 📕 기획 명세서

### 요구사항 정의서
![image](https://github.com/GIGAZO/TeamProject/assets/102974424/b2e06662-30a6-47ef-8e9c-b015c8fefcba)


### ERD Diagram
![image](https://github.com/GIGAZO/TeamProject/assets/102974424/07ab1265-a1de-4bde-9e39-22599a7731cf)


## 🔖 Features

- 수강생 정보 등록:
    - 수강생의 고유번호, 이름, 선택한 과목, 기분 상태를 등록할 수 있습니다.
- 수강생 정보 업데이트:
    - 수강생의 이름 혹은 상태를 수정할 수 있습니다.
    - 수강생의 정보를 삭제할 수 있습니다.
- 수강생 목록 조회:
    - 현재 등록된 모든 수강생의 정보를 조회할 수 있습니다.
    - 특정 상태를 가진 수강생의 정보를 조회할 수 있습니다.
- 시험 점수 등록:
    - 수강생의 과목별 시험 회차 및 점수를 등록할 수 있습니다.
    - 등록된 점수에 따라 자동으로 등급이 부여됩니다.
- 시험 점수 업데이트:
    - 수강생의 과목별 회차 점수를 수정할 수 있습니다.
    - 수정된 점수에 따라 자동으로 등급이 부여됩니다.
- 시험 등급 조회:
    - 수강생의 특정 과목의 회차별 등급을 조회할 수 있습니다.
    - 수강생의 과목별 평균 등급을 조회할 수 있습니다. 
    - 특정 상태를 가진 수강생들의 필수 과목 평균 등급을 조회할 수 있습니다.
      
## 🤙 Commit convention
- **파일 이름:** JAVA 파일 이름은 포함된 소스의 최상위 클래스의 이름과 `.java` 확장자로 구성했습니다.
- **import:** 와일드 카드 (ex. java.util.* 처럼 아스테리스크로 하위 클래스를 다 적용하는 방식)으로는 가져오지 않았습니다.
- **클래스 이름**: 클래스 이름은 카멜 케이스를 따라 첫 글자를 대문자로 작성했습니다.
- **메소드 이름**: 메소드 이름도 카멜 케이스를 따라 작성되었고, 의미 있는 이름을 사용하고 있습니다.
- **변수 이름**: 변수 이름은 카멜 케이스를 따르고, 명확한 의미를 전달하기 위해 적절한 이름을 사용하였습니다.
- **이름 재사용**: 변수 이름과 메서드 이름이 중복되지 않도록 적절히 선택되었습니다.
- **들여쓰기**: 코드 블록은 4개의 공백으로 들여쓰기되어 있습니다.
- **빈 줄 넣기:** if, for와 여는 괄호 “(” 사이에 공백을 넣었고 else 앞에 오는 닫는 중괄호 “}” 앞에 공백을 넣었습니다.
- **중괄호 사용**: 모든 메서드와 클래스에 중괄호가 사용되었습니다.
- **주석**: 주석은 적절하게 사용되어 메서드의 용도를 설명하고 있습니다.
- **상수**: 상수는 대문자로 작성되어 있습니다.

## Github Rules
- 자신의 브랜치에서 코드 구현
- 하나의 기능을 구현 할 때마다 commit
- 큰 함수 혹은 기능을 구현하면 develop 브랜치로 pull request
- 충돌 해결 후 merge
- develop에 올라가면 slack에 공지 - ex) develop pull 한 번 해주세요~ <br><br>
- git commit -m “message”
- message : [update] 수강생 등록 기능 구현
- 작업한 내용과 기능이 무엇인지 정확히 파악 가능하게 message 적기 <br>

<img src="https://github.com/GIGAZO/TeamProject/assets/102974424/5195ca23-e684-43b8-a14f-8b79cd94302f" width="200">
