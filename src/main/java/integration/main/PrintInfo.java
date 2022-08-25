package integration.main;

import integration.values.LoginValues;
import integration.values.ProjectValues;

public class PrintInfo {
	static LoginValues loginValues = LoginValues.getInstance();		
	static ProjectValues projectValues = ProjectValues.getInstance();
	
	public static void startBlackDuck() {
		 System.out.println("Start BlackDuck Process");
		 System.out.println("================================================================================================");
		 System.out.println("                                                                                                ");
		 System.out.println("  888888b.   888                   888           8888888b.                    888               "); 
		 System.out.println("  888   88b  888                   888           888    88b                   888               ");  
		 System.out.println("  888  .88P  888                   888           888    888                   888               ");  
		 System.out.println("  8888888K.  888  8888b.   .d8888b 888  888      888    888 888  888  .d8888b 888  888          "); 
		 System.out.println("  888   Y88b 888      88b d88P     888 .88P      888    888 888  888 d88P     888 .88P          "); 
		 System.out.println("  888    888 888 .d888888 888      888888K       888    888 888  888 888      888888K           "); 
		 System.out.println("  888   d88P 888 888  888 Y88b.    888  88b      888  .d88P Y88b 888 Y88b.    888  88b          ");
		 System.out.println("  8888888P   888  Y888888   Y8888P 888  888      8888888P     Y88888   Y8888P 888  888          ");
		 System.out.println("                                                                                                ");
		 System.out.println("================================================================================================");
	}
	
	
	public static void printBlackDuckInfo() {
		
		System.out.println();
		System.out.println("===================================================================");
		System.out.println("BlackDuck Server URL: " +  loginValues.getBDserverUri());
		System.out.println("BlackDuck ApiKey: " + "*******");
		System.out.println("BlackDuck Project Name/Id: " + projectValues.getBDprojectName() + " / " + projectValues.getBDprojectId());
		System.out.println("BlackDuck Project VersionName Name/Id: " + projectValues.getBDprojectVersionName() + " / " + projectValues.getBD_projectVersionId());		
		System.out.println("===================================================================");
		System.out.println();
	}
	
	
	public static void startFosslight() {	
		 System.out.println("=================================================================================================================================================");
		 System.out.println("   8 8888888888       ,o888888o.       d888888o.      d888888o.   8 8888          8 8888     ,o888888o.    8 8888        8 8888888 8888888888    ");
		 System.out.println("   8 8888          . 8888     `88.   .`8888:' `88.  .`8888:' `88. 8 8888          8 8888    8888     `88.  8 8888        8       8 8888          ");
		 System.out.println("   8 8888         ,8 8888       `8b  8.`8888.   Y8  8.`8888.   Y8 8 8888          8 8888 ,8 8888       `8. 8 8888        8       8 8888          ");
		 System.out.println("   8 8888         88 8888        `8b `8.`8888.      `8.`8888.     8 8888          8 8888 88 8888           8 8888        8       8 8888          ");
		 System.out.println("   8 888888888888 88 8888         88  `8.`8888.      `8.`8888.    8 8888          8 8888 88 8888           8 8888        8       8 8888          ");
		 System.out.println("   8 8888         88 8888         88   `8.`8888.      `8.`8888.   8 8888          8 8888 88 8888           8 8888        8       8 8888          ");
		 System.out.println("   8 8888         88 8888        ,8P    `8.`8888.      `8.`8888.  8 8888          8 8888 88 8888   8888888 8 8888888888888       8 8888          ");
		 System.out.println("   8 8888         `8 8888       ,8P 8b   `8.`8888. 8b   `8.`8888. 8 8888          8 8888 `8 8888       .8' 8 8888        8       8 8888          ");
		 System.out.println("   8 8888          ` 8888     ,88'  `8b.  ;8.`8888 `8b.  ;8.`8888 8 8888          8 8888    8888     ,88'  8 8888        8       8 8888          ");
		 System.out.println("   8 8888             `8888888P'     `Y8888P ,88P'  `Y8888P ,88P' 8 888888888888  8 8888     `8888888P'    8 8888        8       8 8888          ");
		 System.out.println("=================================================================================================================================================");
		
	}
	
	public static void printFLTInfo() {
		
		System.out.println();
		System.out.println("===================================================================");		
		System.out.println("Fosslight Server URL: " + loginValues.getFLTserverUri());		
		System.out.println("Fosslight Token: " + "*******");
		System.out.println("Fosslight Project ID: " + projectValues.getFLTprojectId());	
		//System.out.println("Fosslight Project Name/Version: " + projectValues.getFLTprojectName() + " / " + projectValues.getFLTprojectVersion());
		System.out.println("Fosslight Not New Project: " + projectValues.getFLTprojectIdCheck());
		System.out.println("===================================================================");
		System.out.println();
	}
	
	public static void endFosslight() {
		
	}

}
