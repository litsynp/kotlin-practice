package com.litsynp.kotlinpractice.javatokotlin

/** 9강. 코틀린에서 클래스를 다루는 방법 */
fun main() {
    // 1. 클래스와 프로퍼티
    val person = Person("litsynp", 100)
    println(person.name) // .name으로 getter
    person.age = 10      // .age로 setter 호출
    println(person.age)

    // 2. 생성자와 init
    println("====")
    val person1 = Person("litsynp")
    println("====")
    val person2 = Person()
    println("====")
    // 역순으로 실행된다.
    // 1. 초기화 블록의 body 먼저 실행
    // 2. 첫 번째 생성자의 body 실행
    // 3. 두 번째 생성자의 body 실행

    // 3. 커스텀 getter, setter

    // 4. backing field

}

// Property = Field + Getter + Setter
// 코틀린에서는 필드만 만들면 getter, setter 자동 생성

// 생성자는 위에: class Person constructor(...)
// constructor은 생략 가능
class Person(
    // 생성자 만드는 동시에 property 생성 가능
    val name: String, var age: Int
    // 주생성자(primary constructor)는 반드시 존재해야 함
    // 주생성자에 파라미터가 하나도 없다면 (e.g., Student)는 기본적으로 만들어줘서 생략 가능
    // e.g. class Student()
) {
//    var name = name
//        set(value) {
//            field = value.uppercase()
//            // setter 자체를 지양하므로 사실 backing field를 이용하는 custom setter도 잘 안쓴다
//        }

    // [Backing Field]
    // = custom getter / setter을 만들 때 필드 이름을 통한 접근에 따른 무한 루프를 방지하기 위한 키워드
    // custom getter
//    val name = name
//        get() = field.uppercase() // name.uppercase()가 아닌 field.uppercase()
    // 똑같이 name이라고 적으면 name에 대한 getter를 다시 부르는 무한 루프 발생하므로
    // -> "backing field" (자기 자신 가리키는 보이지 않는 필드)
    // BUT, custom getter에서 backing field를 쓰는 경우는 드물다

    // 이런 식으로도 처리 가능

    // 1.
//    fun getUppercaseName(): String {
//        return this.name.uppercase()
//    }

    // 2.
//    val uppercaseName: String
//        get() = this.name.uppercase()

    init {
        // 클래스 초기화되는 시점에 한 번 호출되는 블록
        // 값을 적절히 만들어주거나, 검증 로직 등 추가 가능
        if (age <= 0) {
            throw IllegalArgumentException("나이는 ${age}일 수 없습니다")
        }
        println("초기화 블록")
    }

    // 추가 생성자는 constructor(파라미터)로 생성
    // "부생성자" (secondary constructor)
    // - this는 생성자를 가리키고 있음
    // - **최종적으로는 주생성자를 호출해야한다**
    constructor(name: String) : this(name, 1) {
        // - body를 가질 수 있음
        println("부생성자 1")
    }

    // 부생성자를 호출한다면 그 부생성자가 주생성자를 호출한다면 상관 없음
    constructor() : this("litsynp") {
        println("부생성자 2")
    }

    // 코틀린에서는 기본적으로 부생성자보다는 default parameter을 권장한다.
    // 하지만 부생성자를 사용해야하는 경우가 있다.
    // e.g., Alien이 들어오면 Person으로 변환 -> 주생성자로 불가능
    // -> Converting 경우 부생성자 사용 가능하나 정적 팩토리 메소드 추천

    // fun isAdult(): Boolean {
    //     return this.age >= 20
    // }
    // -> 이렇게 구현하는 방법도 있지만
    // Property처럼 보이게 하는, Custom getter를 만들 수도 있다
    val isAdult: Boolean
        get() = this.age >= 20 // get() = XXX, 또는 get() { return XXX }
    // 구현 기준: 객체의 속성이라면 custom getter, 그렇지 않다면 함수
}
// body에 아무것도 없으면 {} 생략 가능
