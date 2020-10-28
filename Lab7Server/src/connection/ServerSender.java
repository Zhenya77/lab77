package connection;


import dragon.Dragon;
import utilities.Serializator;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Hashtable;

public class ServerSender {
    private Serializator serializator = new Serializator();
    private boolean isOpen = true;
    private static DatagramSocket serverSocket = null;
    private byte[] sendData = new byte[100000];
    private InetAddress address = InetAddress.getLocalHost();
    private int port;

    public ServerSender(int port) throws IOException {
        serverSocket = new DatagramSocket(port);
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setPort(String port) {
        this.port = Integer.parseInt(port);
    }

    public void sendCollecton(Hashtable<Integer, Dragon> collection) {
        if (this.isOpen) {
            try {
                sendData = serializator.toSerialize(collection);
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
                serverSocket.send(sendPacket);
            } catch (Exception e) {
                System.out.println("Клиент вышел из программы.");
            }
        }
    }

    public synchronized void send(String text) throws IOException {
        if (this.isOpen) {
            sendData = text.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
            serverSocket.send(sendPacket);

        }
    }

    public int getPort() {
        return port;
    }
}
