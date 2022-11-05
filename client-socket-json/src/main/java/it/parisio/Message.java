package it.parisio;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {
    String content;

    public Message(
        @JsonProperty("content") String content) 
        {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message [content=" + content + "]";
    }

}
