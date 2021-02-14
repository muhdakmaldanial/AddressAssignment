/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author muhdakmaldanial
 */
import java.util.ArrayList;

public class displayAddress {

    public static String aptNum;
    public static String street;
    public static String section;
    public static String city;
    public static String state;
    public static String postCode;

    public void showAddress() {
        aptNum = userInput.aptNum;
        street = userInput.street;
        section = userInput.section;
        city = userInput.city;
        state = userInput.state;
        postCode = userInput.postCode;

        ArrayList<String> output = new ArrayList<String>();

        output.add(aptNum);
        output.add(street);
        output.add(section);
        output.add(city);
        output.add(state);
        output.add(postCode);

        if (aptNum != null || street != null || section != null || city != null || state != null || postCode != null) {
            System.out.println("Output: \n" + "{");

            if (aptNum != null) {
                System.out.println("'apt': " + "'" + output.get(0) + "',");
            }
            if (street != null) {
                System.out.println("'street': " + "'" + output.get(1) + "',");
            }
            if (section != null) {
                System.out.println("'section': " + "'" + output.get(2) + "',");
            }
            if (city != null) {
                System.out.println("'city': " + "'" + output.get(3) + "',");
            }
            if (state != null) {
                System.out.println("'state': " + "'" + output.get(4) + "',");
            }
            if (postCode != null) {
                System.out.println("'postcode': " + "'" + output.get(5) + "',");
            }
            System.out.println("}");
        }
    }
}
