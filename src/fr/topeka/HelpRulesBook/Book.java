package fr.topeka.HelpRulesBook;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Book {

	private ItemStack book;
	private Main main;
	public String _title, _author;
	public List<String> _pages;
	
	public Book(Main main) {
		this.main = main;
	}
	
	public boolean createBook() {
		book = new ItemStack(Material.WRITTEN_BOOK, 1);
		BookMeta meta = (BookMeta) book.getItemMeta();
		meta.setTitle(_title);
		meta.setAuthor(_author);
		for (String rawPage: _pages){
			ComponentBuilder page = new ComponentBuilder("");
			rawPage = rawPage.replace("&", "§");
			if (rawPage.contains("{") && rawPage.contains("}")) {
				String[] sections = rawPage.split("\\{|\\}");
				boolean edit = false;
				TextComponent message = new TextComponent();
				for(String section : sections) {
					if(!edit) {
						message.addExtra(section);
					}else {
						String[] elements = section.split(",");
						TextComponent eventBuilder = new TextComponent(elements[0]);
						if(!elements[1].equals("")) {
							eventBuilder.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(elements[1]).create()));
						}
						if(elements[2].startsWith("true")) {
//							System.out.println(elements[2].substring(5, elements[2].length() - 1));
							if(elements[2].startsWith("OPEN_URL", 5)) {
								eventBuilder.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, elements[2].substring(elements[2].indexOf(".") + 1, elements[2].length() - 1)));
							}
							if(elements[2].startsWith("RUN_COMMAND", 5)) {
								eventBuilder.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, elements[2].substring(elements[2].indexOf(".") + 1, elements[2].length() - 1)));

							}
							if(elements[2].startsWith("SUGGEST_COMMAND", 5)) {
								eventBuilder.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, elements[2].substring(elements[2].indexOf(".") + 1, elements[2].length() - 1)));

							}
							if(elements[2].startsWith("CHANGE_PAGE", 5)) {
								eventBuilder.setClickEvent(new ClickEvent(ClickEvent.Action.CHANGE_PAGE, elements[2].substring(elements[2].indexOf(".") + 1, elements[2].length() - 1)));

							}
						}
						message.addExtra(eventBuilder);
					}
					edit = !edit;
				}
				page.append(message);
			}else {
				page.append(rawPage);
			}
			meta.spigot().addPage(page.create());
		}
		if(book.setItemMeta(meta)) {
			return true;
		}
		return false;
	}
	
	public void loadConfig() {
		_title = main.getConfig().getString("book.title");
		_author = main.getConfig().getString("book.author");
		_pages = main.getConfig().getStringList("book.pages");
	}
	
	public void openBook(Player player) {
		player.openBook(book);
	}
}
