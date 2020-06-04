package per.xgt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("per.xgt.dao")
public class SRSMSystemApplication {

    public static void main(String[] args) {

        SpringApplication.run(SRSMSystemApplication.class, args);

    }

}
