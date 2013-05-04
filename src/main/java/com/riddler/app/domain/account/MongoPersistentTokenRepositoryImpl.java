package com.riddler.app.domain.account;

import java.util.Date;
import java.util.List;

import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * Implementation for {@link PersistentTokenRepository}. Store {@link RememberMeToken} to MongoDB.
 */
public class MongoPersistentTokenRepositoryImpl implements PersistentTokenRepository {

    private final RememberMeTokenRepository rememberMeTokenRepository;
    
    public MongoPersistentTokenRepositoryImpl(RememberMeTokenRepository rememberMeTokenRepository){
        this.rememberMeTokenRepository = rememberMeTokenRepository;
    }
    
    /* (non-Javadoc)
     * @see org.springframework.security.web.authentication.rememberme.PersistentTokenRepository#createNewToken(org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken)
     */
    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        RememberMeToken newToken = new RememberMeToken(token);
        this.rememberMeTokenRepository.save(newToken);
    }

    /* (non-Javadoc)
     * @see org.springframework.security.web.authentication.rememberme.PersistentTokenRepository#updateToken(java.lang.String, java.lang.String, java.util.Date)
     */
    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        RememberMeToken token = this.rememberMeTokenRepository.findBySeries(series);
        if (token != null){
            token.setTokenValue(tokenValue);
            token.setDate(lastUsed);
            this.rememberMeTokenRepository.save(token);
        }

    }

    /* (non-Javadoc)
     * @see org.springframework.security.web.authentication.rememberme.PersistentTokenRepository#getTokenForSeries(java.lang.String)
     */
    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        RememberMeToken token = this.rememberMeTokenRepository.findBySeries(seriesId);
        if (token == null){
            return null;
        }
        return new PersistentRememberMeToken(token.getUsername(), token.getSeries(), token.getTokenValue(), token.getDate());
    }

    /* (non-Javadoc)
     * @see org.springframework.security.web.authentication.rememberme.PersistentTokenRepository#removeUserTokens(java.lang.String)
     */
    @Override
    public void removeUserTokens(String username) {
        List<RememberMeToken> tokens = this.rememberMeTokenRepository.findByUsername(username);
        this.rememberMeTokenRepository.delete(tokens);
    }

}
