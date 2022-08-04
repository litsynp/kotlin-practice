package com.litsynp.kotlinpractice.javatokotlin

/** 20강. 코틀린의 scope function */
fun main() {
    val person = Lec20Person("litsynp", 20)

    // 1. scope function이란 무엇인가?
    // -
    // scope: 영역
    // function: 함수

    // -
    // scope function (영역 함수): 일시적인 영역을 형성하는 함수
    // 람다를 사용해 일시적인 영역을 만들고 코드를 더 간결하게 만들거나,
    // method chaining에 활용하는 함수를 scope function 이라고 한다.


    // 2. scope function의 분류
    // 외우지 말 것
    // - 5개 종류: let, run, also, apply, with

    // * ---------*--------*----------*
    // |   반환값   | it 사용 | this 사용 |
    // * ---------*--------*----------*
    // | 람다의 결과 | let    | run      |
    // | 객체의 결과 | also   | apply    |
    // |          |        |          |
    // * ---------*--------*----------*
    // - with는 확장함수가 아니다. 나머지는 확장함수다. (멤버함수 사용하듯이 사용)
    // - this: 생략이 가능한 대신, 다른 이름을 붙일 수 없다.
    // - it: 생략이 불가능한 대신, 다른 이름을 붙일 수 있다.

    // 왜 차이가 나는가?
    // => 코틀린 문법 때문에 차이가 존재한다.
    //    - let은 일반 함수를 받는다 (block: (T) -> R)
    //    - run은 확장 함수를 받는다 (block: T.() -> R)

    val value1 = person.let {
        it.age
    }

    val value2 = person.run {
        this.age
    }

    val value3 = person.also {
        it.age
    }

    val value4 = person.apply {
        this.age
    }

    // with(파라미터, 람다): this를 사용해 접근하고, this는 생략 가능하다.
    with(person) {
        println(name)
        println(this.age)
    }


    // 3. 언제 어떤 scope function을 사용해야 할까?

    // ===============================
    // - let

    // a. 하나 이상의 함수를 call chain 결과로 호출할 때
    val strings = listOf("APPLE", "CAR")
    strings.map { it.length } // String -> 길이
        .filter { it > 3 } // 길이 -> 길이 3 이상
        .let(::println) // == { lengths -> println(lengths) }
    // let은 앞에 있는 전체 결과인 List<Int>에 대해 호출되므로 lengths로 표현

    // b. non-null 값에 대해서만 code block을 실행시킬 때
    // 가장 많이 사용되는 경우!
    val str = "APPLE"
    val length = str?.let {
        println(it.uppercase())
        it.length
    }

    // c. 일회성으로 제한된 영역에 지역 변수를 만들 때
    // private function 등으로 빼서 depth를 줄이려고 노력하는 편이라 잘 안씀
    val numbers = listOf("one", "two", "three", "four")
    val modifiedFirstItem = numbers.first()
        .let { firstItem ->
            if (firstItem.length >= 5) firstItem else "!$firstItem!"
        }
        .uppercase()
    println(modifiedFirstItem)

    // ===============================
    // - run
    // a. 객체 초기화와 반환 값의 계산을 동시에 해야할 때
    // val person = Person("litsynp", 100).run(personRepository::save)
    // => 실제 DB에 저장된 person이 나오게 된다!

    // b. 객체를 만들어 DB에 바로 저장하고, 그 인스턴스를 활용할 때
    // val person = Person("litsynp", 100).run {
    //     hobby = "독서" // this를 통해 인스턴스에 접근
    //     personRepository.save(this)
    // } // 저장된 person을 반환

    // => 개인적으로는 잘 사용하지 않는다.
    // 아래 코드가 더 익숙하기도 하고,
    // val person = personRepository.save(Person("litsynp", 100))
    // 반복되는 생성 후처리는 생성자, 프로퍼티, init 블록으로 넣는 것이 좋다.

    // 그러나 생성자가 너무 길어진다면 run을 쓰면 더 깔끔해지긴 한다
    // 어떤 게 더 예쁜지 보고 판단할 수 있다

    // ===============================
    // - apply
    // 객체 그 자체가 반환된다.
    // a. 객체 설정을 할 때에 객체를 수정하는 로직이 call chain 중간에 필요할 때
    data class Lec20Person3(
        val name: String,
        var age: Int,
        var hobby: String = "cards",
    ) {
        fun growOld() {
            age += 1
        }
    }

    // e.g., Test Fixture를 만들 때
    fun createPerson(
        name: String,
        age: Int,
        hobby: String,
    ): Lec20Person3 {
        return Lec20Person3(name = name, age = age).apply {
            this.hobby = hobby
        }
    }
    // 이런 코드도 가능은 합니다
    val person4 = Lec20Person3("litsynp", 20)
    person4.apply { this.growOld() } // 중간에 수정
        .let { println(it) }
    // 잘은 쓰지 않는다

    // ===============================
    // - also
    // 객체 그 자체가 반환된다. (apply와 비슷)
    // a. 객체를 수정하는 로직이 call chain 중간에 필요할 때
    mutableListOf("one", "two", "three")
        .also { println(println("four 추가 이전 지금 값: $it")) }
        .add("four")

    // Java 문법에 익숙하다면 also보단 아래처럼 사용하게 된다
    val numbers2 = mutableListOf("one", "two", "three")
    println("The list elements before adding new one: $numbers2")
    numbers2.add("four")

    // ===============================
    // - with
    // 특정 객체를 다른 객체로 변환해야 하는데,
    // 모듈 간의 의존성에 의해
    // 정적 팩토리, 혹은 toClass 함수를 만들기 어려울 때
    // e.g.,
    // return with(person) {
    //     PersonDto(
    //         name = name,
    //         age = age,
    //     )
    // }
    // => this를 생략할 수 있어, 필드가 많아도 코드가 간결해진다.
    // 객체를 컨버팅해야하는데, 한쪽에 로직을 넣기 어려울 때 사용하는 편이다.

    // ===============================


    // 4. scope function과 가독성
    // scope function을 사용한 코드가 그렇지 않은 코드보다 가독성 좋은 코드일까?

    // [Effective Kotlin 예제] -- 기능은 동일하다.
    // 1번 코드
    // if (person != null && person.isAdult) {
    //     view.showPerson(person)
    // } else {
    //     view.showError()
    // }

    // 2번 코드
    // person?.takeIf { it.isAdult }
    //     ?.let(view::showPerson)
    //     ?: view.showError() // person이 null이거나, takeIf의 isAdult가 null일 때 실행

    // 사람마다 기준은 다르지만, 1번 코드가 더 좋은 코드라고 생각한다.
    // 1. 구현 2는 숙련된 코틀린 개발자만 더 알아보기 쉽다. 어쩌면 숙련된 코틀린 개발자도 잘 이해하지 못할 수 있다.
    // 2. 구현 1의 디버깅이 더 쉽다. (IDE에도 더 전통적인 코드에 대한 디버깅 방법론이 더 잘 갖춰짐)
    // 3. 구현 1이 수정도 더 쉽다. (추가 요구사항 반영 측면, takeIf, let 처럼 함수형으로 짜여있고 흐름도 일정하지 않아 수정 어렵다)
    //      + view.showPerson()이 null을 반환한다면 elvis 연산자에서 view.showError도 호출된다 (둘 다 호출되는 버그)

    // 사용 빈도가 적은 관용구는 코드를 더 복잡하게 만들고
    // 이런 관용구들을 한 문장 내에서 조합해 사용하면
    // 복잡성이 훨씬 증가한다.

    // 하지만 scope function을 사용하면 안되는 것도 아니다!
    // 적절한 convention을 적용하면 유용하게 활용할 수 있다
    // - 팀에서 코틀린에 대한 숙련도
    // - 개인의 코드의 선호도
    // 등 다양한 요인에 의해 적절한 convention을 따르면 되겠다.
}

data class Lec20Person(
    val name: String,
    val age: Int,
)

// 이런 함수가 있다고 가정.
fun printPerson(person: Lec20Person?) {
    if (person != null) {
        println(person.name)
        println(person.age)
    }
}

// 이렇게 바꿀 수 있다
// let에 람다를 넣어 일시적인 영역이 생겼다
fun printPerson2(person: Lec20Person?) {
    // safe call로 null이 아닐 때 let 호출
    person?.let { // scope function의 한 종류
        // 일시적인 영역을 생성한다
        // let: 확장함수. 람다를 받아, 람다 결과를 반환한다. (block: (T) -> R)
        // 람다를 사용한다. 따라서 it을 통해 person에 접근
        println(it.name)
        println(it.age)
    }
}
