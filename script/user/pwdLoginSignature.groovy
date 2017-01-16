import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

static void main(String... args) {
    if (args == null || args.length != 3) {
        System.err.println("参数序列应为：[ salt ] [ password ] [ mobile/userName/email ]")
        System.exit(-1)
    }
    System.out.println("signature:" + genLoginSignature(args))
}

static String genLoginSignature(String... args) {
    String salt = args[0]
    String password = args[1]
    StringBuffer sb = new StringBuffer()
    String hash = encryptBySha256(salt + password)
    System.out.println("hash: " + hash)
    System.out.println("hash2:" + base64(salt + password))
    sb.append(args[2]) // mobile/userName/email
    sb.append(hash)
    return encryptBySha256(sb.toString())
}

static String encryptBySha256(String decryptStr) {
    try {
        String ret = null
        if (null != decryptStr && "" != decryptStr.trim()) {
//            ret = Base64.encoder.encodeToString(MessageDigest.getInstance("SHA-256").digest(decryptStr.getBytes()))
            ret = Base64.getUrlEncoder().encodeToString(MessageDigest.getInstance("SHA-256").digest(decryptStr.getBytes()))
        }
        return ret.replaceAll("=", "")
    } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException(e)
    }
}

static String base64(String decryptStr) {
    return Base64.encoder.encodeToString(MessageDigest.getInstance("SHA-256").digest(decryptStr.getBytes()))
}