import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GrammarExercise {
    public static void main(String[] args) {
        String firstWordList = "";
        String secondWordList = "";

        Scanner sc = new Scanner(System.in);
        firstWordList = sc.nextLine();
        secondWordList = sc.nextLine();

        System.out.println(firstWordList);
        System.out.println(secondWordList);

        List<String> result = findCommonWordsWithSpace(firstWordList,secondWordList);

        System.out.println(result);
    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {
        firstWordList = firstWordList.toLowerCase();
        secondWordList = secondWordList.toLowerCase();

        List<String> firstwordList = Arrays.asList(firstWordList.split(","));
        List<String> secondwordList = Arrays.asList(secondWordList.split(","));

        long errorWordsFirst = firstwordList.stream()
                .filter(str -> !str.matches("^[a-zA-Z]+$"))
                .count();

        long errorWordsSecond = secondwordList.stream()
                .filter(str -> !str.matches("^[a-zA-Z]+$"))
                .count();

        if (errorWordsFirst > 0 || errorWordsSecond > 0)
            throw new RuntimeException();

        long emptyWordsFirst = firstwordList.stream()
                .filter(str -> str.equals(""))
                .count();

        long emptyWordsSecond = secondwordList.stream()
                .filter(str -> str.equals(""))
                .count();

        if (emptyWordsFirst > 0 || emptyWordsSecond > 0)
            throw new RuntimeException();

        List<String> result = firstwordList.stream()
                .filter(secondwordList::contains).distinct().sorted(String.CASE_INSENSITIVE_ORDER).collect(Collectors.toList())
                .stream().map(String::toUpperCase).map(str->{
                    String r = "";
                    for (int i = 0; i < str.length() - 1; i++) {
                        r = r + str.charAt(i) + " ";
                    }
                    r += str.charAt(str.length()-1);
                    return r;
                }).distinct().collect(Collectors.toList());;

        System.out.println(result);
        return result;
    }
}