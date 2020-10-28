package connection;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Coder {

    public static String code(String st) {
        //на лекциях говорили, что нужно использовать рандомный набор символов, так называемый "соль"
        //для кодировки пароля
        st += "asdwquqowehiquwkqw;epqwdowefweib";
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
        }

        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);
        //записываем набор байтов в 16-ричную кодировку
        //если мы будем хранить в бд массив байтов, то мы будем хранить ссылку на массив
        //так как массив байтов - объект, то каждый раз это будет новая ссылка, потому разное время создания объекта
        while (md5Hex.length() < 32) {
            md5Hex = "0" + md5Hex;
        }
        return md5Hex;
    }
}
