package org.ouroui.interns.Dto;

public class OrderResponce {
    private String name;
    private String role;

    public OrderResponce(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public OrderResponce() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
