package com.joelmvc.controllers;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joelmvc.models.Work;

public interface WorkRepository extends JpaRepository<Work, Integer>
{

}
