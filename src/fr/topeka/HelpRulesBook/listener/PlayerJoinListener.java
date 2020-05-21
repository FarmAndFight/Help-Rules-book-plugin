package fr.topeka.HelpRulesBook.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.topeka.HelpRulesBook.Main;

public class PlayerJoinListener implements Listener {

	private Main main;
	
	public PlayerJoinListener(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if(!player.hasPlayedBefore()) {
			main._book.openBook(player);
		}
	}

}
