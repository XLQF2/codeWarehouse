package com.clh.pms.service;

import com.clh.pms.common.IPasswordStrength;

import java.util.*;

public class PmsService {
    private PmsService() {
    }

    public static PmsService getPmsService() {
        return new PmsService();
    }

    //判断密码是否合法
    public boolean judgmentPassword(String pwd) {
        String pattern = "^[a-zA-Z0-9]{1,16}$";
        return pwd.matches(pattern);
    }

    //加密，将每个字符的ASCII码加上它在字符串中的位置(1开始)和偏移值3
    public void encryptPassword(StringBuffer pwd) {
        for (int i = 0; i < pwd.length(); i++) {
            pwd.setCharAt(i, (char) (pwd.charAt(i) + (i + 1) + 3));
        }
    }

    //解码，将每个字符的ASCII码减去它在字符串中的位置(1开始)和偏移值3
    public void decryptPassword(StringBuffer pwd) {
        for (int i = 0; i < pwd.length(); i++) {
            pwd.setCharAt(i, (char) (pwd.charAt(i) - (i + 1) - 3));
        }
    }

    //将字符串的第一位和最后一位调换顺序
    public void exchangePassword(StringBuffer pwd) {
        pwd.setCharAt(0, (char) (pwd.charAt(0) ^ pwd.charAt(pwd.length() - 1)));
        pwd.setCharAt(pwd.length() - 1, (char) (pwd.charAt(0) ^ pwd.charAt(pwd.length() - 1)));
        pwd.setCharAt(0, (char) (pwd.charAt(0) ^ pwd.charAt(pwd.length() - 1)));
    }

    //将字符串反转
    public void reversePasswords(StringBuffer pwd) {
        for (int i = 0, j = pwd.length() - 1; i < j; i++, j--) {
            pwd.setCharAt(i, (char) (pwd.charAt(i) ^ pwd.charAt(j)));
            pwd.setCharAt(j, (char) (pwd.charAt(i) ^ pwd.charAt(j)));
            pwd.setCharAt(i, (char) (pwd.charAt(i) ^ pwd.charAt(j)));
        }
    }

    //判断密码强度
    public String judgeIntensity(String pwd) {
        //密码的长度在[1,16]
        int length = pwd.length();//获取密码的长度
        if (length < 8) {
            return IPasswordStrength.WEAK_INTENSITY;
        }
        //目前密码的长度在[8,16]
        //matches默认全局匹配
        if (pwd.matches("[0-9]{1,16}|[a-zA-Z]{1,16}")) {//从头到尾只有数字或字母
            return IPasswordStrength.WEAK_INTENSITY;
        } else if (pwd.matches("\\w*[a-z]+\\w*[A-Z]+\\w*|\\w*[A-Z]+\\w*[a-z]+\\w*")) {
            return IPasswordStrength.STRENGTH_INTENSITY;
        } else {
            return IPasswordStrength.MEDIUM_INTENSITY;
        }
    }

    //随机生成密码
    public String randomPassword(int len) {
        Random random = new Random();
        int numCount = random.nextInt(len - 2) + 1;
        int lowerCount = random.nextInt(len - numCount - 1) + 1;
        int upperCount = len - numCount - lowerCount;
        List<Character> password = new ArrayList<>();
        for (int i = 0; i < numCount; i++) {
            password.add(generateRandomChar('0', '9', random));
        }
        for (int i = 0; i < lowerCount; i++) {
            password.add(generateRandomChar('a', 'z', random));
        }
        for (int i = 0; i < upperCount; i++) {
            password.add(generateRandomChar('A', 'Z', random));
        }
        Collections.shuffle(password);
        StringBuilder passwordBuilder = new StringBuilder();
        for (char character : password) {
            passwordBuilder.append(character);
        }
        return passwordBuilder.toString();
    }

    //获取随机数
    public char generateRandomChar(char start, char end, Random random) {
        int range = end - start + 1;
        return (char) (start + random.nextInt(range));
    }


}
