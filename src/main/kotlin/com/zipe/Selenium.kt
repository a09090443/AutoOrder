package com.zipe

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait


fun main() {
    val driverPath = resource.getString("web.driver.path")
    val pchomeProductionUrl = resource.getString("pchome.production.prefix.url")
    val pchomeCartUrl = resource.getString("pchome.cart.url")
    val username = resource.getString("pchome.username")
    val password = resource.getString("pchome.password")
    // 若瀏覽器安裝位置為預設則webDriver會自動搜尋path設定的位置，也可以使用System.setProperty 來指定路徑
    System.setProperty(
        "webdriver.chrome.driver",
        driverPath
    )
    // Selenium對不同瀏覽器提供了不同的webDriver
    val driver: WebDriver = ChromeDriver() // googleChrome
    // 將指定商品加入購物車
    driver.get("${pchomeProductionUrl}DEBQB3-A900AQUSI")
    WebDriverWait(
        driver,
        5L
    ).until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='ButtonContainer']/button")))
    driver.findElement(By.xpath("//li[@id='ButtonContainer']/button")).click()

    driver[pchomeCartUrl]

    // 登入
    WebDriverWait(driver, 10L).until(ExpectedConditions.presenceOfElementLocated(By.id("loginAcc")))
    println(driver.findElement(By.id("loginAcc")).size)
    driver.findElement(By.id("loginAcc")).sendKeys(username)
    driver.findElement(By.id("loginPwd")).sendKeys(password)
    WebDriverWait(driver, 2L).until(ExpectedConditions.presenceOfElementLocated(By.id("btnLogin")))
    driver.findElement(By.id("btnLogin")).click()

    WebDriverWait(
        driver,
        10L
    ).until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='CC']/a[@class='ui-btn']")))
    println("登入成功")
    //超商取貨
//    driver.findElement(By.id("radio_24hMarket")).click()

    val js = driver as JavascriptExecutor
    js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//li[@class='CC']/a[@class='ui-btn']")))

    WebDriverWait(
        driver,
        5L
    ).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='btnSubmit']")))

    // 信用卡背面三碼
    driver.findElement(By.id("multi_CVV2Num")).sendKeys("922")
    // 送出訂單
//    driver.findElement(By.xpath("//a[@id='btnSubmit']")).click()

}
