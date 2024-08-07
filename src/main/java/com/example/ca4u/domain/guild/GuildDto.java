package com.example.ca4u.domain.guild;

import lombok.*;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class GuildDto {
    private Long id;

    public static GuildDto of(Guild guild){
        return GuildDto.builder().
                id(guild.getId())
                .build();

    }
}
