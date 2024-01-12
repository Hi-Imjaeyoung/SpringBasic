package com.encore.basic.controller;

import com.encore.basic.domain.Hello;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//HTTP 통신을 매우 편하게 할 수 있다
@Controller

//@RestController -> 모든 메서드들이 rest api 방식으로 리턴한다. -> csr 프론트가 따로 존재.

// 클래스 차원에서 url 경로를 지정하고 싶다면, @RequestMapping을 클래스 위에 선언하면서 경로지정.
@RequestMapping("hello")
public class HelloController {

    // @responseBody가 없고, return 타입이 String이면 templates밑에 return.html파일 리턴 없으면 에러
    // data만을 return 할때는 @ResponseBody를 붙인다.(CSR, Rest API 방식)
    // URL 지정 가능 (사용자의 요청을 분기처리 가능)
    @GetMapping("string")
                     //url 패턴          // 메서드로 기능 명시
//    @RequestMapping(value = "string",method = RequestMethod.GET)
    @ResponseBody
    public String helloString(){
        return "hello_string";
    }


    @GetMapping("json")
    @ResponseBody
    public Hello helloJson(){
        Hello hello = new Hello();
        hello.setName("재영");
        hello.setEmail("naver");
        hello.setPasssWd("12343");
        System.out.println(hello);
        return hello;
    }

    @GetMapping("screen")
    public String helloScreen(){
        return "screen";
    }

    @GetMapping("screen-model-param")
    // ?name=재영 의 방식으로 호출 : parameter 호출 방식!
    public String helloScreenModelParam(@RequestParam(value = "name")String inputName, Model model){
        // 화면에 데이터를 넘기고 싶을때는 model객체를 사용한다.
        // model의 키 value형식으로 전달한다.
        model.addAttribute("MyData",inputName); // 템플릿 엔진을 통해서 html에서 자바 문법 사용 가능
        return "screen";
    }

    // pathvariable 방식은 url을 통해 자원의 구조를 명확하게 표현할 수 있어 좀더 restful api 디자인에 적합하다
    // id를 표현하기 자연스러워서 그러듯..?
    @GetMapping("screen-model-path/{id}")
    public String helloScreenModelPath(@PathVariable int id, Model model){
        model.addAttribute("MyData",id);
        return "screen";
    }


}
