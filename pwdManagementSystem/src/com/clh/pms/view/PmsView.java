package com.clh.pms.view;

import com.clh.pms.service.PmsService;
import com.clh.pms.utils.Utility;

public class PmsView {
    PmsService pmsService = PmsService.getPmsService();
    private PmsView(){
    }
    public static void getPmsView(){
        new PmsView().mainMenu();
    }

    //标题
    private void titeView(){
        System.out.println("=================================");
        System.out.println("\t\t欢迎使用密码管理系统");
        System.out.println("=================================");
    }

    //加密
    private void encryptOrDecryptView(char choice){
        String pwdTemp;
        //判断密码是否符合要求
        while (true) {
            titeView();
            System.out.print("请输入要加密的数字密码：");
            pwdTemp = Utility.readString(16);//读取一个16字符以内的密码
            if (!pmsService.judgmentPassword(pwdTemp)) {
                System.out.println("密码中的字符只能为数字或字母大小写：");
                continue;
            }
            break;
        }
        StringBuffer pwd = new StringBuffer(pwdTemp);
        if (choice == '1') {
            pmsService.encryptPassword(pwd);
            pmsService.exchangePassword(pwd);
            pmsService.reversePasswords(pwd);
            System.out.println("加密后的结果是：" + pwd);
            System.out.print("按回车键返回");
            pmsService.switchPage();
        } else if (choice == '2') {
            pmsService.reversePasswords(pwd);
            pmsService.exchangePassword(pwd);
            pmsService.decryptPassword(pwd);
            System.out.println("解密后的结果是：" + pwd);
            System.out.print("按回车键返回");
            pmsService.switchPage();
        }
    }



    //主界面
    private void mainMenu(){
        boolean loop = true;
        while (loop){
            titeView();
            System.out.println("\t\t\t请选择操作");
            System.out.println("\t\t1. 加密");
            System.out.println("\t\t2. 解密");
            System.out.println("\t\t3. 判断密码强度");
            System.out.println("\t\t4. 密码生成");
            System.out.println("\t\t5. 退出");
            System.out.print("请输入选项序号：");
            char choice = Utility.readMenuSelection();
            switch (choice){
                case '1':
                case '2':
                    encryptOrDecryptView(choice);
                    break;
                case '3':
                    break;
                case '4':
                    break;
                case '5':
                    loop = false;
                    break;
            }
        }
    }
}
