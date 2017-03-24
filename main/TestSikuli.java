import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestSikuli {

	private static Screen s = new Screen();
	private static String deviceUDID = "743A24D8-7500-4114-85F3-7D7AA3D8942F";
	
	@BeforeClass
	public static void setup() {
		System.out.println("Entering BeforeClass");
		//Open (and configure?) simulator
		try {
			Runtime.getRuntime().exec("open -a Simulator --args -CurrentDeviceUDID " + deviceUDID + " &");

			s.wait("imgs/SettingsIcon.png", 60);
		} catch (IOException | FindFailed ex) {
			ex.printStackTrace();
		}
	}
	
	@AfterClass
	public static void tearDown() {
		System.out.println("Entering AfterClass to close iPhone Simulator");
		//Close Simulator
		try {
			Runtime.getRuntime().exec("killall iPhone Simulator");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@BeforeMethod
	public void changeLocaleToEsUs() {
		System.out.println("Entering BeforeMethod to change locale to es_US");
        try {
	        s.click("imgs/SettingsIcon.png", 0);
	        s.wait("imgs/GeneralOptionsButton.png", 5);
	        s.click("imgs/GeneralOptionsButton.png", 0);
	        s.click("imgs/english/LangAndRegion.png", 0);
	        s.click("imgs/english/LangButton.png", 0);
	        s.wait("imgs/SearchTextField.png");
	        s.type("imgs/SearchTextField.png", "Spanish");
	        s.click("imgs/english/SpanishButton.png", 0);
	        s.click("imgs/english/Done.png", 0);
	        s.click("imgs/english/ConfirmChangeToSpanish.png", 0);
	        s.wait("imgs/BatteryIndicator.png", 30);
	        s.wait(5.0);
        
	        //Go back to home screen
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_META);
			robot.keyPress(KeyEvent.VK_H);
			
			
			robot.keyRelease(KeyEvent.VK_SHIFT);
			robot.keyRelease(KeyEvent.VK_H);
			robot.keyRelease(KeyEvent.VK_META);

			s.wait("imgs/SettingsIcon.png", 10);
        }
        catch(FindFailed  | AWTException e) {
        	e.printStackTrace();
        }
	}
	
	@AfterMethod
	public void changeLocaletoEnUs() {
		System.out.println("Entering AfterMethod to revert locale to en_US");
		try {
	        s.click("imgs/SettingsIcon.png", 0);
	        s.wait("imgs/spanish/LangButton.png", 5);
	        s.click("imgs/spanish/LangButton.png", 0);
	        s.wait("imgs/SearchTextField.png");
	        s.type("imgs/SearchTextField.png", "English");
	        s.click("imgs/spanish/EnglishButton.png", 0);
	        s.click("imgs/spanish/Ok.png", 0);
	        s.click("imgs/spanish/ConfirmChangeToEnglish.png", 0);
	        s.wait("imgs/BatteryIndicator.png", 30);
	        s.wait(5.0);
        
	        //Go back to home screen
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_META);
			robot.keyPress(KeyEvent.VK_H);
			
			
			robot.keyRelease(KeyEvent.VK_SHIFT);
			robot.keyRelease(KeyEvent.VK_H);
			robot.keyRelease(KeyEvent.VK_META);

			s.wait("imgs/SettingsIcon.png", 10);
        }
        catch(FindFailed  | AWTException e) {
        	e.printStackTrace();
        }
	}

	@Test
	public void testSomething() {
		System.out.println("Entering Test");
		//Pretend we execute a test here in es_US locale. Ideally, the java test generated from the CSV file would go right here.
		//Not sure how that will work with simulator though as appium i believe starts the simulator then installs the app and opens it right away.
	}

}