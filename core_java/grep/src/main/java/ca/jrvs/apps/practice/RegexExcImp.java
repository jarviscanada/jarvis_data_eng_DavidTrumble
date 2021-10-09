package ca.jrvs.apps.practice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExcImp implements RegexExc {

    @Override
    public boolean matchJpeg(String filename) {

        Pattern _pattern = Pattern.compile(".*.jpe?g$", Pattern.CASE_INSENSITIVE);
        Matcher _matcher = _pattern.matcher(filename);
        return _matcher.matches();
    }

    @Override
    public boolean matchIp(String ip) {

        Pattern _pattern = Pattern.compile("^[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}$");
        Matcher _matcher = _pattern.matcher(ip);
        return _matcher.matches();
    }

    @Override
    public boolean isEmptyLine(String Line) {

        Pattern _pattern = Pattern.compile("\\s");
        Matcher _matcher = _pattern.matcher(Line);
        return _matcher.matches();
    }

    public static void main(String[] args) {

        RegexExcImp regexExample = new RegexExcImp();


    }
}
