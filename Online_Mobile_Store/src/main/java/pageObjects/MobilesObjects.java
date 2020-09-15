package pageObjects;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.csvreader.CsvWriter;

import dataProvider.Excel_DataProvider;

public class MobilesObjects 

{
	public WebDriver driver;

	public MobilesObjects(WebDriver driver)
	{
		driver=this.driver;
	}

	@FindBy(xpath="//div[@class='O8ZS_U']/input") WebElement searchbox ;
	@FindBy(xpath="//select[@class='fPjUPw']/option[@value='Min']") WebElement minrange;
	@FindBy(xpath="//div[@class='_1YoBfV']//select[@class='fPjUPw']/option[@value='10000']")  WebElement price ;
	@FindBy(xpath="//div[@class='bhgxx2 col-12-12']/div/section[4]/div[2]/div//div[4]") WebElement selectram ;
	@FindBy(xpath="//div[@class='_1YuAuf']/section[4]") static List<WebElement> ramlist;
	@FindBy(xpath ="//div[@class='_1YuAuf']/section[16]") WebElement selectbrand;
	@FindBy(xpath="//div[@class='bhgxx2 col-12-12']") static List<WebElement> getlist;
	@FindBy(xpath="//div[@class='_1YuAuf']/section[16]/div[2]/div") static List<WebElement> brandlist;

	//div[@class='_1YuAuf']/section[4]/div[2]/div
	public void clickOnSearchBox() {
		searchbox.click();
	}
	public void searchMobile() throws IOException {

		searchbox.sendKeys(Excel_DataProvider.mobilename);
	}

	public void pressKeyDown()
	{
		searchbox.sendKeys(Keys.ARROW_DOWN);
		searchbox.sendKeys(Keys.ENTER);
	}
	public WebElement selectMinRange(String minimumrange) throws InterruptedException {
		Thread.sleep(2000);
		return minrange;
	}

	public WebElement selectPrice(String mobileprice) throws InterruptedException
	{
		price.click();
		return price;
	}

	public WebElement selectRam( ) throws Exception {
		selectram.click();
		return selectram;
	}
	

public void getRamlist(){
	int count= ramlist.size();
	for(int i=0;i<count;i++) {

		String getbrandlist=ramlist.get(i).getText();

		if(getbrandlist.contains(Excel_DataProvider.ramsize)) {
			ramlist.get(i).click();
		}
	}

}

	public WebElement selectProcessorBrand()
	{
		selectbrand.click();
		return selectbrand;

	}
	public void getBrand(){
		int count= brandlist.size();
		for(int i=0;i<count;i++) {

			String getbrandlist=brandlist.get(i).getText();

			if(getbrandlist.contains("Snapdragon")) {
				brandlist.get(i).click();
			}
		}

	}
	static String getList = null;
	public  String getMobileList() {
		int count=getlist.size();

		for(int i=0;i<count-2;i++) {

			getList = getlist.get(i).getText();
		}
		return getList;

	}

	public static void writeDataToCSVFile() {
		// define output file name
		String csvOutputFile = "writetest.csv";

		// check if file exist
		boolean isFileExist = new File(csvOutputFile).exists();

		try {

			// create a FileWriter constructor to open a file in appending mode
			CsvWriter testcases = new CsvWriter(new FileWriter(csvOutputFile, true), ',');

			// write header column if the file did not already exist
			if (!isFileExist) {
				testcases.write(getList);

				// end the record
				testcases.endRecord();
			}

			// end the record
			testcases.endRecord();

			// close the file
			testcases.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}




}
