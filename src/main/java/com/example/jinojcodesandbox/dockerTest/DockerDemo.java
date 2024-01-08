package com.example.jinojcodesandbox.dockerTest;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.*;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.api.model.PullResponseItem;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.command.LogContainerResultCallback;

public class DockerDemo {
    public static void main(String[] args) throws InterruptedException {
        creteContainer();
    }

    /***
     * 执行命令
     */
    public static void exec(){
        DockerClient dockerClient = DockerClientBuilder.getInstance().build();
        PingCmd pingCmd = dockerClient.pingCmd();
        pingCmd.exec();
    }
    /***
     * 拉取镜像方法
     * @throws InterruptedException
     */
    public static void pullImages() throws InterruptedException {
        DockerClient dockerClient = DockerClientBuilder.getInstance().build();
        String image = "nginx:latest";
        PullImageCmd pullImageCmd = dockerClient.pullImageCmd(image);
        PullImageResultCallback pullImageResultCallback = new PullImageResultCallback(){
            @Override
            public void onNext(PullResponseItem item) {
                System.out.println("下载镜像"+item.getStatus());
                super.onNext(item);
            }
        };
        pullImageCmd.exec(pullImageResultCallback).awaitCompletion();
        System.out.println("下载完成");
    }

    /***
     * 基于nginx镜像创建容器 + 启动容器
     * @throws InterruptedException
     */
    public static void creteContainer() throws InterruptedException {
        //获取Docker Client
        DockerClient dockerClient = DockerClientBuilder.getInstance().build();
        //拉取镜像
        String image = "openjdk:8-alpine";
        PullImageCmd pullImageCmd = dockerClient.pullImageCmd(image);
        PullImageResultCallback pullImageResultCallback = new PullImageResultCallback(){
            @Override
            public void onNext(PullResponseItem item) {
                System.out.println("下载镜像"+item.getStatus());
                super.onNext(item);
            }
        };
        pullImageCmd.exec(pullImageResultCallback).awaitCompletion();
        System.out.println("下载完成");
        //创建容器
        CreateContainerCmd containCmd = dockerClient.createContainerCmd(image);
        CreateContainerResponse exec = containCmd.withCmd("echo", "hello docker").exec();
        System.out.println(exec);
        //查看容器
        String containId = exec.getId();
//        ListContainersCmd listContainersCmd = dockerClient.listContainersCmd();
//        List<Container> containerList = listContainersCmd.withShowAll(true).exec();
//        for (Container container:
//             containerList) {
//            System.out.println("容器:"+container);
//        }

        //启动容器
        dockerClient.startContainerCmd(containId).exec();

        //日志查看
        LogContainerResultCallback logContainerResultCallback = new LogContainerResultCallback(){
            @Override
            public void onNext(Frame item){
                System.out.println(item.getStreamType());
                System.out.println("日志: " + new String(item.getPayload()));
                super.onNext(item);
            }
        };
        try {
            //获取所有日志返回
            //awaitCompletion等待所有结果返回才继续执行
            dockerClient.logContainerCmd(containId)
                    .withStdErr(true)
                    .withStdOut(true)
                    .exec(logContainerResultCallback)
                    .awaitCompletion();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //删除容器
        //withForce 表示携带参数 -f
        dockerClient.removeContainerCmd(containId).withForce(true).exec();

        //删除镜像
        //withForce 表示携带参数 -f
        dockerClient.removeImageCmd(image).withForce(true).exec();

    }
}