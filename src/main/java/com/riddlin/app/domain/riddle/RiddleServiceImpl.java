package com.riddlin.app.domain.riddle;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RiddleServiceImpl implements RiddleService {
    static final Logger logger = LoggerFactory.getLogger(RiddleServiceImpl.class);
    private final RiddleRepository riddleRepository;

    public RiddleServiceImpl(RiddleRepository riddleRepository)
    {
        this.riddleRepository = riddleRepository;
    }

    public List getAllRiddles()
    {
        return riddleRepository.findAll();
    }

    public Riddle create(Riddle riddle)
    {
        return (Riddle)riddleRepository.save(riddle);
    }

}