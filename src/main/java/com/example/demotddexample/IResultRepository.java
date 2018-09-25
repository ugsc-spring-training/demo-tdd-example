package com.example.demotddexample;

import java.util.List;

public interface IResultRepository {
    List<Integer> findByYear(int year);
}
