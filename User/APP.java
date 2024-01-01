package User;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class APP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<User> list = new ArrayList<User>();

        while (true) {
            System.out.println("欢迎来到学生管理系统");
            System.out.println("请选择操作：1.登录 2.注册 3.忘记密码");
            switch(sc.next()){
                case "1" -> login(list);
                case "2" -> register(list);
                case "3" -> forgotPassword(list);
                case "4" -> {
                    System.out.println("谢谢使用再见！");
                    System.exit(0);
                }
                default  -> System.out.println("没有这个选项");
            }
        }
    }
    public static void login(ArrayList<User> list){
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            System.out.println("请输入用户名：");
            String username = sc.next();
            //判断用户名是否存在
            boolean flag = contains(list,username);
            if (!flag){
                System.out.println("用户名"+username+"未注册，请先注册后再登陆：");
                return;
            }
            System.out.println("请输入密码：");
            String password = sc.next();

            while (true) {
                String rightCode = getCode();
                System.out.println("当前正确的验证为；" + rightCode);
                System.out.println("请输入验证码：");
                String code = sc.next();
                if (code.equalsIgnoreCase(rightCode)){
                    System.out.println("验证码正确!");
                    //创建对象调用方法，启动学生管理系统
                    StudentSystem ss = new StudentSystem();
                    ss.startStudentSystem();
                    break;
                }else{
                    System.out.println("验证码错误！");
                    continue;
                }
            }
            //验证用户名和密码是否正确
            //集合中是否包含用户名和密码
            //定义一个方法验证用户名和密码是否正确

            //封装是思想的应用
            //我们可以把零散的数据封装成一个对象
            //以后传递参数的时候，只要传递一个整体就可以了，不需要这些零散的数据
            User userInfo = new User(username,password,null,null);
            boolean result = checkuserInfo(list,userInfo);
            if (result){
                System.out.println("登录成功，可以开始使用学生管理系统了");
                break;
            }else{
                System.out.println("登录失败，用户名或密码错误");
                if (i == 2){
                    System.out.println("当前账号" + username + "已被锁定！请联系XXX-xxxx");
                    //当前账号锁定之后，直接结束方法即可
                    return;
                }else{
                    System.out.println("用户名或密码错误，还剩下" + (2-i) +"次机会！");
                    continue;
                }
            }
        }


    }

    private static boolean checkuserInfo(ArrayList<User> list, User userInfo) {
        //遍历集合，判断用户是否存在，如果存在登录成功，如果不存在登录失败
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            if (user.getUsername().equals(userInfo.getUsername()) && user.getPassword().equals(userInfo.getPassword())){
                return true;
            }
        }
        return false;
    }

    public static void register(ArrayList<User> list){
        Scanner sc = new Scanner(System.in);
        String username;
        while (true) {
            System.out.println("请收入用户名：");
            username = sc.next();
            boolean flag1 = checkUsername(username);
            if (!flag1){
                System.out.println("格式有误，请重新输入");
                continue;
            }
            //判断用户是否唯一
            boolean flag2 = contains(list,username);
            if (flag2){
                //用户名存在，那么当前用户名无法注册，需要重新输入
                System.out.println("用户名" + username + "已存在，请重新输入");
            }else{
                //不存在，表示当前用户名可用，可用继续录用下面其他的数据
                System.out.println("用户名" + username + "可用");
                break;
            }

        }

        //2.键盘录入密码
        //密码键盘输入两次，两次一致才可以进行注册
        String password;
        while (true) {
            System.out.println("请输入要注册的密码：");
             password = sc.next();
            System.out.println("请再次输入要注册的密码：");
            String againPassword = sc.next();
            if (!password.equals(againPassword)){
                System.out.println("两次密码不一致，请重新输入");
                continue;
            }else{
                break;
            }
        }

        //3.键盘录入身份证号码
        String personID;
        while (true) {
            System.out.println("请输入身份证号码：");
            personID = sc.next();
            boolean flag = checkPersonID(personID);
            if (flag){
                System.out.println("身份证号码格式满足要求");
                break;
            }else{
                System.out.println("身份证号码格式不正确，请重新输入");
            }
        }

        //4.键盘录入手机号码
        String phoneNumber;
        while (true) {
            System.out.println("请输入手机号码：");
            phoneNumber = sc.next();
            boolean flag = checkphoneNumber(phoneNumber);
            if (flag){
                System.out.println("手机号格式正确");
                break;
            }else{
                System.out.println("手机格式有误，请重新输入");
                continue;
            }
        }
        User u = new User(username,password,personID,phoneNumber);
        list.add(u);
        System.out.println("注册成功");

        //遍历集合
        printlist(list);

    }

    private static void printlist(ArrayList<User> list) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            System.out.println(user.getUsername() + "," + user.getPassword() + "," +  user.getPersonID() + "," + user.getPhoneNumber());
        }
    }


    private static boolean checkphoneNumber(String phoneNumber) {
        //长度为11
        int len = phoneNumber.length();
        if (len != 11){
            return false;
        }
        //不能以0开头
        if (phoneNumber.startsWith("0")){
            return false;
        }
        //必须都是数字
        for (int i = 0; i < phoneNumber.length(); i++) {
            char c = phoneNumber.charAt(0);
            if (!(c >= '0' && c <= '9')){
                return false;
            }
        }
        //当循环结束后，表示每个字符都在0-9之间
        return true;

    }

    private static boolean checkPersonID(String personID) {
//        长度为18位
        if (personID.length() != 18){
            return false;
        }
//        不能以0开头

        if (personID.startsWith("0")){
            return false;
        }
//        前17位必须是数字
        for (int i = 0; i < personID.length() - 1; i++) {
            char c = personID.charAt(i);
            if (!(c >= '0' && c <= '9')){
                return false;
            }
        }
//        最后一位可以是数字，也可以是大写X或小写x
        char endChar = personID.charAt(personID.length() -1);
        if ((endChar >= '0') && (endChar <= '9') || (endChar == 'X') || (endChar == 'x') ){
            return true;
        }else{
            return false;
        }

    }


    private static boolean contains(ArrayList<User> list, String username) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            String rightUsername = user.getUsername();
            if (rightUsername.equals(username)){
                return true;
            }
        }
        //当循环结束了，表示集合里面的所有用户都比较完了，还没有一样的就返回false
        return false;
    }

    private static boolean checkUsername(String username) {
        //用户名的长度必须在3~15之间
        int len = username.length();
        if (len < 3 || len > 15){
            return false;
        }
        //当代码执行到这里，表示用户名的长度是符合要求的。
        //继续校验：只能是数字加字母的组合
        //循环得到username里面的每一个字符，若果里面有一个字符不是字母或数字就返回false
        for (int i = 0; i < username.length(); i++) {
            //i 索引
            char c = username.charAt(i);
            if (!(c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c >= '0' && c <= '9')){
                return false;
            }
        }
        //当代码执行到这里，表示什么？
        //用户名满足两个要求：1，长度满足 2，内容也满足（字母+数字）
        //但不能是纯数字
        //统计在用户名中有多少个字符就可以了。
        int count = 0;
        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            if ((c >= 'a' && c <= 'z' )||( c >= 'A' && c <= 'Z')){
                count++;
                break;
            }
        }
        return count > 0;









        //只能是字母加数字的组合，但不能是纯数字
    }

    public static void forgotPassword(ArrayList<User> list){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = sc.next();
        boolean flag = contains(list,username);
        if(!flag){
            System.out.println("当前用户" + username + "未注册，请先注册");
            return;
        }

        //键盘录入身份证号码和手机号码
        System.out.println("请输入身份证号码：");
        String personID = sc.next();
        System.out.println("请输入手机号码：");
        String phoneNumber = sc.next();

        //需要把用户对象通过索引先获取出来
        int index = findIdex(list,username);
        User user = list.get(index);
        //比较用户对象中的手机号码和身份证号码是否相同
        if (!(user.getPersonID().equalsIgnoreCase(personID) && user.getPhoneNumber().equals(phoneNumber))){
            System.out.println("身份证号码或手机号码输入有误，不能修改密码");
            return;
        }

        //当代码执行到这里，表示所有的数据全部验证成功，直接修改即可
        String password;
        while (true) {
            System.out.println("请输入新的密码：");
             password = sc.next();
            System.out.println("请再次输入密码：");
            String againPassword = sc.next();
            if (password.equals(againPassword)){
                System.out.println("两次密码输入一致");
                break;
            }else {
                System.out.println("两次密码不一致请重新输入");
                continue;
            }
        }
        //直接修改即可
        user.setPassword(password);
        System.out.println("密码修改成功");

    }

    private static int findIdex(ArrayList<User> list, String username) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
             if (user.getUsername().equals(username)){
                 return i;
             }
        }
            return -1;
    }

    private static String getCode(){
        //1，创建一个集合添加所有的大写字母和小写字母
        ArrayList<Character> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            list.add((char)('a' + i));
            list.add((char)('A' + i));

        }
        //2.要随机抽取4个字符
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            //获取随机索引
            int index = r.nextInt(list.size());
            //利用随机索引获取字符
            Character c = list.get(index);
            //把随机字符添加到sb当中
            sb.append(c);
        }
        //3.把一个随机数字添加到末尾
        int number = r.nextInt(10);
        sb.append(number);
        //4.如果我们要修改字符串中的内容
        //先把字符串变成字符数组，在数组中修改，然后再创建一个新的字符串
        char[] arr = sb.toString().toCharArray();
        //拿着最后一个索引和随机缩索引进行交换
        int randomIndex = r.nextInt(arr.length);
        //最大索引指向的元素，和随机元素指向的索引进行交换
        char temp = arr[randomIndex];
        arr[randomIndex] = arr[arr.length  - 1];
        arr[arr.length - 1] = temp;
        return new String(arr);

    }



}

