package com.riddlin.app.domain.riddle.americanIdiom;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AmericanIdiomServiceImpl implements AmericanIdiomService {
    static final Logger logger = LoggerFactory.getLogger(AmericanIdiomServiceImpl.class);
    private final AmericanIdiomRepository americanIdiomRepository;

    public AmericanIdiomServiceImpl(AmericanIdiomRepository americanIdiomRepository) {
        this.americanIdiomRepository = americanIdiomRepository;
    }

    public List getAllAmericanIdioms() {
        return americanIdiomRepository.findAll();
    }

    public AmericanIdiom findAmericanIdiomById(Long americanIdiomId) {
        return americanIdiomRepository.findAmericanIdiomById(americanIdiomId);
    }

    public AmericanIdiom create(AmericanIdiom americanIdiom) {
        return (AmericanIdiom)americanIdiomRepository.save(americanIdiom);
    }

}