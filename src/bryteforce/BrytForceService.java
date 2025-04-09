package bryteforce;

import constant.Alphabets;
import crypt.CriptService;
import java.util.Arrays;
import java.util.List;

public class BrytForceService {
    private static final List<String> COMMON_WORDS = Arrays.asList(" the ", " and ", " is ", " to ", " in ", " you ", " a ");

    public int brytDecript(StringBuilder encryptedText) {
        CriptService criptService = new CriptService();

        for (int key = 1; key < Alphabets.ALPHABET_EN.size(); key++) {
            StringBuilder attempt = criptService.deCript(encryptedText, key);
            String attemptText = attempt.toString();

            if (looksLikeRealText(attemptText)) {
                return key;
            }
        }
         return -1;
    }

    private boolean looksLikeRealText(String text) {
        for (String word : COMMON_WORDS) {
            if (text.contains(word)) {
                return true;
            }
        }
        return false;
    }
}
