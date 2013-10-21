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

import net.milkbowl.vault.chat.Chat;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class TabListColours extends JavaPlugin
  {
    private static TabListColours instance;
    private Chat chat;
    
    public static TabListColours getInstance ()
      {
        return instance;
      }
    
    public Chat getChat ()
      {
        return chat;
      }
    
    private boolean setupChat ()
      {
        RegisteredServiceProvider<Chat> chatProvider =
            getServer().getServicesManager().getRegistration(
                net.milkbowl.vault.chat.Chat.class);
        if (chatProvider != null)
          {
            chat = chatProvider.getProvider();
          }
        
        return (chat != null);
      }
    
    @Override
    public void onDisable ()
      {
        instance = null;
        
        this.getLogger().info("Is now disabled");
      }
    
    @Override
    public void onEnable ()
      {
        instance = this;
        
        if (this.setupChat())
          {
            this.getLogger().info("Vault linked successfully");
          }
        else
          {
            this.getLogger().severe("Failed to link Vault!");
            this.getLogger().severe("Disabling plugin");
            this.getPluginLoader().disablePlugin(this);
            return;
          }
        
        this.getServer().getPluginManager()
            .registerEvents(new PlayerListener(), this);
        
        this.getLogger().info("Is now enabled");
      }
  }
