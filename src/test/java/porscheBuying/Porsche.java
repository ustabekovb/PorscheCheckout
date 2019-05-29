package porscheBuying;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Porsche {
	
	static WebDriver driver;
	
	@Before
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.navigate().to("https://www.porsche.com/usa/modelstart/”");
	
	}
	 
	@Test
	public void TestPorsche() {
		WebElement model718 = driver.findElement(By.xpath("//a[@href='/usa/modelstart/all/?modelrange=718']"));
		model718.click();
		
		String price718 = driver.findElement(By.xpath("//div[@class='m-14-model-name']//following::div[@class='m-14-model-price']")).getText();
		price718 = price718.substring(7, 9)+price718.substring(10, 13);
		int p718 = Integer.parseInt(price718);
		System.out.println(price718);
		driver.get("https://cc.porsche.com/icc_pcna/ccCall.do?rt=1558201355&screen=1280x800&userID=USM&lang=us&PARAM=parameter_internet_us&ORDERTYPE=982120&CNR=C02&MODELYEAR=2019&hookURL=https%3a%2f%2fwww.porsche.com%2fusa%2fmodelstart%2fall%2f");
		WebElement priceButton = driver.findElement(By.xpath("//*[@id=\'s_iccCca\']/div[1]/div[4]/div[1]/span"));
		priceButton.click();
		String priceBase = driver.findElement(By.xpath("//div[@class='row highlighted priceTotal separator']//following::div[@class='row additional']/div[2]")).getText();
		priceBase = priceBase.substring(1, 3)+priceBase.substring(4);
		int pBase = Integer.parseInt(priceBase);
		System.out.println(priceBase);
		Assert.assertEquals("base price is not verified",price718, priceBase);
		
		String priceEquipment = driver.findElement(By.xpath("//div[@class='row highlighted priceTotal separator']//following::div[@class='row']/div[2]")).getText();
		priceEquipment = priceEquipment.substring(1).replace(",", "");
		int pEquipment = Integer.parseInt(priceEquipment);
		System.out.println(priceEquipment);
		Assert.assertEquals("price equipment is not 0", "0", priceEquipment);
		String priceTotal = driver.findElement(By.xpath("//div[@class='row highlighted priceTotal separator']//div[@class='cca-price']")).getText();
		priceTotal = priceTotal.substring(1,3)+priceTotal.substring(4);
		int pTotal = Integer.parseInt(priceTotal);
		System.out.println(priceTotal);
		String priceDelivery = driver.findElement(By.xpath("//div[@class='row highlighted priceTotal separator']//following::div[@class='row additional'][2]//div[@class='cca-price']")).getText();
		priceDelivery = priceDelivery.substring(1,2)+priceDelivery.substring(3);
		int pDelivery = Integer.parseInt(priceDelivery);
		System.out.println(priceDelivery);
		Assert.assertEquals("Total price is not equal to Base and Delivery price",pTotal, pBase+pDelivery);
	//9  MIAMI BLUE
		WebDriverWait wait = new WebDriverWait(driver, 50);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)", "");
		WebElement mBlue = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@style='background-color: rgb(0, 120, 138);']")));
		mBlue.click();
	//10 Miami blue price equal to Equipment price	
		String mBluePriceEquipment = driver.findElement(By.xpath("//section[@id='s_iccCca']//following::div[@class='row']//div[@class='cca-price']")).getText();
		mBluePriceEquipment = mBluePriceEquipment.substring(1).replace(",", "");
		int mBluePEquipment = Integer.parseInt(mBluePriceEquipment);
		System.out.println(mBluePEquipment);
		String mBluePrice = driver.findElement(By.xpath("//div[@id='IAF_wrapper']//following-sibling::div[@class='content recommendContainer hideRecommendationFootnote']//div[@class='tt_price tt_cell']")).getText();
		mBluePrice = mBluePrice.substring(1).replace(",", "");
		int mBlueP = Integer.parseInt(mBluePrice);
		System.out.println(mBlueP);
		Assert.assertEquals("Miami blue price is not equal to Equipment price", mBluePEquipment, mBlueP);
	//11 Total price equal to base, delivery, equipment prices	
		String mBluePriceTotal = driver.findElement(By.xpath("//section[@id='s_iccCca']//div[@class='row highlighted priceTotal separator']//div[@class='cca-price']")).getText();
		mBluePriceTotal = mBluePriceTotal.substring(1).replace(",", "");
		int mBluePTotal = Integer.parseInt(mBluePriceTotal);
		System.out.println(mBluePTotal);
		Assert.assertEquals("Total price is not equal to base+delivery+equipment prices for Miami Blue",mBluePTotal, pBase+pDelivery+mBluePEquipment);
	//12 CARRERA SPORT WHEELS
		js.executeScript("window.scrollBy(0,250)", "");
		WebElement sWheel = driver.findElement(By.xpath("//li[@id='s_exterieur_x_MXRD']"));
		sWheel.click();
	//13 Equipment price equal to Miami blue and Sport Wheel prices	
		String sWheelPrice = driver.findElement(By.xpath("//div[@id='s_exterieur_x_IRA']//div[@class='content']//div[@class='tt_price tt_cell']")).getText();
		sWheelPrice = sWheelPrice.substring(1).replace(",", "");
		int sWheelP = Integer.parseInt(sWheelPrice);
		String sWheelPriceEquipment = driver.findElement(By.xpath("//section[@id='s_iccCca']//div[@class='row']//div[@class='cca-price']")).getText();
		sWheelPriceEquipment = sWheelPriceEquipment.substring(1).replace(",", "");
		int sWheelPEquipment = Integer.parseInt(sWheelPriceEquipment);
		System.out.println(sWheelPEquipment);
		Assert.assertEquals("Equipment price is not equal to Wheel and Miami blue prices",sWheelPEquipment, mBlueP+sWheelP);
	//14 Total price equal to base, delivery, equipment prices	
		String sWheelPriceTotal = driver.findElement(By.xpath("//section[@id='s_iccCca']//div[@class='row highlighted priceTotal separator']//div[2]")).getText();
		sWheelPriceTotal = sWheelPriceTotal.substring(1).replace(",", "");
		int sWheelpTotal = Integer.parseInt(sWheelPriceTotal);
		System.out.println(sWheelpTotal);
		Assert.assertEquals("Total price is not equal to base+delivery+equipment prices for Sport Wheel",sWheelpTotal, sWheelPEquipment+pBase+pDelivery);
	//15 POWER SPORT SEATS (14-WAY)
		WebElement sSport=driver.findElement(By.xpath("//div[@id='seatGroup_73']//div[@data-camera-command='set_SeatsMemory']"));
		sSport.click();
		
	}
	
	
	
	@After
	public void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}
	
	

}
