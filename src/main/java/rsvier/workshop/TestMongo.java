package rsvier.workshop;


import java.net.UnknownHostException;
import java.util.*;

import com.mongodb.*;

import rsvier.workshop.dao.*;
import rsvier.workshop.domain.*;
import rsvier.workshop.utility.DatabaseConnectionXML;

public class TestMongo {

	public static void main(String[] args) {
		
		AccountDAO accountDao = new AccountDAOImpMongo();
	//						create account and auto generated id = successful
	
		Account account2 = new Account();
		account2.setAccountType(1);
		account2.setEmail("tiger@gmail.com");
		account2.setPassword("99999");
		
		int generatedId = accountDao.createAccount(account2);
		System.out.println("generated ID : " + generatedId);
		
		
		
			//			get account by id = successful
		  
	//	Account account = accountDao.getAccountById(16);
	//	System.out.println(account.toString());
					
		/*
		
						update account = successful
		
		account.setPassword("44444");
		accountDao.updateAccount(account);
		
		*/
			//			get all accounts = successful
		
		/*
		List<Account> accountList = accountDao.getAllAccounts();
		for(Account account : accountList) {
			System.out.println(account.toString());
			
			
		}
		
		//           	get account by email  = successful
		/*
		
		Account account = accountDao.getAccountByEmail("hippo@gmail.com");
		System.out.println(account.toString());
		*/
		
		//				delete account = successful
		
	//	accountDao.deleteAccount(account);
		
		
		       
	
	}

}

