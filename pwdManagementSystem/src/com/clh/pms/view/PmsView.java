package com.clh.pms.view;

import com.clh.pms.common.IPasswordStrength;
import com.clh.pms.service.PmsService;
import com.clh.pms.utils.Utility;

public class PmsView {
    PmsService pmsService = PmsService.getPmsService();

    private PmsView() {
    }

    public static void getPmsView() {
        new PmsView().mainMenu();
    }

    //标题
    private void titleView() {
        System.out.println("=================================");
        System.out.println("\t\t欢迎使用密码管理系统");
        System.out.println("=================================");
    }

    //输入密码
    private String enterPasswordView(char choice) {
        String pwdTemp;
        //判断密码是否符合要求
        while (true) {
            titleView();
            if (choice == '1') {
                System.out.print("请输入要加密的密码：");
            } else if (choice == '2') {
                System.out.print("请输入要解密的密码：");
            } else if (choice == '3') {
                System.out.print("请输入要判断的密码：");
            }
            pwdTemp = Utility.readString(16);//读取一个16字符以内的密码
            if (!pmsService.judgmentPassword(pwdTemp)) {
                System.out.println("密码中的字符只能为数字或字母大小写：");
                Utility.switchContinue();
                continue;
            }

            break;
        }
        return pwdTemp;
    }

    //加密
    private void encryptOrDecryptView(char choice) {
        String pwdTemp = enterPasswordView(choice);
        StringBuffer pwd = new StringBuffer(pwdTemp);
        if (choice == '1') {
            pmsService.encryptPassword(pwd);
            pmsService.exchangePassword(pwd);
            pmsService.reversePasswords(pwd);
            System.out.println("加密后的结果是：" + pwd);
            System.out.print("按回车键返回");
            Utility.switchPage();
        } else if (choice == '2') {
            pmsService.reversePasswords(pwd);
            pmsService.exchangePassword(pwd);
            pmsService.decryptPassword(pwd);
            System.out.println("解密后的结果是：" + pwd);
            Utility.switchPage();
        }
    }

    //判断密码强度
    private void judgeIntensityView(char choice) {
        String pwdTemp = enterPasswordView(choice);//判断密码是否合法
        String result = pmsService.judgeIntensity(pwdTemp);//判断密码强度
        switch (result) {
            case IPasswordStrength.WEAK_INTENSITY:
                System.out.println("密码强度弱");
                Utility.switchPage();
                break;
            case IPasswordStrength.MEDIUM_INTENSITY:
                System.out.println("密码强度中");
                Utility.switchPage();
                break;
            case IPasswordStrength.STRENGTH_INTENSITY:
                System.out.println("密码强度强");
                Utility.switchPage();
        }
    }

    //随机生成强密码
    private void randomPasswordView() {
        int len;
        String password;
        while (true) {
            titleView();
            System.out.print("请输入生成密码的长度:");
            len = Utility.readInt(16);
            password = pmsService.randomPassword(len);
            if (len < 8) {
                System.out.println("强密码必须8位及以上");
                Utility.switchContinue();
                continue;
            }
            break;
        }
        System.out.println("随机生成的强密码：" + password);
        Utility.switchPage();
    }

    //主界面
    private void mainMenu() {
        boolean loop = true;
        while (loop) {
            titleView();
            System.out.println("\t\t\t请选择操作");
            System.out.println("\t\t1. 加密");
            System.out.println("\t\t2. 解密");
            System.out.println("\t\t3. 判断密码强度");
            System.out.println("\t\t4. 密码生成");
            System.out.println("\t\t5. 退出");
            System.out.print("请输入选项序号：");
            char choice = Utility.readMenuSelection();
            switch (choice) {
                case '1':
                case '2':
                    encryptOrDecryptView(choice);
                    break;
                case '3':
                    judgeIntensityView(choice);
                    break;
                case '4':
                    randomPasswordView();
                    break;
                case '5':
                    loop = false;
                    break;
            }
        }
    }
}
