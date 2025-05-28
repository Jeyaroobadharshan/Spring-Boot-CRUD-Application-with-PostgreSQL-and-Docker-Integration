package com.jrdspringboot.spring_boot_beginners;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepository extends JpaRepository<Customers, Integer> {
}
