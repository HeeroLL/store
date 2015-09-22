package com.zebone.btp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * spring MVC 注解例子
 * @author 宋俊傑
 *
 */
@Controller
@RequestMapping("demo")
public class ControllerAnnotationDemo {
    
    /**
     * @RequestBody/@ResponseBody 两个注解的例子。
     * 将HttpServletRequest的getInputStream()内容绑定到入参，将处理方法返回值写入到HttpServletResponse的getOutputStream()中。
     * （只能访问报文体，不能访问报文头）
     * @param requestBody
     * @return
     */
    @RequestMapping("requestBodyDemo")
    public @ResponseBody String requestBodyDemo(@RequestBody() String requestBody){
        System.out.println(requestBody);
        return requestBody; 
    }
    
    @RequestMapping(value = "/httpEntityDemo")
    public @ResponseBody  String httpEntityDemo(HttpEntity<String> httpEntity){
        String res = "";
         long contentLen = httpEntity.getHeaders().getContentLength();  
         String ContentType =  httpEntity.getHeaders().getContentType().getType();
         res += "ConentLen:"+contentLen+"\nContentType:"+ContentType+"\nRequest Body:\n"+httpEntity.getBody();
         System.out.println(res);
         return res;
    }
    
    /**
     * 返回json 数据
     * @return
     */
    @RequestMapping(value = "/getUserList")
    
    public @ResponseBody List<User> getUserList(){
        List<User> list = new ArrayList<User>();
        User user = new User();
        user.setAge(30);
        user.setBirthday(new Date());
        user.setName("张三");
        user.setWeight(65.7f);
        
        User user1 = new User();
        user1.setAge(31);
        user1.setBirthday(new Date());
        user1.setName("李四");
        user1.setWeight(65.9f);
        
        list.add(user);
        list.add(user1);
        return list;
    }
    
    public class User{
        private String name;
        private String sex;
        private Integer age;
        private Date birthday;
        private float weight;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getSex() {
            return sex;
        }
        public void setSex(String sex) {
            this.sex = sex;
        }
        public Integer getAge() {
            return age;
        }
        public void setAge(Integer age) {
            this.age = age;
        }
        
        @DateTimeFormat(pattern="yyyy-MM-dd")
        public Date getBirthday() {
            return birthday;
        }
        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }
        public float getWeight() {
            return weight;
        }
        public void setWeight(float weight) {
            this.weight = weight;
        }
        
    }
}
