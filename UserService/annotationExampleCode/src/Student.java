@VeryImportant(times = 3)
public class Student {
    public String name;

    private  int age ;

    @RunImmediately(times = 4)
    public void display(String name){
        System.out.println("Hello, my name is " + name);
    }
//    private Student(int age){
//        this.age = age;
//    }
//
//    public Student(){

//    }

    public int getAge(){
        return this.age;
    }

}
