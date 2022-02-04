/*
 *     Copyright Tecknix Software 2022.
 *
 *     This is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.tecknix.modding.api.event;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;

public abstract class TMEvent {

    @Getter @Setter private boolean canceled;

    /**
     * Invokes an events method.
     *
     * @param eventContent Accepts an events content.
     * @author Tecknix Software.
     */
    public void invoke(EventBus.EventContent eventContent) {
        try {
            eventContent.getMethod().invoke(eventContent.getRegister(), this);

        } catch (IllegalAccessException | InvocationTargetException exception) {
            exception.printStackTrace();
        }
    }
}
