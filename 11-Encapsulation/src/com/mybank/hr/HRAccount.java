package com.mybank.hr;

import com.mybank.accts.Account;
import com.mybank.com.mybank.newhr.NewHRAccount;

public class HRAccount extends Account {
    public static void main(String[] args){
        /* Accessing super class protected members */
        Account simpleAcct = new Account();
        //simpleAcct.acctId = "111";

        HRAccount hrAcct = new HRAccount();
        //hrAcct.acctId = "111";

        Account hrAcct2 = new HRAccount();
        //hrAcct2.acctId = "111";

        /* Accessing subclass protected members */
        NewHRAccount newHRAcct = new NewHRAccount();

        //newHRAcct.acctId = "111"; // HRAccount owns this field
        //newHRAcct.name = "John"; // HRAccount does not own this field
    }
}
