package com.ruoyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 启动程序
 * 
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableCaching
public class RuoYiApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(RuoYiApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  互联网+智慧工会云平台启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                "mm    mm                        mmmm    mm                           \n" +
                " ##    ##                      m#\"\"\"\"#   ##                           \n" +
                " ##    ##  ##    ##   m#####m  ##m       ##m####m   m#####m  ##m####m \n" +
                " ########  ##    ##   \" mmm##   \"####m   ##\"   ##   \" mmm##  ##\"   ## \n" +
                " ##    ##  ##    ##  m##\"\"\"##       \"##  ##    ##  m##\"\"\"##  ##    ## \n" +
                " ##    ##  ##mmm###  ##mmm###  #mmmmm#\"  ##    ##  ##mmm###  ##    ## \n" +
                " \"\"    \"\"   \"\"\"\" \"\"   \"\"\"\" \"\"   \"\"\"\"\"    \"\"    \"\"   \"\"\"\" \"\"  \"\"    \"\" \n" +
                "                                                                      ");
    }
}
