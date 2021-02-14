/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author muhdakmaldanial
 */
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class splitAddress {

    public String addressString;
    public String aptNum;
    public String city;
    public String state;
    public String postCode;
    public String street;
    public String section;

    public void completeAddress() {
        addressString = userInput.addressString;
        if (addressString.contains(",") && !addressString.contains("  ")) {
            String[] comma = addressString.split(",");
            List<String> commaList = Arrays.asList(comma);
            List<String> trimmedComma = commaList.stream().map(String::trim).collect(Collectors.toList());
            Iterator<String> iterateComma = trimmedComma.iterator();
            while (iterateComma.hasNext()) {
                String listComma = iterateComma.next();
                if (listComma.matches("No (\\w+)(\\s(\\w)+)*") || listComma.matches("No. (\\w+)(\\s(\\w)+)*") || listComma.matches("No(\\d+)*") || listComma.matches("No.(\\d+)*")) {
                    aptNum = listComma;
                    iterateComma.remove();
                }
                if (listComma.contains("No") && listComma.contains("Jalan")) {
                    System.out.println("hello");
                }
                if (listComma.matches("Jalan (\\w+)(\\s(\\w)+)*") || listComma.matches("Jln (\\w+)(\\s(\\w)+)*") || listComma.matches("Lorong (\\w+)(\\s(\\w)+)*") || listComma.matches("Persiaran (\\w+)(\\s(\\w)+)*")) {
                    street = listComma;
                    iterateComma.remove();
                }
                listComma = listComma.replace(".", "");
                if (listComma.matches("Kuala Terengganu") || listComma.matches("Kuala Lumpur") || listComma.matches("Kajang")
                        || listComma.matches("Bangi") || listComma.matches("Damansara") || listComma.matches("Petaling Jaya")
                        || listComma.matches("Puchong") || listComma.matches("Subang Jaya") || listComma.matches("Cyberjaya")
                        || listComma.matches("Putrajaya") || listComma.matches("Mantin") || listComma.matches("Kuching")
                        || listComma.matches("Seremban")) {
                    city = listComma;
                    iterateComma.remove();
                }
                if (listComma.matches("Selangor") || listComma.matches("Terengganu") || listComma.matches("Pahang")
                        || listComma.matches("Kelantan") || listComma.matches("Melaka") || listComma.matches("Pulau Pinang")
                        || listComma.matches("Kedah") || listComma.matches("Johor") || listComma.matches("Perlis")
                        || listComma.matches("Sabah") || listComma.matches("Sarawak")) {
                    state = listComma;
                    iterateComma.remove();
                }
                String regexPostCode = "([0-9]{5}|[0-9]{4})";
                Pattern pattern = Pattern.compile(regexPostCode);
                Matcher matcher = pattern.matcher(listComma);
                if (listComma.matches(regexPostCode)) {
                    while (matcher.find()) {
                        String temp = matcher.group();
                        int temp2 = Integer.parseInt(temp);
                        if (temp2 >= 1000 && temp2 <= 98859) {
                            postCode = matcher.group();
                            iterateComma.remove();
                        }
                    }
                } else {
                    Pattern pattern2 = Pattern.compile("([0-9]{5}|[0-9]{4})");
                    Matcher matcher2 = pattern2.matcher(listComma);
                    Pattern pattern3 = Pattern.compile("\\d+(\\s[a-zA-Z]{0,})*");
                    Matcher matcher3 = pattern3.matcher(listComma);
                    while (matcher2.find()) {
                        String temp3 = matcher2.group();
                        int temp4 = Integer.parseInt(temp3);
                        while (matcher3.find()) {
                            String cityNewList = matcher3.group().replaceAll("[0-9]", "").trim();
                            if (temp4 >= 1000 && temp4 <= 98859) {
                                postCode = matcher2.group();
                            }
                            if (cityNewList.matches("Kuala Terengganu") || cityNewList.matches("Kuala Lumpur") || cityNewList.matches("Kajang")
                                    || cityNewList.matches("Bangi") || cityNewList.matches("Damansara") || cityNewList.matches("Petaling Jaya")
                                    || cityNewList.matches("Puchong") || cityNewList.matches("Subang Jaya") || cityNewList.matches("Cyberjaya")
                                    || cityNewList.matches("Putrajaya") || cityNewList.matches("Mantin") || cityNewList.matches("Kuching")
                                    || cityNewList.matches("Seremban")) {
                                city = cityNewList;
                            }
                        }
                        iterateComma.remove();
                    }
                }
            }
            String trimmedSection = Arrays.toString(trimmedComma.toArray()).replace("[", "").replace("]", "").replaceAll("\\.(?!\\w)", "").trim();
            if (!trimmedSection.isEmpty()) {
                section = trimmedSection;
            }

        } else if (!addressString.contains(",") && !addressString.contains("  ")) {
            String[] blank = addressString.split(" ");
            List<String> blankList = Arrays.asList(blank);
            StringBuilder stringBlankList = new StringBuilder();
            for (int i = 0; i < blankList.size(); i++) {
                stringBlankList.append(" ").append(blankList.get(i));
            }
            String copy = stringBlankList.toString();
            String replaceCopy = copy.replaceAll("\\.(?!\\w)", "");
            replaceCopy = replaceCopy.trim();
            Iterator<String> iterateBlank = blankList.iterator();
            StringBuilder stringIterateBlank = new StringBuilder();
            while (iterateBlank.hasNext()) {
                String newBlankList = iterateBlank.next();
                Pattern postPattern = Pattern.compile("([0-9]{5}|[0-9]{4})");
                Matcher postMatch = postPattern.matcher(newBlankList);
                String newPostCode;
                while (postMatch.find()) {
                    String temp5 = postMatch.group();
                    int temp6 = Integer.parseInt(temp5);
                    if (temp6 >= 1000 && temp6 <= 98859) {
                        newPostCode = postMatch.group();
                        postCode = newPostCode;
                        stringIterateBlank.append(" ").append(postCode);
                    }
                }
                if (newBlankList.matches("No") || newBlankList.matches("No.")) {
                    int i = blankList.indexOf(newBlankList);
                    String newAptNum = blankList.get(i) + " " + blankList.get(i + 1);
                    String replaceAll = newAptNum.replaceAll("\\.(?!\\w)", "");
                    aptNum = replaceAll;
                    stringIterateBlank.append(" ").append(aptNum);
                }
                if (newBlankList.matches("No\\d+") || newBlankList.matches("No.\\d+")) {
                    int i = blankList.indexOf(newBlankList);
                    String newAptNum = blankList.get(i);
                    String replaceAll = newAptNum.replaceAll("\\.(?!\\w)", "");
                    aptNum = replaceAll;
                    stringIterateBlank.append(" ").append(aptNum);
                }
                if (newBlankList.matches("Jalan") || newBlankList.matches("Jln") || newBlankList.matches("Lorong") || newBlankList.matches("Persiaran")) {
                    int i = blankList.indexOf(newBlankList);
                    String newStreet = blankList.get(i) + " " + blankList.get(i + 1);
                    String replaceAll = newStreet.replaceAll("\\.(?!\\w)", "");
                    street = replaceAll;
                    stringIterateBlank.append(" ").append(street);
                }
                if (newBlankList.matches("Selangor") || newBlankList.matches("Terengganu") || newBlankList.matches("Pahang")
                        || newBlankList.matches("Kelantan") || newBlankList.matches("Melaka") || newBlankList.matches("Kedah")
                        || newBlankList.matches("Johor") || newBlankList.matches("Perlis") || newBlankList.matches("Sabah") || newBlankList.matches("Sarawak")) {
                    String replaceAll = newBlankList.replaceAll("\\.(?!\\w)", "");
                    state = replaceAll;
                    stringIterateBlank.append(" ").append(state);
                }
                if (newBlankList.matches("Pulau")) {
                    String replaceAll = newBlankList.replaceAll("\\.(?!\\w)", "");
                    state = replaceAll + " Pinang";
                    stringIterateBlank.append(" ").append(state);
                }

                if (newBlankList.matches("Kajang") || newBlankList.matches("Bangi") || newBlankList.matches("Damansara")
                        || newBlankList.matches("Puchong") || newBlankList.matches("Cyberjaya") || newBlankList.matches("Putrajaya")
                        || newBlankList.matches("Mantin") || newBlankList.matches("Kuching") || newBlankList.matches("Seremban")) {
                    String replaceAll = newBlankList.replaceAll("\\.(?!\\w)", "");
                    city = replaceAll;
                    stringIterateBlank.append(" ").append(city);
                }
                if (newBlankList.matches("Kuala") || newBlankList.matches("Petaling") || newBlankList.matches("Subang")) {
                    String replaceAll = newBlankList.replaceAll("\\.(?!\\w)", "");
                    int number = blankList.indexOf(replaceAll);
                    city = replaceAll + " " + blankList.get(number + 1);
                    stringIterateBlank.append(" ").append(city);
                }
            }
            String stringIBlank = stringIterateBlank.toString();
            stringIBlank = stringIBlank.trim();
            String[] splitCopy = replaceCopy.split(" ");
            String[] splitBlank = stringIBlank.split(" ");
            List<String> splitCopyList = Arrays.asList(splitCopy);
            List<String> splitBlankList = Arrays.asList(splitBlank);
            List<String> union = new ArrayList<>(splitCopyList);
            union.addAll(splitBlankList);
            List<String> intersection = new ArrayList<>(splitBlankList);
            intersection.retainAll(splitBlankList);
            union.removeAll(intersection);
            StringBuilder unionBlankList = new StringBuilder();
            for (String n : union) {
                if (section == null) {
                    section = n;
                    unionBlankList.append(" ").append(n);
                } else {
                    unionBlankList.append(" ").append(n);
                    unionBlankList.toString();
                    String uList = unionBlankList.toString();
                    String trim = uList.trim();
                    section = trim;
                }
            }
        } else {
            System.out.println("Please enter your address in the correct format!");
        }

        userInput.aptNum = aptNum;
        userInput aptNumUser = new userInput();
        aptNumUser.getAptNum();

        userInput.city = city;
        userInput cityUser = new userInput();
        cityUser.getCity();

        userInput.state = state;
        userInput stateUser = new userInput();
        stateUser.getState();

        userInput.postCode = postCode;
        userInput postCodeUser = new userInput();
        postCodeUser.getPostCode();

        userInput.street = street;
        userInput streetUser = new userInput();
        streetUser.getStreet();

        userInput.section = section;
        userInput sectionUser = new userInput();
        sectionUser.getSection();
    }
}
