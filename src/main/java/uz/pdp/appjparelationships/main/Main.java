package uz.pdp.appjparelationships.main;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner jn=new Scanner(System.in);
        System.out.println("1-son:");
        int num1= jn.nextInt();
        System.out.println("2-son:");
        int num2= jn.nextInt();
        System.out.println("3-son:");
        int num3= jn.nextInt();
        System.out.println("4-son:");
        int num4= jn.nextInt();
        System.out.println("5-son:");
        int num5= jn.nextInt();

        if (num1>num2&&num1>num3&&num1>num4&&num1>num5){
            System.out.println("1-son eng katta");
        }else if (num2>num1&&num2>num3&&num2>num4&&num2>num5){
            System.out.println("2-son eng katta");
        }else if (num3>num2&&num3>num1&&num3>num4&&num3>num5){
            System.out.println("3-son eng katta");
        } else if (num4>num2&&num4>num3&&num4>num1&&num4>num5){
            System.out.println("4-son eng katta");
        }else if (num5>num2&&num5>num3&&num5>num4&&num5>num1){
            System.out.println("5-son eng katta");
        }

        if (num1<num2&&num1<num3&&num1<num4&&num1<num5){
            System.out.println("1-son eng kichik");
        }else if (num2<num1&&num2<num3&&num2<num4&&num2<num5){
            System.out.println("2-son eng kichik");
        }else if (num3<num2&&num3<num1&&num3<num4&&num3<num5){
            System.out.println("3-son eng kichik");
        } else if (num4<num2&&num4<num3&&num4<num1&&num4<num5){
            System.out.println("4-son eng kichik");
        }else if (num5<num2&&num5<num3&&num5<num4&&num5<num1){
            System.out.println("5-son eng kichik");
        }
    }
}
