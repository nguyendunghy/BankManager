package com.example.bankmanager.utils;

public class Constants {


	public static final class CODE {

		public static final String SUCCESS = "200";
	}

	public static final class BANK_ACCOUNT {

		public static final String LINK_EMPTY = "Link is required";
		public static final String ICON_EMPTY = "Icon is required";
		public static final String TITLE_EMPTY = "Title is required";
		public static final String DESCRIPTION_EMPTY = "Description is required";
		public static final String SHARED_BY_EMPTY = "Shared user is required";
		public static final String BANK_ACCOUNT_REQUIRED = "Bank account required";
		public static final String ACCOUNT_NAME_REQUIRED = "Bank account required";
		public static final String ACCOUNT_NO_REQUIRED = "Account number required   ";
		public static final String CURRENCY_REQUIRED = "Currency required";
		public static final String USER_NOT_FOUND = "User not found";
	}

    private Constants() {
	}

	public static final class REGISTER {
		public static final String USER_PASS_EMPTY = "User name or password empty";
		public  static final String CONFIRM_PASSWORD_NOT_MATCH= "Password and confirm password not matched";

		public  static final String USER_EXISTED= "User existed";;

	}

	public static final class LOGIN{

		public static final String INVALID_USER_PASS = "Invalid user or password";
		public static final String USER_PASS_EMPTY = "User name or password empty";

	}

	public static final String INTERNAL_ERROR = "404";
	public static final String SUCCESS = "SUCCESS";

	public static final long EXPIRED_TIME = 60 * 60 * 1000;

	public static final Long ACTIVE = 1L;
	public static final Long IN_ACTIVE = 0L;



}
