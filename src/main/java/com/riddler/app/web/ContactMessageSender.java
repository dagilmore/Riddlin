package com.riddler.app.web;

import com.riddler.app.web.blog.ContactForm;

public interface ContactMessageSender {
    void send(ContactForm contact);
}
