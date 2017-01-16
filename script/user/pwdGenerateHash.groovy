import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


static void main(String... args) {
    if (args.length == 0 || args[0] == null) {
        System.err.println("password can't be null")
        System.exit(-1)
    } else if (!isPassword(args[0])) {
        System.err.println("password illegal")
        System.exit(-1)
    }
    String salt = getUuid()
    System.out.println("salt:" + salt)
    System.out.println("hash:" + encryptBySha256(salt + args[0]))
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

static boolean isPassword(String password) {
    if (password != null) {
        return password.matches(/[0-9a-zA-Z.<>?!@#$%^&*_=\-+()]{8,32}/)
    }
    return false
}