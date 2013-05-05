package com.riddlin.app.web;

import com.riddlin.app.domain.post.CommentPost;

public interface CommentNotificationSender {
    void send(CommentPost comment);
}
