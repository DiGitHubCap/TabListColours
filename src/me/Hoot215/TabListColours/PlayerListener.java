/*
 * Customizable colours for the tab player listing.
 * Copyright (C) 2013 Andrew Stevanus (Hoot215) <hoot893@gmail.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package me.Hoot215.TabListColours;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Team;

public class PlayerListener implements Listener
  {
    private final TabListColours plugin = TabListColours.getInstance();
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin (PlayerJoinEvent event)
      {
        Player player = event.getPlayer();
        String prefix = plugin.getChat().getPlayerPrefix(player);
        if (prefix != null && !prefix.isEmpty())
          {
            for (Player p : TabListColours.getInstance().getServer()
                .getOnlinePlayers())
              {
                  {
                    Team team = p.getScoreboard().getTeam(prefix);
                    if (team == null)
                      {
                        team = p.getScoreboard().registerNewTeam(prefix);
                        team.setPrefix(ChatColor.translateAlternateColorCodes(
                            '&', prefix));
                      }
                    team.addPlayer(player);
                  }
                  {
                    String pfx = plugin.getChat().getPlayerPrefix(p);
                    Team team = player.getScoreboard().getTeam(pfx);
                    if (team == null)
                      {
                        team = player.getScoreboard().registerNewTeam(pfx);
                        team.setPrefix(ChatColor.translateAlternateColorCodes(
                            '&', pfx));
                      }
                    team.addPlayer(p);
                  }
              }
          }
      }
  }
