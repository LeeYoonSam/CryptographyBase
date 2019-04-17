import org.bouncycastle.crypto.digests.RIPEMD160Digest
import org.junit.Test
import org.bouncycastle.util.encoders.Hex
import org.bouncycastle.jcajce.provider.digest.SHA3



class HashTest {
    /**
     * 다음 두 단어를 UTF-8로 표현한 바이트 배열을 가지고 SHA3(256비트)와 RIPEMD160 해시값을 계산해 보라
     * apple, appel
     * 자바: bouncy-castle / sponge-castle
     * 자바스크립트: crypto-js
     */

    @Test
    fun convertWordsSha3RIPEMD160() {
        val apple = "apple"
        val appel = "appel"

        /**
         * 인코딩한 배열의 3,4 번째 인덱스가 바뀌어있다. le, el
         * apple [97, 112, 112, 108, 101]
         * appel [97, 112, 112, 101, 108]
         */
        // 42a990655bffe188c9823a2f914641a32dcbb1b28e8586bd29af291db7dcd4e8
        hashSHA3(apple, 256)

        // 828bc085b3a6d3ab703bf238bbb2a497f58e2b11dc6d079fe4c2be516896aa57
        hashSHA3(appel, 256)

        // dfa40098c95721adb3e6b2cf23568b7f6d968694
        hashRIPEMD160(apple)

        // 966f418a3d4b282e2b046d33e13ae650205002a7
        hashRIPEMD160(appel)
    }

    fun hashSHA3(key: String, hash: Int) {
        SHA3.DigestSHA3(hash).run {
            update(key.toByteArray(Charsets.UTF_8))
            println(Hex.toHexString(this.digest()))
        }
    }

    fun hashRIPEMD160(key: String) {
        RIPEMD160Digest().run {
            val output = ByteArray(this.digestSize)
            update(key.toByteArray(Charsets.UTF_8), 0, key.toByteArray().size)
            doFinal(output, 0)
            println(Hex.toHexString(output))
        }
    }
}