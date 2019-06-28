package emailapp;

import java.util.Scanner;

public class Email {
	private String firstName;
	private String lastName;
	private String password;
	private String department;
	private String email;
	private int mailboxCapacity = 500;
	private int defaultPasswordLength = 7;
	private String alternateEmail;
	
	// �����������, ����������� firstName, lastName
	public Email(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		System.out.println("EMAIL CREATED: " + this.firstName + " " + this.lastName);
		
		// ���������� �������� ���������� department
		this.department = setDepartment();
		System.out.println("Department: " + this.department);
		
		// �����, �������� ���������� ��������� ������
		this.password = randomPassword(defaultPasswordLength);
		System.out.println("Your password: " + this.password);
		
		// ���������� email ��������� firstName + lastName + department
		email = firstName.toLowerCase() + "." +lastName.toLowerCase() + "@" + department.toLowerCase() + ".ru";
		System.out.println("Your email is: " + email);
	}
	
	private String setDepartment() {
		System.out.print("DEPARTMENTS LIST:\n[1] - Sales\n[2] - Development\n[3] - Accounting\nEnter the number: ");
		Scanner in = new Scanner(System.in);
		int depChoice = in.nextInt();
		if(depChoice == 1) return "Sales";
		else if(depChoice == 2) return "Development";
		else if(depChoice == 3) return "Accounting";
		else return "";
	}
	
	// ��������� ���������� ������
	private String randomPassword(int length) {
		String passwordSym = "ABCDEFGHIJKLMNOPQRSTUWXYZ0123456789!@#$%";
		char[] password = new char[length];
		for (int i = 0; i < length; i++) {
			int sym = (int) (Math.random() * passwordSym.length());
			password[i] = passwordSym.charAt(sym);
		}
		
		return new String(password);
	}
	
	// ������ ����������� �����
	public void setMailboxCapacity(int capacity) {
		this.mailboxCapacity = capacity;
	}
	
	// ������ �������������� mail
	public void setAlternateEmail(String email) {
		this.alternateEmail = email;
	}
	
	// ������ ������
	public void changePassword(String password) {
		this.password = password;
	}
	
	// �����, ������� ���������� ��� ���������� � ��������
	public String showInfo() {
		return "FULL NAME: " + firstName + " " + lastName +
				"\nEMAIL: " + email +
				"\nMAILBOXCAPACITY: " + mailboxCapacity; 
	}
}
