package com.goodfood.api.request;

import com.sun.istack.NotNull;

public class LoginForm
{
	@NotNull
	private String username;
	
	@NotNull
	private String password;


	// ***************
	// CONSTRUCTOR
	// ***************

	public LoginForm()
	{

	}

	public LoginForm(String username, String password)
	{
		this.username = username;
		this.password = password;
	}


	// ***************
	// GETTER AND SETTER
	// ***************

	public String getUsername()
	{
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
}
