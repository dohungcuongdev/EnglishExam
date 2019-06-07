package test;

import helper.Authentication;
import helper.Logger;

public class TestAuthentication {

	public static void main(String[] args) {
		Logger.infor(Authentication.getEncryptPassword("vinhoccho"));

	}

}
