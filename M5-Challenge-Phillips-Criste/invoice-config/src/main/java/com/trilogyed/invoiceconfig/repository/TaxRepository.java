package com.trilogyed.invoiceconfig.repository;

import com.trilogyed.invoiceconfig.model.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxRepository extends JpaRepository<Tax, String> {
}
