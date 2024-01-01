package User;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentSystem {
    public static void startStudentSystem() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> list = new ArrayList<>();
        while (true) {
            System.out.println("++++++++++++++++++++++欢迎进入学生管理系统++++++++++++++++++++++++");
            System.out.println("1.添加学生");
            System.out.println("2.删除学生");
            System.out.println("3.修改学生");
            System.out.println("4.查询学生");
            System.out.println("5.退出");
            System.out.println("请选择你的操作：");

            switch(sc.next()){
                case "1" -> addStudent(list);
                case "2" -> deleteStudent(list);
                case "3" -> reviseStudent(list);
                case "4" -> queryStudent(list);
                case "5" -> {
                    System.exit(0);
                    System.out.println("退出");
                }
                default -> System.out.println("没有这个选项！");
            }
        }
    }
    public static void addStudent(ArrayList<Student> list){
        Scanner sc = new Scanner(System.in);
        Student stu = new Student();
        while (true) {
            System.out.println("请输入学生ID：");
            String id = sc.next();
            int index = getIndex(list, id);
            if (index == -1){
                stu.setId(id);
                break;
            }else {
                System.out.println("学生id已存在，请重新输入");
            }
        }


        System.out.println("请收入学生姓名：");
        String name = sc.next();
        stu.setName(name);

        System.out.println("请输入学生年龄：");
        int age = sc.nextInt();
        stu.setAge(age);

        System.out.println("请输入学生家庭地址：");
        String address = sc.next();
        stu.setAddress(address);

        list.add(stu);
        System.out.println("添加成功");

    }
    public static void deleteStudent(ArrayList<Student> list){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入要删除学生的id：");
            String id = sc.next();
            int index = getIndex(list, id);
            if (index == -1){
                System.out.println("没有这个学生id，请重新输入");
            }else {
                list.remove(index);
                break;
            }
        }
    }
    public static void reviseStudent(ArrayList<Student> list){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要修改学生的id：");
        String id = sc.next();
        int index = getIndex(list, id);
        if (index == -1){
            System.out.println("没有这个学生的id，请重新输入：");
        }else {
            Student stu = list.get(index);

            System.out.println("请输入学生的姓名：");
            String newName = sc.next();
            stu.setName(newName);

            System.out.println("请输入学生的年龄：");
            int newAge = sc.nextInt();
            stu.setName(newName);

            System.out.println("请输入学的的家庭地址：");
            String newAddress = sc.next();
            stu.setAddress(newAddress);

            System.out.println("修改成功");
        }
    }
    public static void queryStudent(ArrayList<Student> list){

        if (list.size() == 0){
            System.out.println("没有学生信息");
            return;
        }
        System.out.println("id\t\t姓名\t年龄\t地址");
        for (int i = 0; i < list.size(); i++) {
            Student s = list.get(i);
            System.out.println(s.getId() + "\t" + s.getName() + "\t" + s.getAge() + "\t" + s.getAddress());
        }


    }
    public static boolean contains(ArrayList<Student> list,String id){
//        for (int i = 0; i < list.size(); i++) {
//            User.User.Student stu = list.get(i);
//            String sid = stu.getId();
//            if (sid.equals(id)){
//                System.out.println("学生ID" + id  + " 重复");
//                return true;
//            }
//        }
//        return false;
      return  getIndex(list,id) >= 0;
    }
    public static int getIndex(ArrayList<Student> list, String id){
        for (int i = 0; i < list.size(); i++) {
            Student stu = list.get(i);
            String sid = stu.getId();
            if (sid.equals(id)){
                return i;
            }
        }
        return -1;
    }

}
