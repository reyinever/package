package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.tester")  //托管的包
public class Application {
    //程序的入口
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
}
