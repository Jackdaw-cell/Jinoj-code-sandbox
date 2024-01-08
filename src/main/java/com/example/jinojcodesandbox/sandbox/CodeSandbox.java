package com.example.jinojcodesandbox.sandbox;

import com.example.jinojcodesandbox.model.ExecuteCodeRequest;
import com.example.jinojcodesandbox.model.ExecuteCodeResponse;

/**
 * 代码沙箱接口定义
 */
public interface CodeSandbox {

    /**
     * 参数模式执行代码
     *
     * @return
     */
     ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) ;
}
