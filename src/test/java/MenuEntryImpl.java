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

import com.tecknix.modding.api.menu.IMenuEntry;

public class MenuEntryImpl implements IMenuEntry {

    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public String getDisplayDescription() {
        return null;
    }

    @Override
    public Double getModVersion() {
        return null;
    }

    @Override
    public boolean getModEnabled() {
        return false;
    }

    @Override
    public void onButtonToggle() {

    }

    @Override
    public void onSettingsPress() {

    }
}
