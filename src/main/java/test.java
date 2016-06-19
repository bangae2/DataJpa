import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.encrypt.Encryptors;

/**
 * Created by bangae11 on 2016-06-19.
 */
public class test {

    public static void main(String[] args) {
        ShaPasswordEncoder en = new ShaPasswordEncoder();
        System.out.println(en.encodePassword("aa", null));
    }
}
