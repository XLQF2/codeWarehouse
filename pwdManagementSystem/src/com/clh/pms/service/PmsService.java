package com.clh.pms.service;

import java.util.Scanner;

public class PmsService {
    private PmsService() {
    }

    public static PmsService getPmsService() {
        return new PmsService();
    }

    //判断密码是否合法
    public boolean judgmentPassword(String str) {
        String pattern = "^[a-zA-Z0-9]{1,16}$";
        return str.matches(pattern);
    }

    //（1）将每个字符的ASCII码加上它在字符串中的位置(1开始)和偏移值3
    public void encryptPassword(StringBuffer pwd) {
        for (int i = 0; i < pwd.length(); i++) {
            pwd.setCharAt(i, (char)(pwd.charAt(i) + (i + 1) + 3));
        }
    }
    public void decryptPassword(StringBuffer pwd) {
        for (int i = 0; i < pwd.length(); i++) {
            pwd.setCharAt(i, (char)(pwd.charAt(i) - (i + 1) - 3));
        }
    }

    //（2）将字符串的第一位和最后一位调换顺序
    public void exchangePassword(StringBuffer pwd) {
        pwd.setCharAt(0, (char) (pwd.charAt(0)^ pwd.charAt(pwd.length()-1)));
        pwd.setCharAt(pwd.length()-1, (char) (pwd.charAt(0)^ pwd.charAt(pwd.length()-1)));
        pwd.setCharAt(0, (char) (pwd.charAt(0)^ pwd.charAt(pwd.length()-1)));
    }

    //（3）将字符串反转
    public void reversePasswords(StringBuffer pwd){
        for (int i = 0, j = pwd.length() - 1; i < j; i++,j--) {
            pwd.setCharAt(i, (char) (pwd.charAt(i)^ pwd.charAt(j)));
            pwd.setCharAt(j, (char) (pwd.charAt(i)^ pwd.charAt(j)));
            pwd.setCharAt(i, (char) (pwd.charAt(i)^ pwd.charAt(j)));
        }
    }

    //按任意键返回主界面
    public void switchPage(){
        new Scanner(System.in).nextLine();
    }
}
