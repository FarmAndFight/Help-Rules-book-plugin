# Help-Rules-book-plugin

## Ressources:

https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/Player.html#openBook-org.bukkit.inventory.ItemStack- Pour ouvrir les livres

https://hub.spigotmc.org/javadocs/spigot/org/bukkit/inventory/meta/BookMeta.html - Pour éditer un livre

Créer un ItemStack book, puis récupérer `(BookMeta) getItemMeta()` modifier les propriétés, puis `player.openBook(ItemStack)`
