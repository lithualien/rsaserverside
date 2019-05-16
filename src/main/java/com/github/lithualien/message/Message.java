package com.github.lithualien.message;

import java.io.Serializable;
import java.security.PublicKey;

public class Message implements Serializable {

    PublicKey publicKey;
    String hash;
    String cryptedMessage;

    public Message(PublicKey publicKey, String cryptedMessage, String hash) {
        this.publicKey = publicKey;
        this.cryptedMessage = cryptedMessage;
        this.hash = hash;
    }

    public void setCryptedMessage(String cryptedMessage) {
        this.cryptedMessage = cryptedMessage;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public String getCryptedMessage() {
        return cryptedMessage;
    }

    public String getHash() {
        return hash;
    }
}
