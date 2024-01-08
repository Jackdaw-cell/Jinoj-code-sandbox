package com.example.jinojcodesandbox.sandbox.native_;

import com.example.jinojcodesandbox.model.ExecuteCodeRequest;
import com.example.jinojcodesandbox.model.ExecuteCodeResponse;
import com.example.jinojcodesandbox.sandbox.JavaCodeSandboxTemplate;
import org.springframework.stereotype.Component;

import java.io.File;

/***
 * 远程代码沙箱
 * 原生实现（基于模板方法）
 */
@Component
public class JavaNativeCodeSandbox extends JavaCodeSandboxTemplate {

    //父类方法增强
    @Override
    public File saveCodeToFile(String code) {
        File file = super.saveCodeToFile(code);
        System.out.println("增强方法");
        return file;
    }

    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        return super.executeCode(executeCodeRequest);
    }

}
