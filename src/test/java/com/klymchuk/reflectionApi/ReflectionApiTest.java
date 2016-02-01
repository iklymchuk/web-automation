package com.klymchuk.reflectionApi;

import com.klymchuk.reflectionApi.util.LocatorsLoader;
import org.openqa.selenium.By;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionApiTest

{
   public static void main (String[] arg) {

       By tmp = LocatorsLoader.get("loginPage.submit");
           System.out.println(tmp.toString());
       }

}
