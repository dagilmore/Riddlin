package com.riddlin.app.domain.riddle;

import java.util.List;

public interface RiddleService {

    List getAllRiddles();

    Riddle create(Riddle riddle);
}