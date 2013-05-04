package com.riddler.app.web;

import com.riddler.app.domain.post.CommentPost;

public interface CommentNotificationSender {
    void send(CommentPost comment);
}
