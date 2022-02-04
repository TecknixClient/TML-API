package com.tecknix.modding.api.event.type;

import com.tecknix.modding.api.event.TMEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public class TMKeyEvent extends TMEvent {

    @Getter private int key;

}
