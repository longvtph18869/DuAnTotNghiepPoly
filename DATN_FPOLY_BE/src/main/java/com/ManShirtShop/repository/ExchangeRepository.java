package com.ManShirtShop.repository;

import com.ManShirtShop.entities.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ExchangeRepository extends JpaRepository<Exchange, Integer> {
    @Query(value = "SELECT * FROM defaultdb.exchange where exchange.return_id = :idReturn", nativeQuery = true)
    Optional<Integer> findIdById(@Param("idReturn") Integer id);

    @Query(value = "SELECT exchange.code FROM defaultdb.exchange \n" +
            "join defaultdb.returns on exchange.return_id = returns.id\n" +
            "where returns.code = :codeEx", nativeQuery = true)
    Optional<String> getByCodeReturn(@Param("codeEx") String codeEx);
}
