import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

static void main(String... args) {
    if (args == null || args.length != 3) {
        System.err.println("参数序列应为：[ oldSalt ] [ oldPassword ] [ password ]")
        System.exit(-1)
    }
    String salt = getUuid()
    String hash = encryptBySha256(salt + args[2])
    System.out.println("salt:" + salt)
    System.out.println("hash:" + hash)
    System.out.println("signature:" + genOldSignature(args[0], args[1], salt, hash))
}

static String genOldSignature(String oldSalt, String oldPassword, String salt, String hash) {
    StringBuffer sb = new StringBuffer()
    sb.append(encryptBySha256(oldSalt + oldPassword))
    sb.append(salt) // salt
    sb.append(hash) // hash
    return encryptBySha256(sb.toString());
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

static String getUuid() {
    return UUID.randomUUID().toString()
}