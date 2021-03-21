package playground;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class String1Test {

    /**
     * 문자열 내의 각각의 문자는 앞에서부터 동일한 사이즈로만 압축 가능. 2개씩, 3개씩, ...
     * 균일한 압축 사이즈로 분류시, 남은 문자열은 그대로 붙는다. aabbc에 대하여 2개씩의 경우: aa bb c 
     */
    @ParameterizedTest(name = "주어진 문자열의 가장 짧은 압축형 만들기")
    @ValueSource(strings = {
        "aaaabbbbcccddddde", 
        "abcabbbbbbbbcabcddd", 
        "xabcdeabcdeffa", 
        "zzyzzyzzyzzy"}
    )
    public void test(String input) {
        String answer = input;
        
        // 하나씩 자르는 것은 의미가 없으므로 skip하여 2개씩 시작
        // size / 2 => 주어진 문자열의 절반 길이보다 큰 기준으로 분할 하는 것은 의미가 없기 때문. 
        for (int i = 2; i <= input.length() / 2; i++) {
            String format = String.format("(\\w{%d})", i);
            Matcher matcher = Pattern.compile(format).matcher(input);
            
            List<String> matches = new ArrayList<>();
            while (matcher.find()) {
                matches.add(matcher.group(1));
            }
            // 단위로 쪼개고 남은 문자들에 대하여 하나의 그룹으로 처리
            int leftStartIndex = matches.size() * i;
            matches.add(input.substring(leftStartIndex));            
            
            // for (String string : matches) {
            //     System.out.println(string);
            // }

            int count = 1;
            String result = "";
            for (int j = 0; j < matches.size(); j++) {
                if (j + 1 < matches.size() && matches.get(j).equals(matches.get(j + 1))) {
                    count++;
                } else {
                    if (count == 1) {
                        result += matches.get(j);
                    } else {
                        result += (count + matches.get(j));
                        count = 1;
                    }
                }
            }

            System.out.println(String.format("per %d chars: %s", i, result));
            
            if (result.length() < answer.length()) {
                answer = result;
            }
        }

        System.out.println(String.format("=== THE ANSWER is %s", answer));
    }
}
