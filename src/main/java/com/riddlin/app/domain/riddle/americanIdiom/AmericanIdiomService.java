package com.riddlin.app.domain.riddle.americanIdiom;

import java.util.List;

public interface AmericanIdiomService {

    List getAllAmericanIdioms();

    AmericanIdiom findAmericanIdiomById(Long americanIdiomId);

    AmericanIdiom create(AmericanIdiom americanIdiom);
}