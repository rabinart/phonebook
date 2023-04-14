package com.rabinart;

import java.io.Serializable;
import java.util.Objects;

public class UserDto implements Serializable {
    private final String name;
    private final String number;

    public UserDto(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return number.equals(userDto.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
