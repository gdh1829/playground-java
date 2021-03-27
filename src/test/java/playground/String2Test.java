package playground;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class String2Test {
    
    @ParameterizedTest(name = "Proper id recommendation")
    @CsvSource({
        "...!@BaT#*..y.abcdefghijklm,bat.y.abcdefghi",
        "z-+.^.,z--",
        "=.=,aaa",
        "123_.def,123_.def",
        "abcdefghijklmn.p,abcdefghijklmn"
    })
    public void solution(String input, String expected) {
        String phase1 = input.toLowerCase();
        // System.out.println(phase1);
        String phase2 = phase1.replaceAll("[^\\w\\-\\.]", "");
        // System.out.println(phase2);
        String phase3 = phase2.replaceAll("\\.+", ".");
        // System.out.println(phase3);
        String phase4 = phase3.replaceAll("^\\.|\\.$", "");
        // System.out.println(phase4);
        String phase5 = (phase4.length() == 0) ? phase4 += "a" : phase4;
        //System.out.println(phase5);
        String phase6 = (phase5.length() >= 16) ? doPhase4(phase5.substring(0, 15)) : phase5;
        // System.out.println(phase6);
        String phase7 = (phase6.length() <= 2) ? doPhase7(phase6, phase6.charAt(phase6.length() - 1)) : phase6;
        // System.out.println(phase7);

        String result = phase7;
        System.out.println("@@Final output: " + result);
        assertEquals(expected, result);
    }

    private String doPhase4(String input) {
        return input.replaceAll("^\\.|\\.$", "");
    }

    private String doPhase7(String input, char appender) {
        if (input.length() > 2) {
            return input;
        }  

        return doPhase7(input += appender, appender);
    }
}
