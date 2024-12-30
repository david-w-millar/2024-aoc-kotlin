package day03

object MulExamples {
    val jawn: String get() = $$"""]select(23,564)/$!where()>%mul(747,16)*why()mul(354,748)how()<?mul(29,805)where()mul(480,119)!,why()mul(685,393)(~'&[what()what()mul(376,146)-,<)do()^(mul(735,916)/~~,] what()where()mul(321,623)select()$#what() %#who()<*mul(363,643)where()[mul(360,266),:do()'mul(95,167)who()-select()@[{,)$select()mul(802,119) how()^: {from()mul(147,169)*select())^mul(488,194)$?when()mul(540,154)from()from()*^select()who()mul(8,750)where()mul(140,841)when()] >$(when()<:mul(428,793) where()from()how()/how()]*?mul(156,996))what()!,what()~@((mul(976,569)]-,>$-~%;mul(426,703)/mul(948,128)>+?+>?%select()*mul(477,567)why()%select()?!(@~how(){mul(182,79),mul(203,707)?[mul(186,170)select(283,626)*/*when()mul(130,392)')^&when(),[;mul(563,902)where()}*}<$/)how()mul(953,129)!!what()#what()!who()mul(852,652)~)+mul(973,163)$?why()]where()mul(158,596)when()@}what(29,454)mul(968,252)<'^'how()when()<*^mul(617,885)when()) +&;'mul(264,456)/mul(713,804),-mul(803,862)mul(575,310)[ why(527,60) )from()mul(475,876)from()when()*^$@:do()mul(557,2)'{^:-*what()mul(611,157) >- when()mul(894,415)!mul(856,397)from(),where()mul(13,373),!where(),do() {how()select()^:(#select(622,699)[mul(395,375)-##>+[what()?mul(535,15)/(];)mul(115,296)mul(201,604)^+[>+do()&:}how()/:mul(34,586)?where(375,645)?:-who()select()'why()>mul(389,101)don't()<^}who()mul(501,691)'select()mul(551,120),]?from(545,381)?*%~mul(492,926),:(who() {$ when()mul(348,721)'?/)?!what(784,670)mul(811,483): where()why()why()>$[when()do(),~*# {/mul(312,382),}*what(944,486)?^{+%mul(224,412)~why();?<]who()*^mul(199,783)what()from()@why()where()what()?select()(}mul(267,247) mul(126,337)select()mul(534,156)($%%}+*@mul(103,848):;'%mul(237,35)<&-where()mul(423,484),!]where()#!mul(281,866)select(750,996)(( *{<^%who()mul(437,982)}:mul(357,682)@< mul(124,834)}~mul(668,671)mul(787,282)</{[@+mul(669,479)&+who(324,639)when()mul(217,891)why()who(),who()!+~%who()from()mul(157,768)what()why()/mul(654,217)/?]+how()($mul(173,829))#(what()mul(78,373)(+{?&${([from()mul >from():where()'[mul(985,702)*{: -&where()how()mul(180,738)(from()@mul(240,76),[:'#!:select() mul(822,179)*#how()~!%!<mul(806;+from(28,284);@select()why()?what()how()#don't()select();;?how()[<mul(682,60)%+mul(166,261)!#<~who()'@who()/mul(991;mul(602,939)why()*how()mul(815 ~>who()who()how()where(184,532)#from() [mul(771,388)how()'~!^!@+mul(646,938)+,(({-mul(486,708)^%^from()-(;what()]mul(144,833)~why()%select()&<~how())mul(439,873)mul(677[[;{:?{>[ (mul(25,577))@:mul(727,412)why();?select()?what()};from()*mul(826,116)#*)/where()who();<@<mul(457,847)mul(145,20)^when()mul(547,892)}mul(368>!where()~when()where(597,883)-mul(835,616)'((where(808,96)',mul(649,224)&/ mul(35,958)who(871,394) :!-who()where()where()(mul(322,104^what()%,}[why()what()**who()mul(983,838)mul(614,657)what()&,mul(238,871)-{},select()>who()#>mul(943,599)select()select(558,572)?^who() <:mul(572,265))who()[why()!$,-mul(454,326)<mul(620,631)[who()]from()>mul(300,416)what()who()what()[;when()mul(786,381!<who()@}mul(588,123)mul(912}?-,&mul(757,105)*!!mul(646,183)~*^mul(208,472)^>&who()when()where(381,479) from()<!mul(374,508)',mul(936,836)&when()don't()<mul(87,618)"""
    val example1 = $$"xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))}"
    val example2 = $$"""
        |x
        |mul(2,4)
        |&mul[3,7]!^
        |don't()
        |_
        |mul(5,5)
        |+mul(32,64](
        |mul(11,8)
        |un
        |do()?
        |mul(8,5)
        |)}
        |""".trimMargin().split("\n").joinToString("")


    abstract class HasValue() { abstract fun getValue(): Int }

    open class Instruction(val name: String, val regex: Regex) : HasValue() {
        override fun getValue() = 0
        override fun toString() = name
    }

    private val mulRegex = """mul\((\d+),(\d+)\)""".toRegex()
    class MUL(val x: Int, val y: Int) : Instruction("mul($x,$y)", mulRegex) {
        override fun getValue() = x * y
        override fun toString() = "mul($x,$y)"
    }


    data class InputAndResult(val input: String,val result: Int) {
        override fun toString() = input
        fun value() = result
    }

    val DONT = Instruction("don't()", """don't\(\)""".toRegex())
    val DO = Instruction("do()", """do\(\)""".toRegex())

    fun fromInstructions(vararg instructions: Instruction) =
         StringBuilder().let { sb ->
            instructions.forEach { sb.append(it.name) }
            sb.toString()
        }

    fun instructionsToString(vararg instructions: Instruction) =
        fromInstructions(*instructions).toString()

    val example_50 = fromInstructions(DO, MUL(2,25))
    val example_100 = fromInstructions(DONT, MUL(2,25), DO, MUL(10,10))

    // ---------------------- MEH
    fun getMul(x: Int, y: Int) = MUL(x, y)

    val four = getMul(2,2)
    val mull11 = getMul(1,2)
    val mull12 = getMul(2,20)
    val mull13 = getMul(3,200)

    val mull21 = getMul(10,2)
    val mull22 = getMul(20,20)
    val mull23 = getMul(30,200)

    val mul31 = getMul(100,2)
    val mul32 = getMul(100,20)
    val mul33 = getMul(100,200)


    // DO, 4, DO, 4
    val example_8 =
        with(StringBuilder()) {
            append(DO)
            append(four)
            append(DO)
            append(four)
        }


}
