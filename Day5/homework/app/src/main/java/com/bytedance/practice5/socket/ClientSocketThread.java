package com.bytedance.practice5.socket;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.Socket;

public class ClientSocketThread extends Thread {
    public ClientSocketThread(SocketActivity.SocketCallback callback) {
        this.callback = callback;
    }

    private SocketActivity.SocketCallback callback;

    //head请求内容
    private static String content = "HEAD / HTTP/1.1\r\nHost:www.zju.edu.cn\r\n\r\n";

    @Override
    public void run() {
        try {
            Log.d("my socket", "begin");
            Socket socket = new Socket("www.zju.edu.cn", 80);
            Log.d("my socket", "new success");

            BufferedOutputStream os = new BufferedOutputStream(socket.getOutputStream());
            BufferedInputStream is = new BufferedInputStream(socket.getInputStream());

            os.write(content.getBytes());
            os.flush();
            Log.d("my socket", "客户端发送 " + content);

            byte[] data = new byte[1024 * 5];

            while (socket.isConnected())
            {
                int reciveLen = is.read(data);
                if (reciveLen!=-1){
                    String receive = new String(data, 0, reciveLen);
                    Log.d("my socket", "客户端收到 " + receive);
                    callback.onResponse(receive);
                    break;
                }else {
                    Log.d("my socket", "客户端收到-1");
                }
            }

            os.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // TODO 6 用socket实现简单的HEAD请求（发送content）
        //  将返回结果用callback.onresponse(result)进行展示
    }
}