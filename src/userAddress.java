/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author muhdakmaldanial
 */

import java.io.InputStreamReader;
import java.util.Scanner;

public class userAddress {
    public userInput input;
    
    public userAddress() {
        input = new userInput();
    }

    public void endAddress(){
        System.out.println("Input: ");
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        String input = String.valueOf(scanner.nextLine());
        userInput.addressString = input;
        userInput retrieve = new userInput();
        retrieve.getAddressString();
        splitAddress complete = new splitAddress();
        complete.completeAddress();
    }
}