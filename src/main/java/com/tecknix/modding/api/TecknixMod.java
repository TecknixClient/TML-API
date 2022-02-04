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

package com.tecknix.modding.api;

import com.tecknix.modding.api.menu.IMenuEntry;
import com.tecknix.modding.api.transform.IModTransformer;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
public abstract class TecknixMod {

    private final List<IMenuEntry> menuEntries = new CopyOnWriteArrayList<>();
    private final List<IModTransformer> modTransformers = new CopyOnWriteArrayList<>();

    /**
     * Called on initialization by the mod loader.
     *
     * @author Tecknix Software.
     */
    public abstract void onInitialize();

    /**
     * Called on game shutdown by the mod loader.
     *
     * @author Tecknix Software.
     */
    public abstract void onTerminate();

    /**
     * Used to register entries into the mod menu.
     *
     * @author Tecknix Software
     */
    protected void registerMenuEntries(IMenuEntry... entries) {
        this.menuEntries.addAll(Arrays.asList(entries));
    }

    /**
     * Used to register transformers.
     *
     * @author Tecknix Software
     */
    protected void registerModTransformers(IModTransformer... entries) {
        this.modTransformers.addAll(Arrays.asList(entries));
    }
}
