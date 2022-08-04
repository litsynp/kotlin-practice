package com.litsynp.kotlinpractice.javatokotlin

/** 12강. 코틀린에서 object 키워드를 다루는 방법 */
fun main() {
    // Kotlin에는 Java와 달리 object라는 별도의 지시어가 추가됨

    // 1. static 함수와 변수
    // [코틀린에는 static이 없다 => companion object을 쓴다!]
    // static: 클래스가 인스턴스화 될 때 새로운 값이 복제되는 것이 아니라 정적으로 인스턴스끼리의 값을 공유
    // companion object: 클래스와 동행하는 유일한 오브젝트

    // -
    // 자바와 다르게, companion object, 즉 동반 객체도 하나의 객체로 취급된다.
    // 때문에 이름을 붙일 수도 있고, interface를 구현할 수도 있다.

    // -
    // companion object에 유틸성 함수를 넣어도 되지만,
    // 최상단 파일을 활용하는 것을 추천!

    // -
    // Java에서 Kotlin에 있는 static field, static 함수를 사용하고 싶을 수 있다
    // a. 이름이 없는 경우: Person.Companion.newBaby(...);
    //    또는 코틀린 함수 위에 @JvmStatic을 붙여주면 Person.newBaby("ABC"); 라고 할 수 있다
    // b. 이름이 있는 경우: Person.Factory.newBaby(...);

    // 2. 싱글톤
    // 싱글톤: 단 하나의 인스턴스만을 갖는 클래스

    // -
    // 자바에서의 싱글톤
    // 자바에는 static 영역에 INSTANCE를 만들어 getInstance()를 구현했다 (private constructor)
    // 동시성 처리를 더 하거나, enum class를 활용하는 방법도 있었다

    // -
    // 코틀린에서의 싱글톤
    // 단순히 object를 붙여주면 된다... That simple!
    println(Singleton.a)
    Singleton.a += 10
    println(Singleton.a)
    // 물론 서버 개발에 직접 싱글톤 객체를 만들 일은 거의 없었다

    // 3. 익명 클래스
    // 익명 클래스: 특정 인터페이스 / 클래스를 상속 받은 구현체를 일회성으로 사용할 때 쓰는 클래스

    // -
    // Java에서의 익명 클래스
    // new Movable() { @Override ... } 와 같은 식으로 사용했다

    // -
    // Kotlin에서의 익명 클래스
    // object 키워드를 사용한다
    // Movable을 구현한 object를 구현
    moveSomething(object : JavaMovable {
        override fun move() {
            println("무브 무브")
        }

        override fun fly() {
            println("날다 날다")
        }
    })
}

private interface Lec12Log {

    fun log()
}

private class Lec12Person(
    var name: String,
    var age: Int,
) {
    companion object Factory : Lec12Log {
        // val만 쓰면 런타임 시에 변수가 할당됨
        // const를 붙이면 컴파일 시에 변수가 할당됨 (진짜 상수)
        // const는 기본 타입과 String에만 붙일 수 있다
        private const val MIN_AGE = 1
        fun newBaby(name: String): Lec12Person {
            return Lec12Person(name, MIN_AGE)
        }

        override fun log() {
            println("나는 Person 클래스의 동행 객체 Factory에요")
        }
    }
}

private object Singleton {
    var a: Int = 0
}

fun moveSomething(movable: JavaMovable) {
    movable.move()
    movable.fly()
}
