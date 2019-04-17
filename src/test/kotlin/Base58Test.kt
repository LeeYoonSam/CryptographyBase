import junit.framework.Assert.assertEquals
import org.bitcoinj.core.Base58
import org.junit.Test

class Base58Test {
    /**
     * 다음 문장을 UTF16으로 인코딩한 결과를 base58로 변환하라. (따옴표 포함)
     */
    @Test
    fun base58() {
        val plainText = "\"UTF16란 무엇이고 베이스58이란 무엇인가?\""

        val charset = Charsets.UTF_16

        plainText.toByteArray(charset).let {
            println("utf16: ${it.contentToString()}")
            println("utf16 to plain ${it.toString(charset)}")

            val result = Base58.encode(it)
            println("base58: $result")

            assertEquals(plainText, Base58.decode(result).toString(charset))
        }
    }
}