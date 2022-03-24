package com.leather.workshop.global.common;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    public static final String FILE_NAME_EXPRESSION = "(?<=fileName=)(.*?)(?=\" style=)";

    public static List<String> findAllRegEx(String contents, String regEx){

        Pattern pat = Pattern.compile(regEx);
        Matcher match = pat.matcher(contents);

        List<String> resultList = new ArrayList<>();
        while (match.find()) {
            resultList.add(match.group());
        }

        return resultList;
    }
}

