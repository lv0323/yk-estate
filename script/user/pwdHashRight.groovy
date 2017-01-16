import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

static void main(String... args) {
    if (args == null || args.length != 2) {
        System.err.println("参数序列应为：[ salt ] [ password ]")
        System.exit(-1)
    }
    String salt = args[0]
    String hash = encryptBySha256(salt + args[1])
    System.out.println("hash:" + hash)
}

static String encryptBySha256(String decryptStr) {
    try {
        String ret = null
        if (null != decryptStr && "" != decryptStr.trim()) {
            ret = Base64.getUrlEncoder().encodeToString(MessageDigest.getInstance("SHA-256").digest(decryptStr.getBytes()))
        }
        return ret.replaceAll("=", "")
    } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException(e)
    }
}