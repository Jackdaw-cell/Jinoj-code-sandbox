package com.example.jinojcodesandbox.utils;

import cn.hutool.core.date.StopWatch;
import cn.hutool.core.util.StrUtil;
import com.example.jinojcodesandbox.model.ExecuteMessage;

import java.io.*;

/**
 * 进程工具类
 */
public class ProcessUtils {

    /***
     * 控制台进程编译
     * @param runProcess 控制台进程
     * @param opName 操作名称
     * @return
     */
    public static ExecuteMessage runProcessAndGetMessage(Process runProcess,String opName){
        ExecuteMessage executeMessage = new ExecuteMessage();
        try{
            //执行计时开始
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            //exitValue会等待process执行完成才返回值，可用于判断执行是否成功
            int exitValue = runProcess.waitFor();
            executeMessage.setExitValue(exitValue);
            //判断错误码
            if (exitValue == 0) {
                System.out.println(opName+"成功");
                //读取执行结果【正常输出】
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
                StringBuilder compileOutputlineBuilder=new StringBuilder();
                String compileOutputline;
                while ((compileOutputline = bufferedReader.readLine())!=null){
                    compileOutputlineBuilder.append(compileOutputline);
                }
                executeMessage.setMessage(compileOutputlineBuilder.toString());
                System.out.println(compileOutputlineBuilder);
            }else {
                System.out.println(opName+"失败:错误码: "+exitValue);
                //读取执行结果【正常输出】
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
//                StringBuilder compileOutputlineBuilder=new StringBuilder();
//                String compileOutputline;
//                while ((compileOutputline = bufferedReader.readLine())!=null){
//                    compileOutputlineBuilder.append(compileOutputline);
//                }
//                System.out.println(compileOutputlineBuilder);
                //读取执行结果【异常输出】
                BufferedReader errorBufferedReader = new BufferedReader(new InputStreamReader(runProcess.getErrorStream()));
                StringBuilder errCompileOutputlineBuilder=new StringBuilder();
                String errCompileOutputline;
                while ((errCompileOutputline = errorBufferedReader.readLine())!=null){
                    errCompileOutputlineBuilder.append(errCompileOutputline);
                }
                executeMessage.setErrorMessage(errCompileOutputlineBuilder.toString());
                System.out.println(errCompileOutputlineBuilder);
            }
            stopWatch.stop();
            executeMessage.setTime(stopWatch.getLastTaskTimeMillis());
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        return executeMessage;
    }

    /***
     * 交互式控制台进程编译（通过scanner获取输入）
     * @param runProcess 控制台进程
     * @param opName 操作名称
     * @return
     */
    public static ExecuteMessage runInterProcessAndGetMessage(Process runProcess,String opName,String args){
        ExecuteMessage executeMessage = new ExecuteMessage();
        try{
            //交互式输入信息（相当于用户在控制台输入信息）
            InputStream inputStream = runProcess.getInputStream();
            OutputStream outputStream = runProcess.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            //写入流，这里要注意不能真的输入空格，要输入空格的转义符\n才能识别
            String[] s = args.split(" ");
            String join = StrUtil.join("\n", s) + "\n";
            outputStreamWriter.write(join);
            outputStreamWriter.flush();
            //读取执行结果【正常输出】
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder compileOutputlineBuilder=new StringBuilder();
            String compileOutputline;
            while ((compileOutputline = bufferedReader.readLine())!=null){
                compileOutputlineBuilder.append(compileOutputline);
            }
            int exitValue = runProcess.waitFor();
            executeMessage.setExitValue(exitValue);
            executeMessage.setMessage(compileOutputlineBuilder.toString());
            executeMessage.setErrorMessage(null);
            //资源回收
            outputStream.close();
            outputStreamWriter.close();
            inputStream.close();
            runProcess.destroy();
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
        return executeMessage;
    }
}
