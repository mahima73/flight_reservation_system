import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {


    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        System.out.println("Hello world!");
        Student s = new Student();
        if(s.getClass().isAnnotationPresent(VeryImportant.class)){
            System.out.println("very important annotation");
//            VeryImportant anno = s.getClass().get
        }

        for(Method m: s.getClass().getDeclaredMethods()){
            if(m.isAnnotationPresent(RunImmediately.class)){
                RunImmediately anno = m.getAnnotation(RunImmediately.class);
                for(int i = 0; i < anno.times(); i++){
                    m.invoke(s,"asha");
                }
            }
        }

        Field[]  allFields = s.getClass().getDeclaredFields();
        for(Field f: allFields){
            if(f.getName().equals("age")){
                f.setAccessible(true);
                f.set(s,12);
            }
        }

        System.out.println(s.getAge());
    }
}