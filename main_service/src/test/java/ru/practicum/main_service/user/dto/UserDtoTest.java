package ru.practicum.main_service.user.dto;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@JsonTest
class UserDtoTest {

    private JacksonTester<UserDto> json;

    @Autowired
    public UserDtoTest(JacksonTester<UserDto> json) {
        this.json = json;
    }

    @Test
    @SneakyThrows
    void testUserDto() {
        UserDto userDto = new UserDto(1L, "userDto1Name", "userDto1@Email");
        JsonContent<UserDto> result = json.write(userDto);
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(userDto.getId().intValue());
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo(userDto.getName());
        assertThat(result).extractingJsonPathStringValue("$.email").isEqualTo(userDto.getEmail());
    }
}