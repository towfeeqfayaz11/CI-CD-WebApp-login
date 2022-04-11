package org.data.service;

public class ServiceClass implements UserService {
	
	@Override
	public boolean validateUser(String userName, String userPass) {

		System.out.println("ServiceClass.validateUser()");
	   if(userName.equals("viju") && userPass.equals("data2"))
	   {
		   return true;	   
	   }
	   else return false;
		}

}
