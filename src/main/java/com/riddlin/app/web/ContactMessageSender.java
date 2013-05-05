package com.riddlin.app.web;

import com.riddlin.app.web.blog.ContactForm;

public interface ContactMessageSender {
    void send(ContactForm contact);
}
