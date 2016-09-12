package com.lin.studytest.compiler.example;

import com.lin.studytest.compiler.InMemoryJavaCompiler;

/**
 * @author <a href="mailto:wanqi.lwq@alibaba-inc.com">wanqi.lwq</a>
 * @version 1.0.0
 * @description 主调用
 * @since 2016/9/1
 */
public class MainCaller {

    Person person;

    public void call()throws Exception{
        System.out.println("有个人走了过来：");
        person.dosomething();
        System.out.println("他走了。。。");

    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public static void main(String[] args) throws Exception{
        StringBuilder sourceCode = new StringBuilder();
        sourceCode.append("package com.lin.studytest.compiler.example;\n");
        sourceCode.append("public class ITPerson implements Person {\n");
        sourceCode.append("            public void dosomething() {\n");
        sourceCode.append(" System.out.println(\"他在编程！！\");}}");

        Class<?> helloClass = InMemoryJavaCompiler.compile("com.lin.studytest.compiler.example.ITPerson", sourceCode.toString());
        MainCaller caller = new MainCaller();
        caller.setPerson((Person)helloClass.newInstance());
        caller.call();
        System.out.println(System.getProperty("user.dir"));
    }
}
