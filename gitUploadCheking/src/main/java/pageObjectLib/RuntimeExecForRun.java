package pageObjectLib;

import java.io.IOException;

public class RuntimeExecForRun {

	public static void main(String[] args) throws IOException {
		 // Runtime.getRuntime().exec("Desktop/Run");
		
	// Process process= Runtime.getRuntime().exec("D://Ashish//5.jpg");
		
		
		String command = "cmd.exe /c start "+"taskkill /f /im geckodriver.exe";
		Process child = Runtime.getRuntime().exec(command);
		
	//	Process process= Runtime.getRuntime().exec("C:\\Windows\\System32\\Notepad.exe");
	//  System.out.println("Run is opened!!");
	}
}
