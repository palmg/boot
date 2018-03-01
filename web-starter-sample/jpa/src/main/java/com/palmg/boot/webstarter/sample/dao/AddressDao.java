package com.palmg.boot.webstarter.sample.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.palmg.boot.webstarter.sample.entity.Address;

public interface AddressDao extends JpaRepository<Address, Long> {
}
