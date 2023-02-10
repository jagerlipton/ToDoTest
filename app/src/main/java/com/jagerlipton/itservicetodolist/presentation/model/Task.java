package com.jagerlipton.itservicetodolist.presentation.model;

public class Task {
    private Integer id;
    private Long dateStart;
    private Long dateEnd;
    private String info;
    private String status;

    public Task(Integer id, Long dateStart, Long dateEnd, String info, String status) {
        this.id = id;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.info = info;
        this.status = status;
    }

    public Task() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getDateStart() {
        return dateStart;
    }

    public void setDateStart(Long dateStart) {
        this.dateStart = dateStart;
    }

    public Long getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Long dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", info='" + info + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}