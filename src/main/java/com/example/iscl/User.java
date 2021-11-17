package com.example.iscl;

public class User {
    String name;
    String password;
    int age;

    public User(String name, String password, int age){
        this.setName(name);
        this.setPassword(password);
        this.setAge(age);
    }
    public String getName(){
        return name;
    }
    public String getPassword(){
        return password;
    }
    public int getAge(){
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
