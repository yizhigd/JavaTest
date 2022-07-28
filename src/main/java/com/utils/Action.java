package com.utils;

import org.openqa.selenium.By;

public class Action {

    /**
     * 封装点击
     */
    public static void click(By by){
        WebElementUtils.findElement(by).click();
    }

    /**
     * 封装 向输入框中 输入内容
     */
    public static void sendKeys(By by, String text){
        WebElementUtils.findElement(by).sendKeys(text);
    }

    /**
     * 封装 得到Text
     */
    public static String getText(By by){
        String text=WebElementUtils.findElement(by).getText();
        return text;
    }

}
