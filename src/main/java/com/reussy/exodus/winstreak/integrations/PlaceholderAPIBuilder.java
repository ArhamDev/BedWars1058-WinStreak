package com.reussy.exodus.winstreak.integrations;

import com.reussy.exodus.winstreak.WinStreakPlugin;
import com.reussy.exodus.winstreak.cache.StreakProperties;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class PlaceholderAPIBuilder extends PlaceholderExpansion {

    private final WinStreakPlugin plugin;

    public PlaceholderAPIBuilder(WinStreakPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * The placeholder identifier of this expansion. May not contain {@literal %},
     * {@literal {}} or _
     *
     * @return placeholder identifier that is associated with this expansion
     */
    @Override
    public @NotNull String getIdentifier() {
        return "winstreak";
    }

    /**
     * The author of this expansion
     *
     * @return name of the author for this expansion
     */
    @Override
    public @NotNull String getAuthor() {
        return "reussy";
    }

    /**
     * The version of this expansion
     *
     * @return current version of this expansion
     */
    @Override
    public @NotNull String getVersion() {
        return "1.1.2";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {

        if (player == null) return "";

        if (!plugin.getStreakCache().isInCache(player.getUniqueId())) {
            return "";
        }

        StreakProperties streakProperties = plugin.getStreakCache().get(player.getUniqueId());

        if (streakProperties == null) return "";

        if (params.equalsIgnoreCase("streak")) return String.valueOf(streakProperties.getStreak());

        if (params.equalsIgnoreCase("best_streak")) return String.valueOf(streakProperties.getBestStreak());

        return "";
    }
}
