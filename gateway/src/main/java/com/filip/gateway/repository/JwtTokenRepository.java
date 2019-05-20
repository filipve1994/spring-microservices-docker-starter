package com.filip.gateway.repository;

import com.filip.gateway.bean.auth.JwtToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JwtTokenRepository extends MongoRepository<JwtToken,String> {
}