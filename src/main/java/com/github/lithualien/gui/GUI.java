package com.github.lithualien.gui;

import com.github.lithualien.crypting.Decryption;
import com.github.lithualien.message.Message;
import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class GUI extends JFrame{
    private JPanel mainPanel;
    private JTextField hashMessage = new JTextField();
    private JButton decrypt = new JButton("Decrypt");
    private JLabel output = new JLabel();
    private Decryption decryption;
    private Message message;

    public void setUpGUI() {
        setMainPanel();
        addToMainPanel();
        setTextFields();
        setButtons();
        setLabels();
        receiveMessages();
    }

    private void setMainPanel() {
        add(mainPanel);
        setVisible(true);
        mainPanel.setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1000, 160);
        setResizable(true);
    }

    private void addToMainPanel() {
        mainPanel.add(hashMessage);
        mainPanel.add(decrypt);
        mainPanel.add(output);
    }

    private void setTextFields() {
        hashMessage.setBounds(10, 10, 980, 30);
    }

    private void setButtons() {
        decrypt.setBounds(10, 50, 980, 30);
    }

    private void setLabels() {
        output.setBounds(10, 90, 980, 30);
    }

    private void setActionListenerForDecryptButton() {
        decrypt.addActionListener(e -> {
            if(decryption.decrypt(hashMessage.getText(), message.getCryptedMessage(), message.getPublicKey())) {
                output.setText("Pavyko, žinutė: " + message.getHash());
            }
            else {
                output.setText("Klaida.");
            }
            hashMessage.setText("");
        });
    }

    private void receiveMessages() {
        ServerSocket socket;
        decryption = new Decryption();
        try {
            socket = new ServerSocket(8888);
            while(true) {
                Socket connection = socket.accept();
                ObjectInputStream getMessage = new ObjectInputStream(connection.getInputStream());
                message = (Message) getMessage.readObject();
                hashMessage.setText(message.getHash());
                output.setText("");
                setActionListenerForDecryptButton();
            }
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }



    }
}
