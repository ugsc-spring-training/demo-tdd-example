package com.example.demotddexample;

public class Race {
    public Race(Long id, Integer year, Integer result) {
        this.id = id;
        this.year = year;
        this.result = result;
    }

    private Long id;
    private Integer year;
    private Integer result;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
