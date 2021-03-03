package cpsc488_project;

public class MainScreen {

	public static void main(String[] args) {
		
		IDPasswords idPasswords = new IDPasswords();
		
		
		
		LoginPage2 loginPage2 = new LoginPage2(idPasswords.getLoginInfo());
		
	}
}
