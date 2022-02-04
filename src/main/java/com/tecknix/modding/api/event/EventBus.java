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

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EventBus {

    private static final Map<Class<? extends TMEvent>, ArrayList<EventContent>> EVENT_REGISTRY = new ConcurrentHashMap<>();

    /**
     * Post an event to the API.
     *
     * @param event Passes through the event to post.
     * @author Tecknix Software.
     */
    public static void post(final TMEvent event) {
        //Gets the content object from the map if the key matches that of this events class.
        final ArrayList<EventContent> contents = EVENT_REGISTRY.get(event.getClass());
        if (contents != null) {
            for (final EventContent content : contents) {
                event.invoke(content);
            }
        }
    }


    /**
     * Register an object to the event bus.
     *
     * @param register The object you wish to register to the bus. (typically a class)
     * @author Tecknix Software.
     */
    @SuppressWarnings("unchecked")
    public static void register(final Object register) {
        //Iterating though all of our classes methods.
        for (final Method method : register.getClass().getMethods()) {
            //Checking if our Subscribe annotation is present before continuing.
            if (method.isAnnotationPresent(TMSubscription.class)) {

                //The first param of the method ex: void foo(ExampleEvent event).
                final Class<?> methodClass = method.getParameterTypes()[0];

                //Creates the content object for the object and method being registered.
                final EventContent content = new EventContent(register, method);

                //Setting the method to accessible just in-case.
                if (!content.getMethod().isAccessible()) {
                    content.getMethod().setAccessible(true);
                }

                //Only put if the map doesn't contain it already.
                if (!EVENT_REGISTRY.containsKey(methodClass)) {

                    //Puts the EventContent object into the registry map.
                    EVENT_REGISTRY.put((Class<? extends TMEvent>) methodClass, new ArrayList<EventContent>() {
                        {
                            add(content);
                        }
                    });
                }
            }
        }
    }

    /**
     * Unregister an event from the bus.
     *
     * @param unregister The object you wish to unregister.
     * @author Tecknix Software.
     */
    public static void unregister(final Object unregister) {

        for (ArrayList<EventContent> mapArray : EVENT_REGISTRY.values()) {
            //Remove the content object from the map if it equals that of the method.
            mapArray.removeIf(eventContent -> eventContent.getRegister().equals(unregister));
        }
    }

    /**
     * The event content object.
     * This stores our object and event method.
     *
     * @author Tecknix Software.
     */
    @AllArgsConstructor
    static class EventContent {
        //The object.
        @Getter private final Object register;
        //The method.
        @Getter private final Method method;
    }
}
