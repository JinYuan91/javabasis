package com.java.basis.io.bio;

import com.java.basis.io.common.ServerInfo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class BioServer {
    public static void main(String[] args) throws Exception {

        ServerSocket ss = new ServerSocket(ServerInfo.PORT);
        System.out.println("Server started on " + ServerInfo.PORT);
        ExecutorService es = Executors.newCachedThreadPool();


        while (true) {
            Socket socket = ss.accept();
            es.submit(new TaskHandler(socket));
        }
    }

    static class TaskHandler implements Runnable {
        private Socket socket;
        private Scanner scanner;
        private BufferedWriter out;

        public TaskHandler(Socket socket) {
            this.socket = socket;
            try {
                scanner = new Scanner(socket.getInputStream());
                out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            boolean flag = true;
            while (flag) {
                System.out.println(Thread.currentThread().getId()+"-Read wait from client -"+System.currentTimeMillis());
                String line = scanner.nextLine();
                System.out.println("Read from client - " + line+"-"+System.currentTimeMillis());
                if (line != null) {
                    String writeMessage = "[Echo] " + line + "\n";
                    if (line.equalsIgnoreCase("bye")) {
                        flag = false;
                        writeMessage = "[Exit] byebye " + "\n";
                    }
                    try {
                        out.write(writeMessage);
                        out.flush();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            scanner.close();
            try {
                out.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
