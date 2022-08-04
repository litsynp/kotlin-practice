package com.litsynp.kotlinpractice.javatokotlin

/** 10강. 코틀린에서 상속을 다루는 방법 */
fun main() {
    // 1. 추상 클래스 (abstract)
    // Java, Kotlin 모두 추상 클래스는 인스턴스화 할 수 없다!
    val penguin = Penguin("황제펭귄")
//    penguin.legCount // proteced 접근 불가

    // 2. 인터페이스 (interface)
    // Java, Kotlin 모두 인터페이스는 인스턴스화 할 수 없다!
    // Kotlin에서는 backing field가 없는 프로퍼티를 interface에 만들 수 있다

    // 3. 클래스를 상속할 때 주의할 점
    Derived(300)
    // 상위 클래스의 constructor와 init 블록에서는 하위 클래스의 (final이 아닌) field에 접근하면 안된다
    // IDE에서 WARNING 할 것
    // => 상위 클래스를 설계할 때, 생성자 또는 초기화 블록에서 사용되는 프로퍼티에는 open을 꼭 피해야 한다

    // 4. 상속 관련 지시어 정리
    // a. final: override를 할 수 없게 한다. default로 보이지 않게 존재한다.
    // b. open: override를 열어 준다.
    // c. abstract: 반드시 override 해야 한다.
    // d. override: 상위 타입을 오버라이드 하고 있다.


    // [정리]
    // - 상속 또는 구현에는 : 을 사용한다
    // - 상위 클래스 상속을 구현할 때 생성자를 반드시 호출해야 한다
    // - override를 필수로 붙여야 한다
    // - 추상 멤버가 아니면 기본적으로 override가 불가능하다
    //   - open을 사용해줘야 한다
    // - 상위 클래스의 생성자 또는 초기화 블록에서 open 프로퍼티를 사용하면
    //   예기치 못한 버그가 생길 수 있다
}

private abstract class Animal(
    protected val species: String,
    protected open val legCount: Int, // open을 무조건 붙여줘야 getter override 가능
    // 추상 프로퍼티가 아니라면, 상속 받을 때 open을 꼭 붙여야 한다
) {
    abstract fun move()
}

private class Cat(
    species: String
) : Animal(species, 4) { // 상속에 colon 사용, 무조건 생성자 바로 호출
    // override 필수적으로 붙여야 함
    override fun move() {
        println("고양이가 사뿐 사뿐 걸어가~")
    }
}

private class Penguin(
    species: String
) : Animal(species, 2), Swimmable, Flyable { // interface 구현도 : 뒤에 나온다

    private val wingCount: Int = 2

    override fun move() {
        println("펭귄이 움직입니다~ 꿱꿱")
    }

    // override 한 경우 public private 등을 딱히 명시해주지 않으면 '상위 멤버'의 접근 지시자를 따라간다
    // => 이 경우 protected
    override val legCount: Int
        get() = super.legCount + this.wingCount // open 붙여야 함

    override fun act() {
        // Java에서는 타입.super.XXX()였지만
        // Kotlin에서는 super<타입>.XXX()으로 호출 가능
        super<Swimmable>.act()
        super<Flyable>.act()
    }

    override val swimmability: Int
        get() = 3
}

private interface Flyable {
    fun act() { // default 생략 가능
        println("파닥 파닥")
    }

//    fun fly() // 추상 메소드 생성 가능
}

private interface Swimmable {
    val swimmability: Int // val이니 getter을 상속 클래스에서 구현해주길 기대
//        get() = 3 // 여기서 지정도 가능
    // "Kotlin에서는 backing field가 없는 프로퍼티를 interface에 만들 수 있다"

    fun act() {
        println(swimmability)
        println("어푸 어푸")
    }
}

private open class Base(
    open val number: Int = 100
) {
    init {
        println("Base class")
        println(number) // 0
        // 상위 클래스 생성자 실행되는 동안 하위 클래스의 number을 가져오므로,
        // 상위 클래스 생성자가 아직 실행 중이라 초기화가 이루어지지 않았으므로 0
    }
}

private class Derived(
    override val number: Int
) : Base(number) {
    init {
        println("Derived class")
    }
}
