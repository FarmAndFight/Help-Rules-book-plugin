package fr.topeka.HelpRulesBook;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

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
		for(int i=0;i<_pages.size();i++)
			meta.addPage(_pages.get(i).replace("&", "§"));
		if(book.setItemMeta(meta)) 
			return true;
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
