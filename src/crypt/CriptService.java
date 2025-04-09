package crypt;

import constant.Alphabets;
import java.util.ArrayList;

//Логіка зашифрування файлу
public class CriptService {
    StringBuilder text;

    public StringBuilder cript(StringBuilder text, int key) {
        StringBuilder result = new StringBuilder();
        ArrayList<Character> alphabet = Alphabets.ALPHABET_EN;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            int index = alphabet.indexOf(c);
            if (index != -1) {
                int shiftedIndex = (index + key) % alphabet.size();
                result.append(alphabet.get(shiftedIndex));
            } else {
                result.append(c);
            }
        }
        return result;
    }

    public StringBuilder deCript(StringBuilder text, int key) {
        return cript(text, -key);
    }
}
